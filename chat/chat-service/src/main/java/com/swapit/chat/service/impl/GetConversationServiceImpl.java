package com.swapit.chat.service.impl;

import com.swapit.chat.api.domain.dto.MessageDTO;
import com.swapit.chat.api.domain.response.ConversationResponse;
import com.swapit.chat.domain.Conversation;
import com.swapit.chat.domain.ConversationParticipants;
import com.swapit.chat.repository.ConversationRepository;
import com.swapit.chat.service.ConversationPreviewService;
import com.swapit.chat.service.GetConversationService;
import com.swapit.commons.encryption.EncryptionService;
import com.swapit.commons.exception.ExceptionFactory;
import com.swapit.commons.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class GetConversationServiceImpl implements GetConversationService {

    private final ConversationRepository conversationRepository;
    private final EncryptionService encryptionService;
    private final ExceptionFactory exceptionFactory;

    @Override
    public ConversationResponse getConversation(Integer conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.CONVERSATION_NOT_FOUND));
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
