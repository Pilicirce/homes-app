package com.exerc.mybackend.repositories;

import com.exerc.mybackend.model.HousingLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import com.exerc.back.entities.HousingLocation;

public interface HousingLocationRepository extends JpaRepository<HousingLocation, Integer> {
}