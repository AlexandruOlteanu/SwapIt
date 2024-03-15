package com.swapit.user.api.domain.request;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Data
@Jacksonized
@Builder
public class UpdateBasicUserDetailsRequest {
    private Integer userId;
    Map<String, String> userDetails;
}
