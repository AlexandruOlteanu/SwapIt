package com.swapit.user.service.impl;

import com.swapit.commons.exception.ExceptionFactory;
import com.swapit.commons.exception.ExceptionType;
import com.swapit.commons.generator.RandomCodeGenerator;
import com.swapit.user.api.domain.request.SendSecurityCodeRequest;
import com.swapit.user.domain.SecurityCode;
import com.swapit.user.repository.SecurityCodeRepository;
import com.swapit.user.service.EmailSenderService;
import com.swapit.user.service.SecurityCodeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SecurityCodeServiceImpl implements SecurityCodeService {

    @Value("${security.code.length}")
    private Integer securityCodeLength;

    private final EmailSenderService emailSenderService;
    private final RandomCodeGenerator randomCodeGenerator;
    private final SecurityCodeRepository securityCodeRepository;
    private final ExceptionFactory exceptionFactory;

    @Override
    @Transactional
    public void sendSecurityCode(SendSecurityCodeRequest request) {
        String subject, message;
        String code = randomCodeGenerator.generateRandomCode(securityCodeLength);
        switch (request.getSecurityCodeType()) {
            case REGISTRATION: {
                subject = "SwapIt Registration Code";
                message = "Code: " + code + "\nDon't share this code with anyone!";
                break;
            }
            case FORGOTTEN_PASSWORD_RESET: {
                subject = "SwapIt Password Reset Code";
                message = "Code: " + code + "\nDon't share this code with anyone!";
                break;
            }
            case EMAIL_RESET: {
                subject = "SwapIt Email Change Code";
                message = "Code: " + code + "\nDon't share this code with anyone!";
                break;
            }
            default: throw exceptionFactory.create(ExceptionType.UNAUTHORIZED_ACTION);
        }
        emailSenderService.sendSimpleEmail(request.getEmail(), subject, message);
        securityCodeRepository.deleteByEmailAndCodeType(request.getEmail(), request.getSecurityCodeType());
        securityCodeRepository.save(SecurityCode.builder()
                .email(request.getEmail())
                .code(code)
                .codeType(request.getSecurityCodeType())
                .build());
    }
}
