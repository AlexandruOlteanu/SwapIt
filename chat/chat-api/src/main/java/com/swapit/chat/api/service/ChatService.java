package com.swapit.chat.api.service;

import com.swapit.chat.api.domain.request.PrivateChatMessage;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated
public interface ChatService {

    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String BASE_URL = "/api/v1/swapIt/chat/";
    String SEND = "sendPrivateMessage";

    @PostMapping(value = BASE_URL + SEND, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void sendPrivateMessage(@Valid @RequestBody PrivateChatMessage request);

}
