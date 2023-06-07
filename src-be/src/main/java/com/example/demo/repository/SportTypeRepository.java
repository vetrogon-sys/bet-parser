package com.example.demo.repository;

import com.example.demo.entity.SportType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportTypeRepository extends JpaRepository<SportType, String> {
}
