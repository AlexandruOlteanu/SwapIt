package com.swapit.chat.service;

import com.swapit.chat.api.domain.dto.ConversationPreviewDTO;

public interface ConversationPreviewService {
    ConversationPreviewDTO getConversationPreview(Integer conversationId, Integer userId);
}
