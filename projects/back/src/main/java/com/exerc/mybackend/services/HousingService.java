package com.exerc.mybackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exerc.mybackend.entities.HousingLocation;
import com.exerc.mybackend.repositories.HousingLocationRepository;

@Service
public class HousingService {
    @Autowired
    private HousingLocationRepository repository;

    public void saveHousingLocation(HousingLocation location) {
        repository.save(location);
    }

    public HousingLocation getHousingLocationById(int id) {
        return repository.findById(id).orElse(null);
    }
}