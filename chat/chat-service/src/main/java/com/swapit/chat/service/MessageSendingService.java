package com.swapit.chat.service;

import com.swapit.chat.api.domain.request.PrivateChatMessageRequest;

public interface MessageSendingService {

    void sendPrivateMessage(Integer userId, PrivateChatMessageRequest request) throws Exception;

}
