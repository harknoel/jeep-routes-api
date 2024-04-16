package com.jeep.code.controller;

import com.jeep.code.payload.JeepCodeUpdateDTO;
import com.jeep.code.service.JeepCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/codes")
public class JeepCodeController {
    private final JeepCodeService jeepCodeService;

    public JeepCodeController(JeepCodeService jeepCodeService) {
        this.jeepCodeService = jeepCodeService;
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
