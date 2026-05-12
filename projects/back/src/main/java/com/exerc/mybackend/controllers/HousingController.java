package com.exerc.mybackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.exerc.mybackend.entities.HousingLocation;
import com.exerc.mybackend.services.HousingService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

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
     public ResponseEntity<HousingLocation> getHousingById(@PathVariable Long id) {
       HousingLocation housing = housingService.getHousingLocationById(id);
       return ResponseEntity.ok(housing);
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




  @PutMapping("/{id}")
  public ResponseEntity<HousingLocation> updateHousingLocation(
    @PathVariable Long id,
    @RequestBody HousingLocation updatedHousingLocation) {

    HousingLocation updated = housingService.updateHousingLocation(id, updatedHousingLocation);
    return ResponseEntity.ok(updated);
  }



    //Borrar una ubicación de vivienda (me falta en el service?)
    @DeleteMapping("/{id}")
    public void deleteHousingLocation(@PathVariable Long id) {
        housingService.deleteHousingLocation(id);
    }
}
