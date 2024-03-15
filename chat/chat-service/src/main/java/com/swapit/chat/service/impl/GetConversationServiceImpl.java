package com.swapit.chat.service.impl;

import com.swapit.chat.api.domain.dto.MessageDTO;
import com.swapit.chat.api.domain.response.ConversationResponse;
import com.swapit.chat.api.domain.response.ConversationsPreviewResponse;
import com.swapit.chat.domain.Conversation;
import com.swapit.chat.domain.ConversationParticipants;
import com.swapit.chat.repository.ConversationRepository;
import com.swapit.chat.service.ConversationPreviewService;
import com.swapit.chat.service.GetConversationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.swapit.commons.cache.CacheConstants.CACHE_CONVERSATION;
import static com.swapit.commons.cache.CacheConstants.CACHE_CONVERSATIONS_PREVIEW;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetConversationServiceImpl implements GetConversationService {

    private final ConversationRepository conversationRepository;
    private final ConversationPreviewService conversationPreviewService;
    @Override
    @Cacheable(value = CACHE_CONVERSATIONS_PREVIEW, key = "@cacheKeyGenerator.generateKey(#userId)")
    public ConversationsPreviewResponse getConversationsPreview(Integer userId) {
        List<Integer> conversationsIds = conversationRepository.findAllConversationsIdsOfUser(userId)
                .orElse(new ArrayList<>());
        return ConversationsPreviewResponse.builder()
                .conversationsPreview(conversationsIds.stream()
                        .map(conversationId -> conversationPreviewService.getConversationPreview(conversationId, userId)).toList())
                .build();
    }

    @Override
    @Cacheable(value = CACHE_CONVERSATION, key = "@cacheKeyGenerator.generateKey(#conversationId)")
    public ConversationResponse getConversation(Integer conversationId) {
        Conversation conversation = conversationRepository.findByConversationId(conversationId)
                .orElseThrow(() -> new RuntimeException("Conversation doesn't exist"));
        Collections.reverse(conversation.getMessages());
        List<Integer> conversationParticipantsIds = conversation.getConversationParticipants()
                .stream().map(ConversationParticipants::getUserId).toList();
        List<MessageDTO> messages = conversation.getMessages()
                .stream().map(message -> MessageDTO.builder()
                        .value(message.getValue())
                        .sentBy(message.getSentBy())
                        .sentAt(message.getSentAt())
                        .type(message.getType().name())
                        .build()).toList();
        return ConversationResponse.builder()
                .conversationType(conversation.getType().name())
                .conversationParticipantsIds(conversationParticipantsIds)
                .messages(messages)
                .build();
    }
}
