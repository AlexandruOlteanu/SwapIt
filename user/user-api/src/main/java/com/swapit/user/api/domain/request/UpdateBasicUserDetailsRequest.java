package com.swapit.user.api.domain.request;

import com.swapit.user.api.util.UserBasicDetailType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Data
@Jacksonized
@Builder
public class UpdateBasicUserDetailsRequest {
    @NotNull
    Map<UserBasicDetailType, String> userDetails;
}
