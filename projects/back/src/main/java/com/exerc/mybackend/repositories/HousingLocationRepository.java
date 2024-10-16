package com.exerc.mybackend.repositories;

import com.exerc.mybackend.entities.HousingLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HousingLocationRepository extends JpaRepository<HousingLocation, Long> {
}