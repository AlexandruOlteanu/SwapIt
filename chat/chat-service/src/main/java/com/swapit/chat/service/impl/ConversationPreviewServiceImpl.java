package com.swapit.chat.service.impl;

import com.swapit.chat.api.domain.dto.ConversationPreviewDTO;
import com.swapit.chat.api.domain.response.ConversationsPreviewResponse;
import com.swapit.chat.domain.Conversation;
import com.swapit.chat.domain.ConversationParticipants;
import com.swapit.chat.repository.ConversationRepository;
import com.swapit.chat.service.ConversationPreviewService;
import com.swapit.chat.util.ConversationSortCriteria;
import com.swapit.commons.encryption.EncryptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.swapit.chat.util.ChatCommonFunctions.getSortingCriteria;
import static com.swapit.chat.util.ConversationSortCriteria.NEWEST;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationPreviewServiceImpl implements ConversationPreviewService {

    private final EncryptionService encryptionService;
    private final ConversationRepository conversationRepository;

    @Override
    public ConversationsPreviewResponse getConversationsPreview(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        if (sortCriteria == null) {
            sortCriteria = NEWEST.name();
        }
        Pageable pageable = PageRequest.of(chunkNumber, nrElementsPerChunk, Sort.by(getSortingCriteria(sortCriteria)).descending());
        Page<Conversation> data = conversationRepository.findAllByUserId(userId, pageable);
        List<Conversation> conversations = data.getContent();
        return ConversationsPreviewResponse.builder()
                .conversationsPreview(conversations.stream()
                        .map(conversation -> {
                            try {
                                return getConversationPreview(conversation, userId);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }).toList())
                .build();
    }

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
