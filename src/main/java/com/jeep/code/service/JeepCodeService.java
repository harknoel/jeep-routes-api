package com.jeep.code.service;

import com.jeep.code.model.JeepCode;
import com.jeep.code.repository.JeepCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JeepCodeService {

    private final JeepCodeRepository jeepCodeRepository;

    public boolean removeCodeByName(String name) {
        Optional<JeepCode> jeepCodeOptional = jeepCodeRepository.findByCode(name);
        if (jeepCodeOptional.isPresent()) {
            JeepCode jeepCode = jeepCodeOptional.get();
            jeepCodeRepository.delete(jeepCode);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateCodeByName(String code, String newCode) {
        Optional<JeepCode> jeepCodeOptional = jeepCodeRepository.findByCode(code);
        if (jeepCodeOptional.isPresent()) {
            JeepCode jeepCode = jeepCodeOptional.get();
            jeepCode.setCode(newCode);
            jeepCodeRepository.save(jeepCode);
            return true;
        } else {
            return false;
        }
    }
}
