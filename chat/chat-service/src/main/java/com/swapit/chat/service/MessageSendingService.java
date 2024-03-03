package com.swapit.chat.service;

import com.swapit.chat.api.domain.request.PrivateChatMessage;

import java.util.concurrent.ExecutionException;

public interface MessageSendingService {

    void sendPrivateMessage(PrivateChatMessage request) throws Exception;

}
