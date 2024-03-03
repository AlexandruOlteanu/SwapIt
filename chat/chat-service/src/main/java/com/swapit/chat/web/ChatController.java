package com.swapit.chat.web;

import com.swapit.chat.api.domain.request.PrivateChatMessage;
import com.swapit.chat.api.service.ChatService;
import com.swapit.chat.service.MessageSendingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController implements ChatService {

    private final MessageSendingService messageSendingService;

    @Override
    public void sendPrivateMessage(PrivateChatMessage request) throws Exception {
        messageSendingService.sendPrivateMessage(request);
    }
}
