package com.exerc.mybackend.services;

import com.exerc.mybackend.entities.HousingLocation;
import com.exerc.mybackend.exceptions.ResourceNotFoundException;
import com.exerc.mybackend.repositories.HousingLocationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
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


  //test UPDATE (happy path)
  @Test
  void shouldUpdateHousingWhenExists() {

    //Datos existentes en "BD"
    HousingLocation existing = new HousingLocation(
      "Old Name", "Old City", "Spain", "photo.jpg", 2, true, false, 2, true
    );

    //Datos nuevos que queremos actualizar
    HousingLocation updatedData = new HousingLocation(
      "New Name", "New City", "Spain", "new-photo.jpg", 3, false, true, 3, false
    );

    //Simulamos findById → devuelve existing
    when(repository.findById(1L)).thenReturn(Optional.of(existing));

    //Simulamos que cuando se llama a save → devuelve el objeto actualizado
    when(repository.save(any(HousingLocation.class))).thenAnswer(invocation -> invocation.getArgument(0));

    //Ejecutamos el método real
    HousingLocation result = housingService.updateHousingLocation(1L, updatedData);

    //Validamos que se actualiza correctamente
    assertNotNull(result);
    assertEquals("New Name", result.getName());
    assertEquals("New City", result.getCity());
    assertEquals(3, result.getAvailableUnits());

    //Validamos que se guarda en repositorio
    verify(repository, times(1)).save(existing);
  }


  //test UPDATE 404
  @Test
  void shouldThrowExceptionWhenUpdatingNonExistingHousing() {

    when(repository.findById(99L)).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> {
      housingService.updateHousingLocation(99L, new HousingLocation());
    });
  }


  //testeo que ejecuta el findAll()
  @Test
  void shouldReturnAllHousing() {

    HousingLocation h1 = new HousingLocation(
      "House 1", "Madrid", "Spain", "photo.jpg", 2, true, false, 2, true
    );

    HousingLocation h2 = new HousingLocation(
      "House 2", "Barcelona", "Spain", "photo.jpg", 3, true, true, 3, false
    );

    when(repository.findAll()).thenReturn(Arrays.asList(h1, h2));

    List<HousingLocation> result = housingService.findAll();

    assertEquals(2, result.size());
    verify(repository).findAll();
  }



  //testeo que para la foto venga un string vacio
  @Test
  void shouldSetDefaultPhotoWhenPhotoIsEmpty() {

    HousingLocation housing = new HousingLocation(
      "House", "Madrid", "Spain", "", 2, true, false, 2, true
    );

    when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

    HousingLocation result = housingService.createHousing(housing);

    assertEquals("http://localhost:8081/images/default.jpg", result.getPhoto());
  }


  //testeo que setea foto por defecto si la foto viene null
  @Test
  void shouldSetDefaultPhotoWhenPhotoIsNull() {

    HousingLocation housing = new HousingLocation(
      "House", "Madrid", "Spain", null, 2, true, false, 2, true
    );

    when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

    HousingLocation result = housingService.createHousing(housing);

    assertEquals("http://localhost:8081/images/default.jpg", result.getPhoto());
  }


  //testeo el caso en que la foto venga "no vacio"
  @Test
  void shouldNotOverridePhotoWhenPhotoExists() {

    HousingLocation housing = new HousingLocation(
      "House", "Madrid", "Spain", "custom.jpg", 2, true, false, 2, true
    );

    when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

    HousingLocation result = housingService.createHousing(housing);

    assertEquals("custom.jpg", result.getPhoto());
  }


}
