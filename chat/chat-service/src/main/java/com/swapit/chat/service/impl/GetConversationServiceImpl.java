package com.swapit.chat.service.impl;

import com.swapit.chat.api.domain.dto.MessageDTO;
import com.swapit.chat.api.domain.response.ConversationResponse;
import com.swapit.chat.api.domain.response.ConversationsPreviewResponse;
import com.swapit.chat.domain.Conversation;
import com.swapit.chat.domain.ConversationParticipants;
import com.swapit.chat.repository.ConversationRepository;
import com.swapit.chat.service.ConversationPreviewService;
import com.swapit.chat.service.GetConversationService;
import com.swapit.commons.encryption.EncryptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class GetConversationServiceImpl implements GetConversationService {

    private final ConversationRepository conversationRepository;
    private final ConversationPreviewService conversationPreviewService;
    private final EncryptionService encryptionService;
    @Override
    public ConversationsPreviewResponse getConversationsPreview(Integer userId) {
        List<Conversation> conversations = conversationRepository.findAllByUserId(userId)
                .orElse(new ArrayList<>());
        return ConversationsPreviewResponse.builder()
                .conversationsPreview(conversations.stream()
                        .map(conversation -> {
                            try {
                                return conversationPreviewService.getConversationPreview(conversation, userId);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }).toList())
                .build();
    }

    @Override
    public ConversationResponse getConversation(Integer conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new RuntimeException("Conversation doesn't exist"));
        Collections.reverse(conversation.getMessages());
        List<Integer> conversationParticipantsIds = conversation.getConversationParticipants()
                .stream().map(ConversationParticipants::getUserId).toList();
        List<MessageDTO> messages = conversation.getMessages()
                .stream().map(message -> {
                    try {
                        return MessageDTO.builder()
                                .value(encryptionService.decrypt(message.getValue()))
                                .sentBy(message.getSentBy())
                                .sentAt(message.getSentAt())
                                .type(message.getType().name())
                                .build();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .sorted(Comparator.comparing(MessageDTO::getSentAt).reversed())
                .toList();
        return ConversationResponse.builder()
                .conversationType(conversation.getType().name())
                .conversationParticipantsIds(conversationParticipantsIds)
                .messages(messages)
                .build();
    }
}
