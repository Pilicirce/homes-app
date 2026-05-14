package com.exerc.mybackend.controllers;

import com.exerc.mybackend.services.HousingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Arrays;
import com.exerc.mybackend.entities.HousingLocation;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import com.exerc.mybackend.exceptions.ResourceNotFoundException;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;


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


  //para testear que cuando pido todas las viviendas, la API responde correctamente
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


  //Para testear que cuando pido algo que no existe, la API responde con un error.
  // testeo: GET /api/housing/999 -> debe devolver: 404 Not found
  //Mi API maneja errores correctamente (no devuelve basura ni null)
  @Test
  void shouldReturn404WhenHousingNotFound() throws Exception {

    when(housingService.getHousingLocationById(999L))
      .thenThrow(new ResourceNotFoundException("Not found"));

    mockMvc.perform(get("/api/housing/999"))
      .andExpect(status().isNotFound());
  }


  //Para testear: cuando envío datos inválidos, la API valida y responde correctamente
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

}
