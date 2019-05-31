package com.epam.ua.trainingProject.service.impl;

import com.epam.ua.trainingProject.service.UserService;
import com.epam.ua.trainingProject.service.WebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebSocketServiceImpl implements WebSocketService {

    private final SimpMessagingTemplate messagingService;

    @Override
    public void pushWebsocketDtoToApp(int accountId, String message) {
        String topic = String.format("/topic/%s", accountId);
        messagingService.convertAndSend(topic, message);
    }
}
