package com.swapit.chat.service;

import com.swapit.chat.api.domain.dto.ConversationPreviewDTO;
import com.swapit.chat.api.domain.response.ConversationsPreviewResponse;
import com.swapit.chat.domain.Conversation;

public interface ConversationPreviewService {
    ConversationsPreviewResponse getConversationsPreview(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria);
    ConversationPreviewDTO getConversationPreview(Conversation conversation, Integer userId) throws Exception;
}
