package com.jeep.code.repository;

import com.jeep.code.model.JeepCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JeepCodeRepository extends JpaRepository<JeepCode, Integer> {
}
