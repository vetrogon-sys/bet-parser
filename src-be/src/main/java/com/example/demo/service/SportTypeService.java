package com.example.demo.service;

import com.example.demo.entity.SportType;

public interface SportTypeService {

    SportType saveIfNotExist(SportType sportType);

}
