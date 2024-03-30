package com.swapit.chat.service;

import com.swapit.chat.api.domain.response.ConversationResponse;

public interface GetConversationService {
    ConversationResponse getConversation(Integer conversationId);
}
