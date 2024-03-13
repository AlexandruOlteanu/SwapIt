package com.swapit.chat.service;

import com.swapit.chat.api.domain.response.ConversationResponse;

public interface CacheService {
    void deleteCachedConversationPreview(Integer conversationId, Integer userId);
    void deleteAllCachedConversationPreview(Integer userId);
    void deleteCachedConversation(Integer conversationId);
}
