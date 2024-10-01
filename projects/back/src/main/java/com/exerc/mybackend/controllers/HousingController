package com.exerc.mybackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
// import com.exerc.back.entities.HousingLocation;
// import com.exerc.back.services.HousingService;
import com.exerc.mybackend.model.HousingLocation;
import com.exerc.mybackend.repository.HousingLocationRepository;

import java.util.List;

@RestController
@RequestMapping("/api/housing")
public class HousingController {

    @Autowired
    private HousingLocationRepository housingLocationRepository;

    @Autowired
    private HousingService housingService;

    @GetMapping
    public List<HousingLocation> getAllHousingLocations() {
        return housingService.findAll();
    }

    @GetMapping("/{id}")
    public HousingLocation getHousingLocationById(@PathVariable int id) {
        return housingService.getHousingLocationById(id).orElse(null);
    }

    @PostMapping
    public void createHousingLocation(@RequestBody HousingLocation housingLocation) {
        housingService.saveHousingLocation(housingLocation);
    }

    //este es el mismo que el anterior?
    @PostMapping
    public HousingLocation addHousingLocation(@RequestBody HousingLocation housingLocation) {
        return housingLocationRepository.save(housingLocation);
    }

    @PutMapping("/{id}")
    public HousingLocation updateHousingLocation(@PathVariable Long id, @RequestBody HousingLocation updatedHousingLocation) {
        return housingLocationRepository.findById(id)
                .map(housingLocation -> {
                    housingLocation.setName(updatedHousingLocation.getName());
                    housingLocation.setCity(updatedHousingLocation.getCity());
                    housingLocation.setState(updatedHousingLocation.getState());
                    housingLocation.setPhoto(updatedHousingLocation.getPhoto());
                    housingLocation.setAvailableUnits(updatedHousingLocation.getAvailableUnits());
                    housingLocation.setWifi(updatedHousingLocation.isWifi());
                    housingLocation.setLaundry(updatedHousingLocation.isLaundry());
                    return housingLocationRepository.save(housingLocation);
                }).orElseGet(() -> {
                    updatedHousingLocation.setId(id);
                    return housingLocationRepository.save(updatedHousingLocation);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteHousingLocation(@PathVariable Long id) {
        housingLocationRepository.deleteById(id);
    }
}