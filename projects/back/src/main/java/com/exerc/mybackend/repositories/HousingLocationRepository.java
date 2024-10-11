package com.exerc.mybackend.repositories;

import com.exerc.mybackend.entities.HousingLocation;
import org.springframework.data.jpa.repositories.JpaRepository;

public interface HousingLocationRepository extends JpaRepository<HousingLocation, Integer> {
}