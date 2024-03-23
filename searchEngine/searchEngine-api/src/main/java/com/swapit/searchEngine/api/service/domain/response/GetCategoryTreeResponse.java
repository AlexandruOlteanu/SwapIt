package com.swapit.searchEngine.api.service.domain.response;

import com.swapit.searchEngine.api.service.dto.CategoryTreeValueDTO;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class GetCategoryTreeResponse {
    List<CategoryTreeValueDTO> parentCategories;
    List<CategoryTreeValueDTO> childCategories;
}
