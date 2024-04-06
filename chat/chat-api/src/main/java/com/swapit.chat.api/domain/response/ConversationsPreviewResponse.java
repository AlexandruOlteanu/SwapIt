package com.swapit.chat.api.domain.response;

import com.swapit.chat.api.domain.dto.ConversationPreviewDTO;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class ConversationsPreviewResponse {
    List<ConversationPreviewDTO> conversationsPreview;
}
