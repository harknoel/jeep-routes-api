package com.jeep.code.repository;

import com.jeep.code.model.JeepCode;
import com.jeep.code.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    Optional<Place> findByPlaceNameAndJeepCode(String placeName, JeepCode jeepCode);
}
