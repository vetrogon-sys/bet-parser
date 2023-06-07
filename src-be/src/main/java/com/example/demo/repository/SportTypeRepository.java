package com.example.demo.repository;

import com.example.demo.entity.SportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "sportTypes", path = "sport-types")
public interface SportTypeRepository extends JpaRepository<SportType, String> {
}
