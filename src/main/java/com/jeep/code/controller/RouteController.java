package com.jeep.code.controller;

import com.jeep.code.payload.JeepCodeUpdateDTO;
import com.jeep.code.payload.RouteRequestDTO;
import com.jeep.code.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/routes")
public class RouteController {
    @Autowired
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllRoutes() {
        List<String> routes = routeService.getAllRoutes();
        if (routes != null && !routes.isEmpty()) {
            return ResponseEntity.ok(routes);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList("Error occurred while fetching routes."));
        }
    }


    @GetMapping("/get")
    public ResponseEntity<String> getRoutes(@RequestBody RouteRequestDTO routeRequestDTO) {
        String response = routeService.getRoutes(routeRequestDTO);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching routes.");
        }
    }
}
