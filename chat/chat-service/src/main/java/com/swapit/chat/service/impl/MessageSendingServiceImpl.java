package com.swapit.chat.service.impl;

import com.pusher.rest.Pusher;
import com.swapit.chat.api.domain.request.PrivateChatMessageRequest;
import com.swapit.chat.domain.Conversation;
import com.swapit.chat.domain.ConversationParticipant;
import com.swapit.chat.domain.Message;
import com.swapit.chat.repository.ConversationParticipantRepository;
import com.swapit.chat.repository.ConversationRepository;
import com.swapit.chat.repository.MessageRepository;
import com.swapit.chat.service.MessageSendingService;
import com.swapit.chat.util.ConversationType;
import com.swapit.chat.util.MessageType;
import com.swapit.commons.encryption.EncryptionService;
import com.swapit.commons.exception.ExceptionFactory;
import com.swapit.commons.exception.ExceptionType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import static com.swapit.chat.util.Constants.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class MessageSendingServiceImpl implements MessageSendingService {

    @Qualifier("pusherBean")
    private final Pusher pusher;
    private final ConversationRepository conversationRepository;
    private final ConversationParticipantRepository conversationParticipantRepository;
    private final MessageRepository messageRepository;
    private final EncryptionService encryptionService;
    private final ExceptionFactory exceptionFactory;
    @Override
    @Transactional
    public void sendPrivateMessage(Integer userId, PrivateChatMessageRequest request) throws Exception {
        var senderId = userId;
        var receiverId = request.getReceiverId();
        Conversation conversation;
        ZonedDateTime updatedLastAction = ZonedDateTime.now();
        Integer conversationId = conversationRepository.findPrivateConversationId(senderId, receiverId).orElse(null);
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
                    .orElseThrow(() -> exceptionFactory.create(ExceptionType.CONVERSATION_NOT_FOUND));
            conversation.setLastActionAt(updatedLastAction);
        }

        messageRepository.save(Message.builder()
                        .conversation(conversation)
                        .sentAt(updatedLastAction)
                        .sentBy(senderId)
                        .type(MessageType.valueOf(request.getMessageType()))
                        .value(encryptionService.encrypt(request.getMessage()))
                .build());

        String channel = generatePrivateConversationChannelPath(receiverId, conversationId);

        pusher.trigger(channel, MESSAGE, request);
        log.info("Message sent to channel: {}", channel);
    }

    private String generatePrivateConversationChannelPath(Integer receiver, Integer conversationId) {
        return String.join("_", USER, receiver.toString(), CONVERSATION, conversationId.toString());
    }

    private void savePrivateConversationParticipant(Integer userId, Conversation conversation) {
        conversationParticipantRepository.save(ConversationParticipant.builder()
                .userId(userId)
                .conversation(conversation)
                .build());
    }
}
