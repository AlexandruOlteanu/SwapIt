package com.swapit.chat.service;

import com.swapit.chat.api.domain.request.PrivateChatMessage;

public interface MessageSendingService {

    void sendPrivateMessage(PrivateChatMessage request);

}
