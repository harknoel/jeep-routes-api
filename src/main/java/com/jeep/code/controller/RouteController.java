package com.jeep.code.controller;

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
}
