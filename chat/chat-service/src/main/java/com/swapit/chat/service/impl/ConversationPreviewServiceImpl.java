package com.swapit.chat.service.impl;

import com.swapit.chat.api.domain.dto.ConversationPreviewDTO;
import com.swapit.chat.domain.Conversation;
import com.swapit.chat.domain.ConversationParticipants;
import com.swapit.chat.repository.ConversationRepository;
import com.swapit.chat.service.ConversationPreviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.swapit.commons.cache.CacheConstants.CACHE_SINGULAR_CONVERSATION_PREVIEW;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationPreviewServiceImpl implements ConversationPreviewService {

    private final ConversationRepository conversationRepository;

    @Override
    @Cacheable(value = CACHE_SINGULAR_CONVERSATION_PREVIEW, key = "@cacheKeyGenerator.generateKey(#conversationId, #userId)")
    public ConversationPreviewDTO getConversationPreview(Integer conversationId, Integer userId) {
        Conversation conversation = conversationRepository.findById(conversationId).orElse(Conversation.builder().build());
        List<Integer> otherParticipantsIds = conversation.getConversationParticipants().stream()
                .map(ConversationParticipants::getUserId)
                .filter(participantId -> !participantId.equals(userId))
                .toList();
        return ConversationPreviewDTO.builder()
                .otherParticipantsIds(otherParticipantsIds)
                .conversationTitle(conversation.getConversationTitle())
                .sentByUser(conversation.getMessages().getLast().getSentBy().equals(userId))
                .lastMessageSent(conversation.getMessages().getLast().getValue())
                .lastMessageSentAt(conversation.getMessages().getLast().getSentAt())
                .conversationId(conversation.getConversationId())
                .build();
    }
}
