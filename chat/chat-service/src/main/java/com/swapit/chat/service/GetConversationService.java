package com.swapit.chat.service;

import com.swapit.chat.api.domain.response.ConversationResponse;
import com.swapit.chat.api.domain.response.ConversationsPreviewResponse;
import com.swapit.chat.domain.Conversation;

public interface GetConversationService {
    ConversationsPreviewResponse getConversationsPreview(Integer userId);
    ConversationResponse getConversation(Integer conversationId) throws Exception;
}
