package com.swapit.user.service;

import com.swapit.user.api.domain.request.AuditUserActionRequest;
import com.swapit.user.api.domain.response.GetUserActionsResponse;

public interface UserActionService {
    void auditUserAction(AuditUserActionRequest request);
    GetUserActionsResponse getUserActions(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria);
}
