package com.swapit.user.api.domain.response;

import com.swapit.user.api.dto.ActionLogDTO;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class GetUserActionsResponse {
    List<ActionLogDTO> userActions;
    private Integer currentPage;
    private Integer totalPages;
    private Integer totalItems;
    private Integer itemsPerPage;
    private Boolean hasNextPage;
    private Boolean hasPreviousPage;
}
