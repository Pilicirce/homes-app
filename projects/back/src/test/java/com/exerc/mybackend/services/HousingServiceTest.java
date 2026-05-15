package com.exerc.mybackend.services;

import com.exerc.mybackend.entities.HousingLocation;
import com.exerc.mybackend.exceptions.ResourceNotFoundException;
import com.exerc.mybackend.repositories.HousingLocationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class HousingServiceTest {

  @Mock
  private HousingLocationRepository repository;  //creamos un repo falso

  @InjectMocks  //inyecta el repo mock dentro del service real
  private HousingService housingService;


  //testeo findById OK
  @Test
  void shouldReturnHousingWhenIdExists() {

    HousingLocation housing = new HousingLocation(
      "House 1", "Madrid", "Spain", "photo.jpg", 2, true, false, 2, true
    );

    when(repository.findById(1L)).thenReturn(Optional.of(housing));

    HousingLocation result = housingService.getHousingLocationById(1L);

    assertNotNull(result);
    assertEquals("House 1", result.getName());
  }


  //test 404
  @Test
  void shouldThrowExceptionWhenHousingNotFound() {

    when(repository.findById(99L)).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> {
      housingService.getHousingLocationById(99L);
    });
  }


  //test CREATE
  @Test
  void shouldCreateHousing() {

    HousingLocation housing = new HousingLocation(
      "House 1", "Madrid", "Spain", "", 2, true, false, 2, true
    );

    when(repository.save(any(HousingLocation.class))).thenReturn(housing);

    HousingLocation result = housingService.createHousing(housing);

    assertNotNull(result);
    verify(repository, times(1)).save(housing);
  }


  //Test DELETE
  @Test
  void shouldDeleteHousing() {

    when(repository.existsById(1L)).thenReturn(true);

    housingService.deleteHousingLocation(1L);

    verify(repository, times(1)).deleteById(1L);
  }


  //test DELETE 404
  @Test
  void shouldThrowExceptionWhenDeletingNonExistingHousing() {

    when(repository.existsById(99L)).thenReturn(false);

    assertThrows(ResourceNotFoundException.class, () -> {
      housingService.deleteHousingLocation(99L);
    });
  }

}
