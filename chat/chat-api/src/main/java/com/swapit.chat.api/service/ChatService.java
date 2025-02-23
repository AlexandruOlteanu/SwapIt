package com.swapit.chat.api.service;

import com.swapit.chat.api.domain.request.PrivateChatMessageRequest;
import com.swapit.chat.api.domain.response.ConversationResponse;
import com.swapit.chat.api.domain.response.ConversationsPreviewResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.swapit.commons.utils.Constants.IntegerMaxValueAsString;


@RestController
@Validated
public interface ChatService {

    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String BASE_URL = "/api/v1/swapIt/chat/";
    String SEND = "sendPrivateMessage";
    String GET_CONVERSATIONS_PREVIEW = "getConversationsPreview";
    String GET_CONVERSATION = "getConversation";

    @PostMapping(value = BASE_URL + SEND, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void sendPrivateMessage(@RequestParam("userId") Integer userId, @Valid @RequestBody PrivateChatMessageRequest request) throws Exception;
    @GetMapping(value = BASE_URL + GET_CONVERSATIONS_PREVIEW)
    ResponseEntity<ConversationsPreviewResponse> getConversationsPreview(@RequestParam(value = "userId") Integer userId,
                                                                         @RequestParam(value = "chunkNumber", defaultValue = "0") Integer chunkNumber,
                                                                         @RequestParam(value = "nrElementsPerChunk", defaultValue = IntegerMaxValueAsString) Integer nrElementsPerChunk,
                                                                         @RequestParam(value = "sortCriteria", required = false) String sortCriteria);
    @GetMapping(value = BASE_URL + GET_CONVERSATION)
    ResponseEntity<ConversationResponse> getConversation(@RequestParam("userId") Integer userId, @RequestParam(value = "conversationId") Integer conversationId);

}
