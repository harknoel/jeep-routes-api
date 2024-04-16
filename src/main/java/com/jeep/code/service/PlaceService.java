package com.jeep.code.service;

import com.jeep.code.model.JeepCode;
import com.jeep.code.model.Place;
import com.jeep.code.repository.JeepCodeRepository;
import com.jeep.code.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final JeepCodeRepository jeepCodeRepository;
    private final PlaceRepository placeRepository;

    public boolean addPlaceByCode(String code, String name) {
        Optional<JeepCode> optionalJeepCode = jeepCodeRepository.findByCode(code);
        if (optionalJeepCode.isPresent()) {
            JeepCode jeepCode = optionalJeepCode.get();
            var place = Place.builder()
                    .placeName(name)
                    .jeepCode(jeepCode)
                    .build();
            placeRepository.save(place);
            return true;
        }
        return false;
    }

    public boolean removePlaceByCode(String code, String placeName) {
        Optional<JeepCode> optionalJeepCode = jeepCodeRepository.findByCode(code);
        if (optionalJeepCode.isPresent()) {
            JeepCode jeepCode = optionalJeepCode.get();
            Optional<Place> optionalPlace = placeRepository.findByPlaceNameAndJeepCode(placeName, jeepCode);
            if (optionalPlace.isPresent()) {
                placeRepository.delete(optionalPlace.get());
                return true;
            }
        }
        return false;
    }

    public boolean updatePlaceByCode(String code, String name, String newName) {
        Optional<JeepCode> optionalJeepCode = jeepCodeRepository.findByCode(code);
        if (optionalJeepCode.isPresent()) {
            JeepCode jeepCode = optionalJeepCode.get();
            Optional<Place> optionalPlace = placeRepository.findByPlaceNameAndJeepCode(name, jeepCode);
            if (optionalPlace.isPresent()) {
                Place place = optionalPlace.get();
                place.setPlaceName(newName);
                placeRepository.save(place);
                return true;
            }
        }
        return false;
    }
}
