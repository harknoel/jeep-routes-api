package com.jeep.code.controller;

import com.jeep.code.model.JeepCode;
import com.jeep.code.payload.JeepCodeAddDTO;
import com.jeep.code.payload.JeepCodeUpdateDTO;
import com.jeep.code.service.JeepCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/codes")
public class JeepCodeController {
    private final JeepCodeService jeepCodeService;

    public JeepCodeController(JeepCodeService jeepCodeService) {
        this.jeepCodeService = jeepCodeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllJeepCodes() {
        List<String> allJeepCodes = jeepCodeService.getAllJeepCodes();
        if (allJeepCodes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(allJeepCodes);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCode(@RequestBody JeepCodeAddDTO jeepCodeAddDTO) {
        boolean added = jeepCodeService.addCode(jeepCodeAddDTO.getCode());
        if (added) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Code added successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding code.");
        }
    }

    @DeleteMapping("/remove/{name}")
    public ResponseEntity<String> removeCode(@PathVariable String name) {
        boolean removed = jeepCodeService.removeCodeByName(name);
        if (removed) {
            return ResponseEntity.ok("Code removed successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCode(@RequestBody JeepCodeUpdateDTO jeepCodeUpdateDTO) {
        boolean updated = jeepCodeService.updateCodeByName(jeepCodeUpdateDTO.getCode(), jeepCodeUpdateDTO.getNewCode());
        if (updated) {
            return ResponseEntity.ok("Code updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
