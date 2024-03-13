package com.swapit.chat.api.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Jacksonized
@Builder
public class ConversationPreviewDTO {
    private String lastMessageSent;
    private Boolean sentByUser;
    private List<Integer> otherParticipantsIds;
    private String conversationTitle;
    private ZonedDateTime lastMessageSentAt;
    private Integer conversationId;
}
