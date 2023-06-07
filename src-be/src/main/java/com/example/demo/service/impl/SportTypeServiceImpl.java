package com.example.demo.service.impl;

import com.example.demo.entity.SportType;
import com.example.demo.repository.SportTypeRepository;
import com.example.demo.service.SportTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SportTypeServiceImpl implements SportTypeService {

    private final SportTypeRepository sportTypeRepository;

    @Override
    public SportType saveIfNotExist(SportType sportType) {
        return sportTypeRepository.findById(sportType.getType())
              .orElse(sportTypeRepository.save(sportType));
    }
}
