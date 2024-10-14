package com.exerc.mybackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import javax.persistence.Entity;
// import javax.persistence.Id;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;

@Entity
public class HousingLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String state;
    private String photo;
    private int availableUnits;
    private boolean wifi;
    private boolean laundry;
    private int bedrooms;
    private boolean parking;

    // Constructor vacío para JPA
    public HousingLocation() {
    }

    // Constructor completo con todos los atributos
    public HousingLocation(String name, String city, String state, String photo, int availableUnits, boolean wifi, boolean laundry, int bedrooms, boolean parking) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.photo = photo;
        this.availableUnits = availableUnits;
        this.wifi = wifi;
        this.laundry = laundry;
        this.bedrooms = bedrooms;
        this.parking = parking;
    }

    // Getters y setters
}