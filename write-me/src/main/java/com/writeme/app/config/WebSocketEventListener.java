package com.writeme.app.config;

import com.writeme.app.chat.ChatMessage;
import com.writeme.app.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j // adciona automaticamente um logger SLF4J à classe.

public class WebSocketEventListener {
    private final SimpMessageSendingOperations messageTemplate;

    // Ouvindo quem se desconectou
    @EventListener
    // handleWebSocketDisconnectListener
    public void DisconnectListener( SessionDisconnectEvent aboutEvent  ) {
        StompHeaderAccessor headerAccessor  = StompHeaderAccessor.wrap( aboutEvent.getMessage()  );
        String userName = (String) headerAccessor.getSessionAttributes().get("userName");

        if (userName != null) {
            log.info("User disconnect: {}", userName);
            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .sender(userName)
                    .build();
            messageTemplate.convertAndSend("/topic/public ", chatMessage );
        }
    }
}
