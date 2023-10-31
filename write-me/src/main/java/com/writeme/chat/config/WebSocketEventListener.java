package com.writeme.chat.config;

import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    // Ouvindo quem se desconectou
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event  ) {
        // TODO a implementar
    }
}
