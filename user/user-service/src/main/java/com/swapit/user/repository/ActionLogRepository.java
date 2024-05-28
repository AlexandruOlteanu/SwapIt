package com.swapit.user.repository;

import com.swapit.user.api.util.ActionType;
import com.swapit.user.domain.ActionLog;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ActionLogRepository extends JpaRepository<ActionLog, Integer> {
    @NotNull
    Page<ActionLog> findAll(@NotNull Pageable pageable);
    Page<ActionLog> findAllByActionTypeIn(Collection<ActionType> actionType, Pageable pageable);
}
