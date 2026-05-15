package com.exerc.mybackend.controllers;

import com.exerc.mybackend.services.HousingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import com.exerc.mybackend.entities.HousingLocation;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.exerc.mybackend.exceptions.ResourceNotFoundException;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(HousingController.class)
public class HousingControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private HousingService housingService;
  //creo un servicio falso porque el controller lo necesita (y no quiero usar el real)

  /*
  @Test
  void shouldReturnAllHousing() throws Exception {
    mockMvc.perform(get("/api/housing"))  //como si fuese Postman
      .andExpect(status().isOk());
  }
  */


  //testeo: GET All, (happy path), para testear que cuando pido todas las viviendas, la API responde correctamente
  @Test
  void shouldReturnAllHousing() throws Exception {

    // Simulamos datos del servicio
    HousingLocation h1 = new HousingLocation(
      "House 1", "Madrid", "Spain", "photo.jpg", 2, true, false, 2, true
    );

    HousingLocation h2 = new HousingLocation(
      "House 2", "Barcelona", "Spain", "photo.jpg", 3, true, true, 3, false
    );

    when(housingService.findAll()).thenReturn(Arrays.asList(h1, h2));

    mockMvc.perform(get("/api/housing"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(2)) //el jsonPath valida el JSON devuelto
      .andExpect(jsonPath("$[0].name").value("House 1"))
      .andExpect(jsonPath("$[1].city").value("Barcelona"));
  }


  //testeo: GET by ID (happy path)
  @Test
  void shouldReturnHousingById() throws Exception {

    HousingLocation housing = new HousingLocation(
      "House 1", "Madrid", "Spain", "photo.jpg", 2, true, false, 2, true
    );

    when(housingService.getHousingLocationById(1L)).thenReturn(housing);

    mockMvc.perform(get("/api/housing/1"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.name").value("House 1"))
      .andExpect(jsonPath("$.city").value("Madrid"));
  }


  // testeo: GET by ID /api/housing/999 -> debe devolver: 404 Not found
  //Para testear que cuando pido algo que no existe, la API responde con un error.
  //Mi API maneja errores correctamente (no devuelve basura ni null)
  @Test
  void shouldReturn404WhenHousingNotFound() throws Exception {

    when(housingService.getHousingLocationById(999L))
      .thenThrow(new ResourceNotFoundException("Not found"));

    mockMvc.perform(get("/api/housing/999"))
      .andExpect(status().isNotFound());
  }



  //Para testear: GET by ID, y POST cuando envío datos inválidos, la API valida y responde correctamente
  //test de validación (400)
  @Test
  void shouldReturn400WhenInvalidInput() throws Exception {

    String invalidJson = """
        {
            "name": "",
            "city": "",
            "country": "",
            "availableUnits": -1,
            "bedrooms": -1
        }
    """;

    mockMvc.perform(post("/api/housing")
        .contentType(MediaType.APPLICATION_JSON)
        .content(invalidJson))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.errors.name").value("Name is required"))
      .andExpect(jsonPath("$.errors.city").value("City is required"));
  }

  //testeo POST happy path
  @Test
  void shouldCreateHousing() throws Exception {

    String validJson = """
        {
            "name": "Test House",
            "city": "Madrid",
            "country": "Spain",
            "availableUnits": 2,
            "bedrooms": 2
        }
    """;

    HousingLocation created = new HousingLocation(
      "Test House", "Madrid", "Spain", "photo.jpg", 2, true, false, 2, true
    );

    when(housingService.createHousing(any())).thenReturn(created);

    mockMvc.perform(post("/api/housing")
        .contentType(MediaType.APPLICATION_JSON)
        .content(validJson))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value("Test House"))
        .andExpect(jsonPath("$.city").value("Madrid"));

  }


  //testeo: PUT (happy path)
  @Test
  void shouldUpdateHousing() throws Exception {

    String json = """
        {
            "name": "Updated House",
            "city": "Madrid",
            "country": "Spain",
            "availableUnits": 2,
            "bedrooms": 2
        }
    """;

    HousingLocation updated = new HousingLocation(
      "Updated House", "Madrid", "Spain", "photo.jpg", 2, true, false, 2, true
    );

    when(housingService.updateHousingLocation(eq(1L), any())).thenReturn(updated);

    mockMvc.perform(put("/api/housing/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.name").value("Updated House"));
  }



  //testeo PUT (caso de error 404)
  @Test
  void shouldReturn404WhenUpdatingNonExistingHousing() throws Exception {

    when(housingService.updateHousingLocation(eq(99L), any(HousingLocation.class)))
      .thenThrow(new ResourceNotFoundException("Not found"));

    String validJson = """
        {
            "name": "Updated House",
            "city": "Madrid",
            "country": "Spain",
            "availableUnits": 2,
            "bedrooms": 2
        }
    """;

    mockMvc.perform(put("/api/housing/99")
        .contentType(MediaType.APPLICATION_JSON)
        .content(validJson))
      .andExpect(status().isNotFound());
  }


  //testeo DELETE (happy path)
  @Test
  void shouldDeleteHousing() throws Exception {

    mockMvc.perform(delete("/api/housing/1"))
      .andExpect(status().isOk());
  }


  //testeo DELETE (caso de error 404)
  @Test
  void shouldReturn404WhenDeletingNonExistingHousing() throws Exception {

    doThrow(new ResourceNotFoundException("Not found"))
      .when(housingService).deleteHousingLocation(99L);

    mockMvc.perform(delete("/api/housing/99"))
      .andExpect(status().isNotFound());
  }

}
