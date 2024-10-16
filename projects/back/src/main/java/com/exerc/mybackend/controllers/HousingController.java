package com.exerc.mybackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.exerc.mybackend.entities.HousingLocation;
import com.exerc.mybackend.repositories.HousingLocationRepository;
import com.exerc.mybackend.services.HousingService;

import java.util.List;

@RestController
@RequestMapping("/api/housing")
public class HousingController {

    @Autowired
    private HousingLocationRepository housingLocationRepository;

    @Autowired
    private HousingService housingService;

    // Obtener todas las ubicaciones de vivienda
    @GetMapping
    public List<HousingLocation> getAllHousingLocations() {
        return housingService.findAll();
    }

     // Obtener una ubicación de vivienda por ID
    @GetMapping("/{id}")
    public HousingLocation getHousingLocationById(@PathVariable Long id) {
        return housingService.getHousingLocationById(id).orElse(null);
    }

    // Crear una nueva ubicación de vivienda (me falta en el service?)
    @PostMapping
    public void createHousingLocation(@RequestBody HousingLocation housingLocation) {
        housingService.saveHousingLocation(housingLocation);
    }

    // //este es el mismo que el anterior?
    // @PostMapping
    // public HousingLocation addHousingLocation(@RequestBody HousingLocation housingLocation) {
    //     return housingLocationRepository.save(housingLocation);
    // }

    // Actualizar una ubicación de vivienda existente
    @PutMapping("/{id}")
    public HousingLocation updateHousingLocation(@PathVariable Long id, @RequestBody HousingLocation updatedHousingLocation) {
        HousingLocation housingLocation = housingService.getHousingLocationById(id);
        if (housingLocation != null) {
            housingLocation.setName(updatedHousingLocation.getName());
            housingLocation.setCity(updatedHousingLocation.getCity());
            housingLocation.setState(updatedHousingLocation.getState());
            housingLocation.setPhoto(updatedHousingLocation.getPhoto());
            housingLocation.setAvailableUnits(updatedHousingLocation.getAvailableUnits());
            housingLocation.setWifi(updatedHousingLocation.isWifi());
            housingLocation.setLaundry(updatedHousingLocation.isLaundry());
            housingLocation.setBedrooms(updatedHousingLocation.getBedrooms());
            housingLocation.setParking(updatedHousingLocation.isParking());
            housingService.saveHousingLocation(housingLocation);
            return housingLocation;
        } else {
            return null; // Puedo manejar esto con una excepción o una respuesta adecuada
        }
    }

    //Borrar una ubicación de vivienda (me falta en el service?)
    @DeleteMapping("/{id}")
    public void deleteHousingLocation(@PathVariable Long id) {
        housingLocationRepository.deleteById(id);
    }
}