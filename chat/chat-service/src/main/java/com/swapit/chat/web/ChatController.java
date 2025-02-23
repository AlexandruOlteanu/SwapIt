package com.swapit.chat.web;

import com.swapit.chat.api.domain.request.PrivateChatMessageRequest;
import com.swapit.chat.api.domain.response.ConversationResponse;
import com.swapit.chat.api.domain.response.ConversationsPreviewResponse;
import com.swapit.chat.api.service.ChatService;
import com.swapit.chat.service.ConversationPreviewService;
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
    private final ConversationPreviewService conversationPreviewService;

    @Override
    public void sendPrivateMessage(Integer userId, PrivateChatMessageRequest request) throws Exception {
        messageSendingService.sendPrivateMessage(userId, request);
    }

    @Override
    public ResponseEntity<ConversationsPreviewResponse> getConversationsPreview(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        return ResponseEntity.ok(conversationPreviewService.getConversationsPreview(userId, chunkNumber, nrElementsPerChunk, sortCriteria));
    }


    @Override
    public ResponseEntity<ConversationResponse> getConversation(Integer userId, Integer conversationId) {
        return ResponseEntity.ok(getConversationService.getConversation(userId, conversationId));
    }
}
