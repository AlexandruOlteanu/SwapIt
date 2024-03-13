package com.swapit.chat.web;

import com.swapit.chat.api.domain.request.PrivateChatMessageRequest;
import com.swapit.chat.api.domain.response.ConversationResponse;
import com.swapit.chat.api.domain.response.ConversationsPreviewResponse;
import com.swapit.chat.api.service.ChatService;
import com.swapit.chat.service.GetConversationService;
import com.swapit.chat.service.MessageSendingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController implements ChatService {

    private final MessageSendingService messageSendingService;
    private final GetConversationService getConversationService;

    @Override
    public void sendPrivateMessage(PrivateChatMessageRequest request) {
        messageSendingService.sendPrivateMessage(request);
    }
    @Override
    public ResponseEntity<ConversationsPreviewResponse> getConversationsPreview(Integer userId) {
        return ResponseEntity.ok(getConversationService.getConversationsPreview(userId));
    }

    @Override
    public ResponseEntity<ConversationResponse> getConversation(Integer conversationId) throws Exception {
        return ResponseEntity.ok(getConversationService.getConversation(conversationId));
    }
}
