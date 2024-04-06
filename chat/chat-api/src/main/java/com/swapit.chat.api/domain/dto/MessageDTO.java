package com.swapit.chat.api.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

@Data
@Jacksonized
@Builder
public class MessageDTO {
    private String value;
    private String type;
    private Integer sentBy;
    private ZonedDateTime sentAt;
}
