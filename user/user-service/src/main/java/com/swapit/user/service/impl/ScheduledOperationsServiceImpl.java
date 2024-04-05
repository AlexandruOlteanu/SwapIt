package com.swapit.user.service.impl;

import com.swapit.commons.exception.ExceptionFactory;
import com.swapit.commons.exception.ExceptionType;
import com.swapit.user.domain.RegistrationCode;
import com.swapit.user.domain.User;
import com.swapit.user.repository.RegistrationCodeRepository;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.service.ScheduledOperationsService;
import com.swapit.user.utils.UserStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledOperationsServiceImpl implements ScheduledOperationsService {
    @Value("${user.cron.registration.codes.maxTime.seconds}")
    private Integer maxSeconds;

    private final RegistrationCodeRepository registrationCodeRepository;
    private final UserRepository userRepository;
    private final ExceptionFactory exceptionFactory;

    @Override
    @Transactional
    @Scheduled(cron = "${user.cron.registration.codes.expire.scheduler}", zone = "UTC")
    @Retryable(retryFor = {Exception.class}, maxAttempts = 10, backoff = @Backoff(delay = 10000))
    public void registrationCodesExpire() {
        log.info("Starting Registration Codes Expire Cron");
        List<RegistrationCode> registrationCodes = registrationCodeRepository.findAll();
        ZonedDateTime now = ZonedDateTime.now();
        registrationCodes.forEach(registrationCode -> {
            ZonedDateTime createdAt = registrationCode.getCreatedAt();

            Duration duration = Duration.between(createdAt, now);
            if (duration.toSeconds() > maxSeconds) {
                registrationCodeRepository.deleteById(registrationCode.getId());
            }
        });
        log.info("Successfully Finished Registration Codes Expire Cron");
    }

    @Override
    @Transactional
    @Scheduled(cron = "${user.registration.codes.expire.scheduler}", zone = "UTC")
    @Retryable(retryFor = {Exception.class}, maxAttempts = 10, backoff = @Backoff(delay = 30000))
    public void removeUsersBan() {
        log.info("Starting Removing Users Ban Cron");
        List<User> inactiveUsers = userRepository.findAllByStatus(UserStatus.INACTIVE)
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.USER_NOT_FOUND));
        inactiveUsers.forEach(inactiveUser -> {
            if (inactiveUser.getBanExpiryTime() != null && inactiveUser.getBanExpiryTime().isBefore(ZonedDateTime.now())) {
                inactiveUser.setStatus(UserStatus.ACTIVE);
                inactiveUser.setBanExpiryTime(null);
            }
        });
        log.info("Successfully Finished Removing Users Ban Cron");
    }
}
