package bim.service.project.service.project;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import bim.service.project.dao.entity.ManagerEntity;
import bim.service.project.dao.entity.TeamUserEntity;
import bim.service.project.dao.repository.IEmitterRepository;
import bim.service.project.service.command.BaseCommand;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServerSentEventServiceImpl implements IServerSentEventService {
	
	private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;	
	private final IEmitterRepository emitterRepository;
	
	
	/* sse 연결 요청 */
	@Override
	public SseEmitter SseTest(String userId, String lastEventId) throws IOException {
		// TODO Auto-generated method stub

		// Emitter 객체 생성
		SseEmitter emitter = emitterRepository.save(userId, new SseEmitter(DEFAULT_TIMEOUT));
		
		// 시간 초과 및 네트워크 오류를 포함한 모든 이유로 비동기 요청이 정상 동작할 수 없다면 저장해둔 emitter 삭제
		emitter.onCompletion(() -> emitterRepository.deletebyId(userId));
		emitter.onTimeout(() -> emitterRepository.deletebyId(userId));
		
		sendToClient(userId, emitter, "connect succ!  " + userId, "init");
		
		// 재연결 시 미전송된 데이터 
		if(!lastEventId.isEmpty()) {
			Map<String, Object> cache = emitterRepository.findAllEventCacheStartWithById(userId);
			
			cache.entrySet().stream().filter(x-> lastEventId.compareTo(x.getKey())<0)
				.forEach(x->sendToClient(x.getKey(), emitter, x.getValue(), "re_send"));
		}
		// 미연결 시 미전송된 데이터
		else {
			Map<String, Object> cache = emitterRepository.findAllEventCacheStartWithById(userId);
			
			cache.entrySet().stream()
			.forEach(entry-> sendToClient(entry.getKey(), emitter, entry.getValue(), "init_send"));	
		}
		

		return emitter;
	}

	/* 연결된 client로 데이터 전송 */
	private SseEmitter sendToClient(String resultKey, SseEmitter emitter,Object data, String type) {
		try {

			emitter.send(SseEmitter.event()	//SseEmitter.event() -> SseEventBuilder
									.id(resultKey)	// key
									.name(type)		// event 유형
									.data(data));	// 클라이언트로 전송할 데이터

		} catch(IOException exception) {
			throw new RuntimeException("연결 오류");
		}
		return emitter;
	}
	
	@Override
	public void send(List<ManagerEntity> userId, Object resultObj, String typeKey) {	
		// resultObj: 클라이언트로 전송할 객체
	
		for(ManagerEntity entity: userId) {
			
			String resultKey = entity.getUserId() + "_" + System.currentTimeMillis();	
			
			// event 저장
			emitterRepository.saveEventCache(resultKey, resultObj);
			
			Map<String, SseEmitter> sseEmitters = emitterRepository.findAllStartWithById(entity.getUserId());
			sseEmitters.forEach((key, emitter)->{
						sendToClient(resultKey, emitter, resultObj, typeKey);		
			});
		}
	}
}
