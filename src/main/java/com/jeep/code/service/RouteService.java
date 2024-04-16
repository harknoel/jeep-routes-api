package com.jeep.code.service;

import com.jeep.code.model.JeepCode;
import com.jeep.code.model.Place;
import com.jeep.code.payload.RouteRequestDTO;
import com.jeep.code.repository.JeepCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final JeepCodeRepository jeepCodeRepository;

    public String getRoutes(RouteRequestDTO routeRequestDTO) {
        List<String> codes = routeRequestDTO.getCodes();
        StringBuilder routes = new StringBuilder();

        for (String code : codes) {
            Optional<JeepCode> jeepCodeOptional = jeepCodeRepository.findByCode(code);
            if (jeepCodeOptional.isPresent()) {
                JeepCode jeepCode = jeepCodeOptional.get();
                routes.append(getRouteString(jeepCode)).append("\n");
            } else {
                routes.append("Invalid code: ").append(code).append("\n");
            }
        }

        return routes.toString();
    }

    private String getRouteString(JeepCode jeepCode) {
        List<Place> places = jeepCode.getPlaces();
        if (places == null || places.isEmpty()) {
            return "No route available for code " + jeepCode.getCode();
        }

        // Sort places by placeId in ascending order
        places.sort(Comparator.comparingInt(Place::getPlaceId));

        return jeepCode.getCode() + " => " + places.stream()
                .map(Place::getPlaceName)
                .collect(Collectors.joining(" <-> "));
    }
}
