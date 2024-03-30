package com.swapit.cronEngine.web;

import com.swapit.cronEngine.api.service.CronEngineService;
import com.swapit.cronEngine.service.CronEngineOperationsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CronEngineController implements CronEngineService {

    private final CronEngineOperationsService cronEngineOperationsService;

    @Override
    public void manualRegistrationCodesExpire() {
        cronEngineOperationsService.registrationCodesExpire();
    }
}
