package com.swapit.user.api.domain.response;

import com.swapit.user.api.domain.dto.UserProductDTO;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Jacksonized
@Builder
public class GetUserDetailsResponse {
    private String username;
    private String name;
    private String surname;
    private String email;
    private ZonedDateTime joinDate;
    private List<UserProductDTO> products;
}
