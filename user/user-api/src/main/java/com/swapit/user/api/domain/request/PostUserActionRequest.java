package com.swapit.user.api.domain.request;

import com.swapit.user.api.dto.userActions.AddProductActionDTO;
import com.swapit.user.api.dto.userActions.AdminBanUserActionDTO;
import com.swapit.user.api.dto.userActions.AdminDeleteProductDTO;
import com.swapit.user.api.dto.userActions.AdminRemoveUserBanDTO;
import com.swapit.user.api.util.ActionType;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class PostUserActionRequest {
    private ActionType actionType;
    private Integer userId;
    private AddProductActionDTO addProductAction;
    private AdminBanUserActionDTO adminBanUserAction;
    private AdminDeleteProductDTO adminDeleteProduct;
    private AdminRemoveUserBanDTO adminRemoveUserBan;
}
