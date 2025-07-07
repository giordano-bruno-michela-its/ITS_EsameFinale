package com.giordanobrunomichela.final_test.repository;

import com.giordanobrunomichela.final_test.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
}
