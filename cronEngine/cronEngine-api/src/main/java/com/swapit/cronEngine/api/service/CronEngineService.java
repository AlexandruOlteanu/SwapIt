package com.swapit.cronEngine.api.service;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public interface CronEngineService {

    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String BASE_URL = "/api/v1/swapIt/cronEngine/";
    String MANUAL_REGISTRATION_CODES_EXPIRE = "manualRegistrationCodesExpire";

    @DeleteMapping(value = BASE_URL + MANUAL_REGISTRATION_CODES_EXPIRE)
    void manualRegistrationCodesExpire();

}
