package com.jeep.code.service;

import com.jeep.code.model.JeepCode;
import com.jeep.code.model.Place;
import com.jeep.code.repository.JeepCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final JeepCodeRepository jeepCodeRepository;

    public String getRoute(String code) {
        Optional<JeepCode> jeepCode = jeepCodeRepository.findByCode(code);
        if (jeepCode.isEmpty()) {
            return "Invalid code";
        }

        List<Place> places = jeepCode.get().getPlaces();
        if (places == null || places.isEmpty()) {
            return "No route available for this code";
        }

        return jeepCode.get().getCode() + " => " + places.stream()
                .map(Place::getPlaceName)
                .collect(Collectors.joining(" <-> "));
    }
}
