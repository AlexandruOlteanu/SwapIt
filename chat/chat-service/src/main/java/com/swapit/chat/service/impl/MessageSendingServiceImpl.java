package com.swapit.chat.service.impl;

import com.pusher.rest.Pusher;
import com.swapit.chat.api.domain.request.PrivateChatMessageRequest;
import com.swapit.chat.domain.Conversation;
import com.swapit.chat.domain.ConversationParticipants;
import com.swapit.chat.domain.Message;
import com.swapit.chat.repository.ConversationParticipantsRepository;
import com.swapit.chat.repository.ConversationRepository;
import com.swapit.chat.repository.MessageRepository;
import com.swapit.chat.service.CacheService;
import com.swapit.chat.service.MessageSendingService;
import com.swapit.chat.utils.ConversationType;
import com.swapit.chat.utils.MessageType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.time.ZonedDateTime;

import static com.swapit.chat.utils.Constants.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class MessageSendingServiceImpl implements MessageSendingService {

    @Qualifier("pusherBean")
    private final Pusher pusher;
    private final ConversationRepository conversationRepository;
    private final ConversationParticipantsRepository conversationParticipantsRepository;
    private final MessageRepository messageRepository;
    private final CacheService cacheService;
    @Override
    @Transactional
    public void sendPrivateMessage(PrivateChatMessageRequest request) {
        var senderId = request.getSenderId();
        var receiverId = request.getReceiverId();
        Integer conversationId = request.getConversationId();
        Conversation conversation;
        ZonedDateTime updatedLastAction = ZonedDateTime.now();
        if (conversationId == null) {
            conversationId = conversationRepository.findPrivateConversationId(senderId, receiverId).orElse(null);
        }
        if (conversationId == null) {
            conversation = conversationRepository.save(Conversation.builder()
                            .lastActionAt(updatedLastAction)
                            .type(ConversationType.PRIVATE)
                    .build());
            savePrivateConversationParticipant(senderId, conversation);
            savePrivateConversationParticipant(receiverId, conversation);
            conversationId = conversation.getConversationId();
        }
        else {
            conversation = conversationRepository.findById(conversationId)
                    .orElseThrow();
            conversation.setLastActionAt(updatedLastAction);
        }

        messageRepository.save(Message.builder()
                        .conversation(conversation)
                        .sentAt(updatedLastAction)
                        .sentBy(senderId)
                        .type(MessageType.valueOf(request.getMessageType()))
                        .value(request.getMessage())
                .build());

        String channel = generatePrivateConversationChannelPath(receiverId, conversationId);

        pusher.trigger(channel, MESSAGE, request);
        log.info("Message sent to channel: " + channel);
        cacheService.deleteAllCachedConversationPreview(senderId);
        cacheService.deleteAllCachedConversationPreview(receiverId);
        cacheService.deleteCachedConversationPreview(conversationId, senderId);
        cacheService.deleteCachedConversationPreview(conversationId, receiverId);
        cacheService.deleteCachedConversation(conversationId);
    }

    private String generatePrivateConversationChannelPath(Integer receiver, Integer conversationId) {
        return String.join("_", USER, receiver.toString(), CONVERSATION, conversationId.toString());
    }

    private void savePrivateConversationParticipant(Integer userId, Conversation conversation) {
        conversationParticipantsRepository.save(ConversationParticipants.builder()
                .userId(userId)
                .conversation(conversation)
                .build());
    }
}
