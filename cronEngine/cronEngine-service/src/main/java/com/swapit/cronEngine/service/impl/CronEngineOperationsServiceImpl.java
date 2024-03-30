package com.swapit.cronEngine.service.impl;

import com.swapit.cronEngine.domain.RegistrationCode;
import com.swapit.cronEngine.repository.RegistrationCodeRepository;
import com.swapit.cronEngine.service.CronEngineOperationsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronEngineOperationsServiceImpl implements CronEngineOperationsService {

    @Value("${cron.registration.codes.maxTime.seconds}")
    private Integer maxSeconds;

    private final RegistrationCodeRepository registrationCodeRepository;

    @Override
    @Transactional
    @Scheduled(cron = "${cron.registration.codes.expire.scheduler}")
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
}
