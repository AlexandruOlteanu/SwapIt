package com.swapit.chat.service;

import com.swapit.chat.api.domain.response.ConversationResponse;
import com.swapit.chat.api.domain.response.ConversationsPreviewResponse;

public interface GetConversationService {
    ConversationResponse getConversation(Integer conversationId);
}
