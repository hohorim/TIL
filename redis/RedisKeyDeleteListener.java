package bim.service.account.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import bim.service.account.base.dao.SessionData;
import bim.service.account.service.account.IAccountService;
import bim.service.account.service.account.event.consumer.model.AttendUserInfo;
import bim.service.account.service.account.event.consumer.model.UpdateTeamUserCommand;
import lombok.extern.log4j.Log4j2;

/**
 * Redis 캐시 삭제 이벤트 수신
 */
@Component
@Log4j2
public class RedisKeyDeleteListener implements MessageListener {

    @Autowired
    private IAccountService accountService;
    private final ObjectMapper mapper=new ObjectMapper();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("cache delete");
        try {
            SessionData sessionData=mapper.convertValue(message.toString(), SessionData.class);
            
            if(sessionData!=null){
                AttendUserInfo attendUserInfo=new AttendUserInfo(sessionData.getTenantId(),sessionData.getUserId(),sessionData.getProjectInfos());
                UpdateTeamUserCommand command = new UpdateTeamUserCommand();
                command.setTenantId(sessionData.getTenantId());
                command.setAttendUserInfo(List.of(attendUserInfo));
                accountService.updateUserToken(command);
            }
        } catch (Exception e) {
            log.info(e);
        }
    }
    
}



/**
@Bean
	public RedisMessageListenerContainer redisMessageListenerContainer() {
		RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
		redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory());
        redisMessageListenerContainer.addMessageListener(redisKeyDeleteListener(), ChannelTopic.of("cache-delete"));

		return redisMessageListenerContainer;
	}
빈등록해서 listenerContainer에 listener class 등록후 위 클래스 사용.

*/

/**
	redisTemplate.convertAndSend("cache-delete", message);
	// 호출 시 "cache-delete" 라는 channel 이 열리고 listenrContainer에서 구독하고 있다가 channel에 메시지가 전송되면 해당 메시지를 듣는다.
*
