package com.exerc.mybackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.exerc.mybackend.entities.HousingLocation;
import com.exerc.mybackend.services.HousingService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/housing")
public class HousingController {

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

    //método manual de prueba
    @GetMapping("/test")
public ResponseEntity<String> testEndpoint() {
    return ResponseEntity.ok("El endpoint de prueba está activo");
}


    // Crear una nueva (ubicación) de vivienda
    @PostMapping
    public ResponseEntity<HousingLocation> createHousing(@RequestBody HousingLocation housingLocation) {
        System.out.println("Llegó al controlador el POST");
        HousingLocation createdHousing = housingService.createHousing(housingLocation);
        System.out.println("Llegó al controlador el POST- segunda parte");
        return new ResponseEntity<>(createdHousing, HttpStatus.CREATED);
    }

    //método temporal para ver si entra en el controller:
    @PostMapping("/ping")
    public ResponseEntity<String> pingPost() {
      System.out.println("Entró al POST /ping");
      return ResponseEntity.ok("POST funciona");
    }

    // Actualizar una ubicación de vivienda existente
    // @PutMapping("/{id}")
    // public HousingLocation updateHousingLocation(@PathVariable Long id, @RequestBody HousingLocation updatedHousingLocation) {
    //     HousingLocation housingLocation = housingService.getHousingLocationById(id);
    //     if (housingLocation != null) {
    //         housingLocation.setName(updatedHousingLocation.getName());
    //         housingLocation.setCity(updatedHousingLocation.getCity());
    //         housingLocation.setState(updatedHousingLocation.getState());
    //         housingLocation.setPhoto(updatedHousingLocation.getPhoto());
    //         housingLocation.setAvailableUnits(updatedHousingLocation.getAvailableUnits());
    //         housingLocation.setWifi(updatedHousingLocation.isWifi());
    //         housingLocation.setLaundry(updatedHousingLocation.isLaundry());
    //         housingLocation.setBedrooms(updatedHousingLocation.getBedrooms());
    //         housingLocation.setParking(updatedHousingLocation.isParking());
    //         housingService.saveHousingLocation(housingLocation);
    //         return housingLocation;
    //     } else {
    //         return null; // Puedo manejar esto con una excepción o una respuesta adecuada
    //     }
    // }

  @PutMapping("/{id}")
public HousingLocation updateHousingLocation(@PathVariable Long id, @RequestBody HousingLocation updatedHousingLocation) {
    System.out.println("Llegó al controlador el PUT");
    HousingLocation housingLocation = housingService.getHousingLocationById(id).orElse(null);
    if (housingLocation != null) {
        return housingService.updateHousingLocation(id, updatedHousingLocation);
    } else {
        return null; // Puedo mejorar esto puedo devolver una respuesta HTTP adecuada
    }
}

    //Borrar una ubicación de vivienda (me falta en el service?)
    @DeleteMapping("/{id}")
    public void deleteHousingLocation(@PathVariable Long id) {
        housingService.deleteHousingLocation(id);
    }
}
