package com.swapit.user.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.swapit.user.api.domain.request.AuditUserActionRequest;
import com.swapit.user.api.domain.response.GetUserActionsResponse;
import com.swapit.user.api.dto.ActionLogDTO;
import com.swapit.user.api.util.ActionType;
import com.swapit.user.domain.ActionLog;
import com.swapit.user.repository.ActionLogRepository;
import com.swapit.user.service.UserActionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.swapit.user.utils.UserActionsSearchCriteria.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserActionServiceImpl implements UserActionService {

    private final ActionLogRepository actionLogRepository;
    private final ObjectMapper objectMapper;
    private final String NEWEST_FIELD = "createdAt";

    private final String PRODUCT_ID = "productId";
    private final String BANNED_USER_ID = "bannedUserId";
    private final String BAN_DURATION_DAYS = "banDurationDays";
    private final String UNBANNED_USER_ID = "unbannedUserId";
    private final String PRODUCT_TITLE = "productTitle";

    @Override
    public void auditUserAction(AuditUserActionRequest request) {
        Map<String, Object> actionDetailsMap = new HashMap<>();
        if (request.getActionType().equals(ActionType.USER_ADD_PRODUCT)) {
            actionDetailsMap.put(PRODUCT_ID, request.getAddProductAction().getProductId());
            actionDetailsMap.put(PRODUCT_TITLE, request.getAddProductAction().getProductTitle());
            JsonNode actionDetailsNode = objectMapper.valueToTree(actionDetailsMap);
            ActionLog actionLog = ActionLog.builder()
                    .userId(request.getUserId())
                    .actionType(ActionType.USER_ADD_PRODUCT)
                    .actionDetails(actionDetailsNode)
                    .build();
            actionLogRepository.save(actionLog);
        }
        if (request.getActionType().equals(ActionType.USER_REGISTER)) {
            ActionLog actionLog = ActionLog.builder()
                    .userId(request.getUserId())
                    .actionType(ActionType.USER_REGISTER)
                    .actionDetails(null)
                    .build();
            actionLogRepository.save(actionLog);
        }
        if (request.getActionType().equals(ActionType.ADMIN_BAN_USER)) {
            actionDetailsMap.put(BANNED_USER_ID, request.getAdminBanUserAction().getBannedUserId());
            actionDetailsMap.put(BAN_DURATION_DAYS, request.getAdminBanUserAction().getBanDurationInDays());
            JsonNode actionDetailsNode = objectMapper.valueToTree(actionDetailsMap);
            ActionLog actionLog = ActionLog.builder()
                    .userId(request.getUserId())
                    .actionType(ActionType.ADMIN_BAN_USER)
                    .actionDetails(actionDetailsNode)
                    .build();
            actionLogRepository.save(actionLog);
        }
        if (request.getActionType().equals(ActionType.ADMIN_REMOVE_USER_BAN)) {
            actionDetailsMap.put(UNBANNED_USER_ID, request.getAdminRemoveUserBan().getUnbannedUserId());
            JsonNode actionDetailsNode = objectMapper.valueToTree(actionDetailsMap);
            ActionLog actionLog = ActionLog.builder()
                    .userId(request.getUserId())
                    .actionType(ActionType.ADMIN_REMOVE_USER_BAN)
                    .actionDetails(actionDetailsNode)
                    .build();
            actionLogRepository.save(actionLog);
        }
        if (request.getActionType().equals(ActionType.ADMIN_DELETE_PRODUCT)) {
            actionDetailsMap.put(PRODUCT_TITLE, request.getAdminDeleteProduct().getProductTitle());
            JsonNode actionDetailsNode = objectMapper.valueToTree(actionDetailsMap);
            ActionLog actionLog = ActionLog.builder()
                    .userId(request.getUserId())
                    .actionType(ActionType.ADMIN_DELETE_PRODUCT)
                    .actionDetails(actionDetailsNode)
                    .build();
            actionLogRepository.save(actionLog);
        }

        if (request.getActionType().equals(ActionType.UPDATE_USER_ADD_PRODUCT)) {

            List<ActionLog> actionLogs = actionLogRepository.findActionLogByUserIdAndActionType(request.getUserId(), ActionType.USER_ADD_PRODUCT)
                    .orElse(new ArrayList<>());
            ActionLog actionLog = actionLogs.stream()
                    .filter(actionLog1 -> request.getUpdateAddProductAction().getProductId().equals(actionLog1.getActionDetails().get(PRODUCT_ID).asInt()))
                    .findFirst().orElse(null);
            if (actionLog != null) {
                ((ObjectNode) actionLog.getActionDetails()).put(PRODUCT_TITLE, request.getUpdateAddProductAction().getProductTitle());
                actionLogRepository.save(actionLog);
            }
        }

    }

    @Override
    public GetUserActionsResponse getUserActions(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        if (sortCriteria == null || sortCriteria.isEmpty()) {
            sortCriteria = INCLUDE_ALL.name();
        }

        Pageable pageable = PageRequest.of(chunkNumber, nrElementsPerChunk, Sort.by(NEWEST_FIELD).descending());;
        Page<ActionLog> data = null;
        if (sortCriteria.equalsIgnoreCase(INCLUDE_ALL.name())) {
            data = actionLogRepository.findAll(pageable);
        }
        if (sortCriteria.equalsIgnoreCase(ONLY_USER_ACTIONS.name())) {
            data = actionLogRepository.findAllByActionTypeIn(
                    List.of(ActionType.USER_ADD_PRODUCT, ActionType.USER_REGISTER), pageable);
        }
        if (sortCriteria.equalsIgnoreCase(ONLY_ADMIN_ACTIONS.name())) {
            data = actionLogRepository.findAllByActionTypeIn(
                    List.of(ActionType.ADMIN_BAN_USER, ActionType.ADMIN_REMOVE_USER_BAN, ActionType.ADMIN_DELETE_PRODUCT),
                    pageable);
        }
        assert data != null;
        List<ActionLog> actionLogs = data.getContent();
        List<ActionLogDTO> actionLogDTOS = actionLogs.stream()
                .map(actionLog -> ActionLogDTO.builder()
                        .actionId(actionLog.getId())
                        .actionType(actionLog.getActionType())
                        .userId(actionLog.getUserId())
                        .createdAt(actionLog.getCreatedAt())
                        .actionDetails(actionLog.getActionDetails())
                        .build())
                .toList();
        return GetUserActionsResponse.builder()
                .userActions(actionLogDTOS)
                .currentPage(data.getNumber())
                .totalPages(data.getTotalPages())
                .totalItems(data.getNumberOfElements())
                .itemsPerPage(data.getSize())
                .hasNextPage(data.hasNext())
                .hasPreviousPage(data.hasPrevious())
                .build();
    }
}
