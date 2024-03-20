package com.swapit.chat.service.impl;

import com.swapit.chat.api.domain.dto.ConversationPreviewDTO;
import com.swapit.chat.domain.Conversation;
import com.swapit.chat.domain.ConversationParticipants;
import com.swapit.chat.repository.ConversationRepository;
import com.swapit.chat.service.ConversationPreviewService;
import com.swapit.commons.encryption.EncryptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationPreviewServiceImpl implements ConversationPreviewService {

    private final EncryptionService encryptionService;

    @Override
    public ConversationPreviewDTO getConversationPreview(Conversation conversation, Integer userId) throws Exception {
        List<Integer> otherParticipantsIds = conversation.getConversationParticipants().stream()
                .map(ConversationParticipants::getUserId)
                .filter(participantId -> !participantId.equals(userId))
                .toList();
        return ConversationPreviewDTO.builder()
                .otherParticipantsIds(otherParticipantsIds)
                .conversationTitle(conversation.getConversationTitle())
                .sentByUser(conversation.getMessages().getLast().getSentBy().equals(userId))
                .lastMessageSent(encryptionService.decrypt(conversation.getMessages().getLast().getValue()))
                .lastMessageSentAt(conversation.getMessages().getLast().getSentAt())
                .conversationId(conversation.getConversationId())
                .build();
    }
}
