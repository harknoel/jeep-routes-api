package com.jeep.code.controller;

import com.jeep.code.payload.RouteRequestDTO;
import com.jeep.code.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/routes")
public class RouteController {
    @Autowired
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/get")
    public String getRoute(@RequestBody RouteRequestDTO routeRequestDTO) {
        return routeService.getRoutes(routeRequestDTO);
    }
}
