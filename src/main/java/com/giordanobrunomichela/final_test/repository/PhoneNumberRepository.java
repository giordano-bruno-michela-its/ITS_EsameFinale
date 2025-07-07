package com.giordanobrunomichela.final_test.repository;

import com.giordanobrunomichela.final_test.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
    Optional<PhoneNumber> findByPhoneNumber(String phoneNumber);
}
