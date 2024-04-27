package com.swapit.user.repository;

import com.swapit.user.domain.SecurityCode;
import com.swapit.user.utils.SecurityCodeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecurityCodeRepository extends JpaRepository<SecurityCode, Integer> {
    Optional<SecurityCode> findByEmailAndCodeAndCodeType(String email, String code, SecurityCodeType codeType);
    void deleteByEmailAndCodeAndCodeType(String email, String code, SecurityCodeType codeType);
    void deleteByEmailAndCodeType(String email, SecurityCodeType codeType);
}
