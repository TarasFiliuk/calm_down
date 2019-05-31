package com.epam.ua.trainingProject.service;

import org.springframework.scheduling.annotation.Async;

public interface WebSocketService {
    @Async
    void pushWebsocketDtoToApp(int accountId,String message);
}
