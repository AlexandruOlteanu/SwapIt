package com.swapit.product.api.domain.request;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class GetProductsByIdsRequest {
    List<Integer> productIds;
}
