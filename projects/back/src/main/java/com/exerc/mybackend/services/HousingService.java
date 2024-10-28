package com.exerc.mybackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exerc.mybackend.entities.HousingLocation;
import com.exerc.mybackend.repositories.HousingLocationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HousingService {
    @Autowired
    private HousingLocationRepository housingLocationRepository;

     // Método para obtener todas las ubicaciones de vivienda
     public List<HousingLocation> findAll() {
        return housingLocationRepository.findAll();
        
    }

    // Método para obtener una ubicación de vivienda por ID
    // public HousingLocation getHousingLocationById(Long id) {
    //     Optional<HousingLocation> optional = housingLocationRepository.findById(id);
    //     return optional.orElse(null);
    // }

    public Optional<HousingLocation> getHousingLocationById(Long id) {
        return housingLocationRepository.findById(id);
    }

     // Método para guardar una ubicación de vivienda (create)
     public HousingLocation createHousing(HousingLocation housingLocation) {
        //SI la vivienda viene sin foto, asignar una por defecto
        if (housingLocation.getPhoto() == null || housingLocation.getPhoto().isEmpty()) {
            housingLocation.setPhoto("http://localhost:8080/images/default.jpg");
        }
            return housingLocationRepository.save(housingLocation);
    }


    // Método para actualizar una ubicación de vivienda
    public HousingLocation updateHousingLocation(Long id, HousingLocation updatedHousingLocation) {
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

     // Método para eliminar una ubicación de vivienda por ID
     public void deleteHousingLocation(Long id) {
        housingLocationRepository.deleteById(id);
    }
}