package bim.service.project.dao.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Repository
public interface IEmitterRepository{
	
	/* emitter 저장 */
	SseEmitter save(String resultId, SseEmitter sseEmitter);
	
	/* emitter 삭제 */
	void deletebyId(String resultId);
	
	/* 해당 id와 관련된 모든 emitter 조회 */
	Map<String, SseEmitter> findAllStartWithById(String resultId);
	
	/* 모든 emitter 조회 */
	Map<String, SseEmitter> findAllEmitter();

	
	/* 이벤트 저장 */
	void saveEventCache(String resultId, Object event);
	
	/* 이벤트 삭제 */
	void deleteCacheById(String resultId);
	
	/* 해당 id와 관련된 모든 이벤트 조회 */
	Map<String, Object> findAllEventCacheStartWithById(String resultId);
	
	/* 모든 이벤트 조회 */
	Map<String, Object> findAllCache();
}
