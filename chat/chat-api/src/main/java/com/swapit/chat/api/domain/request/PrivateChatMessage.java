package com.swapit.chat.api.domain.request;


import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class PrivateChatMessage {
    private Integer conversationId;
    private Integer senderId;
    private Integer receiverId;
    private String message;
    private String messageType;
}
