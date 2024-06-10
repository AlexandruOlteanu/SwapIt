package com.swapit.user.api.dto.userActions;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class UpdateAddProductActionDTO {
    private Integer productId;
    private String productTitle;
}
