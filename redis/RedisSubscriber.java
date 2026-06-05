package bimframework.collabor.project.config;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.bim.common.handler.RedisHandler;

import bimframework.collabor.project.service.project.IProjectService;
import bimframework.collabor.project.service.team.event.model.AttendUserInfo;
import bimframework.collabor.project.service.team.event.model.UpdateTeamUserEvent;
import lombok.extern.log4j.Log4j2;

/**
 * Redis 캐시 만료 이벤트 수신
 */
@Component
@Log4j2
public class RedisSubscriber extends KeyExpirationEventMessageListener {

    @Autowired
    IProjectService projectService;
    @Autowired
    RedisHandler redisHandler;
    @Autowired
    ApplicationEventPublisher eventPublisher;

    public RedisSubscriber(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, @Nullable byte[] pattern) {
        log.info("cache expired");
        try {

            Map<String,String> keys=redisHandler.getKey(message.toString());
            AttendUserInfo attendUserInfo=projectService.myProjectAllList(keys.get("tenantId"),keys.get("userId"));

            UpdateTeamUserEvent event = UpdateTeamUserEvent.builder()
            .tenantId(keys.get("tenantId"))
            .attendUserInfo(List.of(attendUserInfo))
            .build();
            eventPublisher.publishEvent(event);
        } catch (Exception e) {
            log.info(e);
        }

    }
}

/**
빈 등록없이 KeyExpirationEventMessageListener 상속받아서 클래스 component 로 사용
*/