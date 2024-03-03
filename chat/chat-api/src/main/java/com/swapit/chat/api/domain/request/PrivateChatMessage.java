package com.swapit.chat.api.domain.request;


import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class PrivateChatMessage {
    private Integer conversationId;
    private String senderUsername;
    private String receiverUsername;
    private String message;
    private String messageType;
}
