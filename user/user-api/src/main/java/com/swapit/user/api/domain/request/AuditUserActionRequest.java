package com.swapit.user.api.domain.request;

import com.swapit.user.api.dto.userActions.*;
import com.swapit.user.api.util.ActionType;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class AuditUserActionRequest {
    private ActionType actionType;
    private Integer userId;
    private AddProductActionDTO addProductAction;
    private AdminBanUserActionDTO adminBanUserAction;
    private AdminDeleteProductDTO adminDeleteProduct;
    private AdminRemoveUserBanDTO adminRemoveUserBan;

    private UpdateAddProductActionDTO updateAddProductAction;
}
