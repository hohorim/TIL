package bim.service.project.dao.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Repository
public class EmitterRepository implements IEmitterRepository {

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final Map<String, Object> eventCache = new ConcurrentHashMap<>();
    
	@Override
	public SseEmitter save(String resultId, SseEmitter sseEmitter) {
		// TODO Auto-generated method stub
		 emitters.put(resultId, sseEmitter);
	     return sseEmitter;
	}

	@Override
	public void deletebyId(String resultId) {
		// TODO Auto-generated method stub
		emitters.remove(resultId);
	}


	@Override
	public Map<String, SseEmitter> findAllStartWithById(String resultId) {
		// TODO Auto-generated method stub
		return emitters.entrySet().stream()
			.filter(entry -> entry.getKey().startsWith(resultId))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


	@Override
	public Map<String, SseEmitter> findAllEmitter() {
		// TODO Auto-generated method stub
		return emitters.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
	
	@Override
	public Map<String, Object> findAllEventCacheStartWithById(String resultId) {
		// TODO Auto-generated method stub
		return eventCache.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(resultId))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	@Override
	public void saveEventCache(String resultId, Object event) {
		// TODO Auto-generated method stub
		eventCache.put(resultId, event);
	}
	
	@Override
	public Map<String, Object> findAllCache() {
		// TODO Auto-generated method stub
		return eventCache.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	@Override
	public void deleteCacheById(String resultId) {
		// TODO Auto-generated method stub
		eventCache.remove(resultId);
	}
	

}
