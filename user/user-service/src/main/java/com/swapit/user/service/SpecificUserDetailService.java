package com.swapit.user.service;

import com.swapit.user.api.domain.request.SpecificUserDetailRequest;

public interface SpecificUserDetailService {
    Object getSpecificUserDetail(SpecificUserDetailRequest request) throws Exception;
}
