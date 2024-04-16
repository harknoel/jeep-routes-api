package com.jeep.code.controller;

import com.jeep.code.payload.PlaceAddDTO;
import com.jeep.code.payload.PlaceRemoveDTO;
import com.jeep.code.payload.PlaceUpdateDTO;
import com.jeep.code.service.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/places")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllPlaces() {
        List<String> allJeepCodes = placeService.getAllPlaces();
        if (allJeepCodes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(allJeepCodes);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPlaceByCode(@RequestBody PlaceAddDTO placeAddDTO) {
        boolean added = placeService.addPlaceByCode(placeAddDTO.getCode(), placeAddDTO.getName());
        if (added) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Place added successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding place.");
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removePlaceByCode(@RequestBody PlaceRemoveDTO placeRemoveDTO) {
        boolean removed = placeService.removePlaceByCode(placeRemoveDTO.getCode(), placeRemoveDTO.getName());
        if (removed) {
            return ResponseEntity.ok("Place removed successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePlaceByCode(@RequestBody PlaceUpdateDTO placeUpdateDTO) {
        boolean updated = placeService.updatePlaceByCode(placeUpdateDTO.getCode(), placeUpdateDTO.getName(), placeUpdateDTO.getNewName());
        if (updated) {
            return ResponseEntity.ok("Place updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
