package com.swapit.chat.service.impl;

import com.pusher.rest.Pusher;
import com.swapit.chat.api.domain.request.PrivateChatMessage;
import com.swapit.chat.service.MessageSendingService;
import com.swapit.commons.domain.Conversation;
import com.swapit.commons.domain.ConversationParticipants;
import com.swapit.commons.domain.Message;
import com.swapit.commons.repository.ConversationParticipantsRepository;
import com.swapit.commons.repository.ConversationRepository;
import com.swapit.commons.repository.MessageRepository;
import com.swapit.commons.repository.UserRepository;
import com.swapit.commons.util.ConversationType;
import com.swapit.commons.util.MessageType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.time.ZonedDateTime;

import static com.swapit.chat.utils.Constants.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class MessageSendingServiceImpl implements MessageSendingService {

    @Qualifier("pusherBean")
    private final Pusher pusher;
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;
    private final ConversationParticipantsRepository conversationParticipantsRepository;
    private final MessageRepository messageRepository;
    @Override
    @Transactional
    public void sendPrivateMessage(PrivateChatMessage request) throws Exception {
        var senderId = userRepository.getUserIdByUsername(request.getSenderUsername());
        var receiverId = userRepository.getUserIdByUsername(request.getReceiverUsername());
        if (senderId.isEmpty() || receiverId.isEmpty()) {
            throw new Exception("Error on processing message!");
        }

        Integer conversationId = request.getConversationId();
        Conversation conversation;
        ZonedDateTime updatedLastAction = ZonedDateTime.now();
        if (conversationId == null) {
            conversationId = conversationRepository.findPrivateConversationId(senderId.get(), receiverId.get()).orElse(null);
        }
        if (conversationId == null) {
            conversation = conversationRepository.save(Conversation.builder()
                            .lastActionAt(updatedLastAction)
                            .type(ConversationType.PRIVATE)
                    .build());
            savePrivateConversationParticipant(senderId.get(), conversation);
            savePrivateConversationParticipant(receiverId.get(), conversation);
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
                        .sentBy(senderId.get())
                        .type(MessageType.valueOf(request.getMessageType()))
                        .value(request.getMessage())
                .build());

        String channel = generatePrivateConversationChannelPath(receiverId.get(), conversationId);

        pusher.trigger(channel, MESSAGE, request);
        log.info("Message sent to channel: " + channel);

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
