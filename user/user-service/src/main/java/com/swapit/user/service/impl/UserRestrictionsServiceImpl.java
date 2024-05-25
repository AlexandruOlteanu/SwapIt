package com.swapit.user.service.impl;

import com.swapit.commons.exception.ExceptionFactory;
import com.swapit.commons.exception.ExceptionType;
import com.swapit.user.api.domain.response.GetUserAccountStatusResponse;
import com.swapit.user.domain.User;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.service.UserRestrictionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZonedDateTime;

import static com.swapit.user.api.util.UserStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRestrictionsServiceImpl implements UserRestrictionsService {

    private final UserRepository userRepository;
    private final ExceptionFactory exceptionFactory;

    @Override
    public void banUser(Integer userId, Integer banDaysDuration) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.USER_NOT_FOUND));
        ZonedDateTime startOfNextDay = ZonedDateTime.now()
                .with(LocalTime.MIDNIGHT)
                .plusDays(1);
        if (banDaysDuration != null) {
            user.setStatus(TEMPORARY_BANNED);
            ZonedDateTime banExpiry = startOfNextDay.plusDays(banDaysDuration);
            user.setBanExpiryTime(banExpiry);
        } else {
            user.setStatus(PERMANENT_BANNED);
            user.setBanExpiryTime(null);
        }
        userRepository.save(user);
    }

    @Override
    public GetUserAccountStatusResponse getUserAccountStatus(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.USER_NOT_FOUND));
        return GetUserAccountStatusResponse.builder()
                .userStatus(user.getStatus())
                .banExpiryTime(user.getBanExpiryTime())
                .build();
    }

    @Override
    public void removeUserBan(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.USER_NOT_FOUND));
        user.setStatus(ACTIVE);
        user.setBanExpiryTime(null);
        userRepository.save(user);
    }
}
