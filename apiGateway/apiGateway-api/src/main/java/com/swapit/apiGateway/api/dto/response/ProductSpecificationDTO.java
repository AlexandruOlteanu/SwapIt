package com.swapit.apiGateway.api.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class ProductSpecificationDTO {
    private Integer specificationId;
    private String key;
    private String value;
}
