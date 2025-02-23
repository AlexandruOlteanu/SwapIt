package com.swapit.chat.api.domain.request;


import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class PrivateChatMessageRequest {
    private Integer receiverId;
    private String message;
    private String messageType;
}
