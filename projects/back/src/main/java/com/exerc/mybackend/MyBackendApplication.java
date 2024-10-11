package com.exerc.mybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.exerc.mybackend.entities.HousingLocation;	
import com.exerc.mybackend.services.HousingService;

@SpringBootApplication
// public class MyBackendApplication  implements CommandLineRunner {
public class MyBackendApplication {
	// @Autowired
    // private HousingService housingService;

	public static void main(String[] args) {
		SpringApplication.run(MyBackendApplication.class, args);
	}

	// @Override
    // public void run(String... args) throws Exception {
    //     HousingLocation location = new HousingLocation();
    //     location.setName("Sample Housing");
    //     location.setCity("Sample City");
    //     location.setState("Sample State");
    //     location.setPhoto("https://example.com/photo.jpg"); //revisar esta url
    //     location.setAvailableUnits(10);
    //     location.setWifi(true);
    //     location.setLaundry(true);
    //     location.setBedrooms(3);
    //     location.setParking(true);
    //     housingService.saveHousingLocation(location);
    // }
}
