package com.swapit.user.api.domain.response;

import com.swapit.product.api.domain.dto.ProductDTO;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Jacksonized
@Builder
public class UserDetailsResponse {
    private String username;
    private String name;
    private String surname;
    private String email;
    private ZonedDateTime joinDate;
    private List<ProductDTO> products;
}
