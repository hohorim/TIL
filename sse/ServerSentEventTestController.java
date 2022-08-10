package bim.service.project.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import bim.service.project.service.project.IServerSentEventService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sse")
public class ServerSentEventTestController {

	private final IServerSentEventService sseService;
	
	/* sseTest (서버로 sse연결요청) */
	@RequestMapping(value="/test/{userId}", method=RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public SseEmitter SseTest(@PathVariable String userId, @RequestHeader(value="Last-Event-ID", required = false, defaultValue = "") String lastEventId) throws IOException {
		
		// lastEventId: 브라우저가 마지막으로 발생한 이벤트를 추적하기 위해
		SseEmitter sseEmitter = sseService.SseTest(userId, lastEventId);
		
		return sseEmitter;
	}
}
