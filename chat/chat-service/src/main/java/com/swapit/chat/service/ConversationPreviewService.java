package com.swapit.chat.service;

import com.swapit.chat.api.domain.dto.ConversationPreviewDTO;
import com.swapit.chat.domain.Conversation;

public interface ConversationPreviewService {
    ConversationPreviewDTO getConversationPreview(Conversation conversation, Integer userId);
}
