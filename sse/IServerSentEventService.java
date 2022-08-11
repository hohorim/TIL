package bim.service.project.service.project;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import bim.service.project.dao.entity.ManagerEntity;
import bim.service.project.dao.entity.TeamUserEntity;

@Service
public interface IServerSentEventService {
	
	public SseEmitter SseTest(String userId, String lastEventId) throws IOException;
	
	public void send(List<ManagerEntity> list, Object resultObj, String typeKey);
}
