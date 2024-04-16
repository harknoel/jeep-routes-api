package com.jeep.code.repository;

import com.jeep.code.model.JeepCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JeepCodeRepository extends JpaRepository<JeepCode, Integer> {
    Optional<JeepCode> findByCode(String code);

    @Query("SELECT j.code FROM JeepCode j")
    List<String> findAllCodes();

}
