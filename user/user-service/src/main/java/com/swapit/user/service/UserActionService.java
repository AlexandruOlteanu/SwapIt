package com.swapit.user.service;

import com.swapit.user.api.domain.request.PostUserActionRequest;
import com.swapit.user.api.domain.response.GetUserActionsResponse;

public interface UserActionService {
    void postUserAction(PostUserActionRequest request);
    GetUserActionsResponse getUserActions(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria);
}
