package com.swapit.chat.api.domain.response;

import com.swapit.chat.api.domain.dto.MessageDTO;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class ConversationResponse {
    private String conversationType;
    List<Integer> conversationParticipantsIds;
    List<MessageDTO> messages;
}
