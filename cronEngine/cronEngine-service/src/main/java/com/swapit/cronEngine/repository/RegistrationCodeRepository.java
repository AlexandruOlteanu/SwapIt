package com.swapit.cronEngine.repository;


import com.swapit.cronEngine.domain.RegistrationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationCodeRepository extends JpaRepository<RegistrationCode, Integer> {
    Optional<RegistrationCode> findByEmailAndCode(String email, String code);
    void deleteByEmailAndCode(String email, String code);
    void deleteByEmail(String email);
}
