package com.swapit.user.api.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.swapit.user.api.util.ActionType;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

@Data
@Jacksonized
@Builder
public class ActionLogDTO {
    private Integer actionId;
    private Integer userId;
    private ActionType actionType;
    private ZonedDateTime createdAt;
    private JsonNode actionDetails;
}
