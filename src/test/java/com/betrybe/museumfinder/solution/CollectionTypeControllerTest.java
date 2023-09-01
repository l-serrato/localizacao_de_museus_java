package com.betrybe.museumfinder.solution;

import com.betrybe.museumfinder.controller.CollectionTypeController;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * CollectionTypeControllerTest class.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CollectionTypeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CollectionTypeService collectionTypeService;

  @Test
  public void testCountCollectionTypes() throws Exception {
    Mockito.when(collectionTypeService.countByCollectionTypes(Mockito.any()))
        .thenReturn(new CollectionTypeCount(new String[]{"hist"}, 492));

    mockMvc.perform(
            MockMvcRequestBuilders.get("/collections/count/hist"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testCountCollectionTypesNotFound() throws Exception {
    Mockito.when(collectionTypeService.countByCollectionTypes(Mockito.any()))
        .thenReturn(new CollectionTypeCount(new String[]{"hist"}, 0));

    mockMvc.perform(
            MockMvcRequestBuilders.get("/collections/count/hist"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void testCountCollectionTypesMultiple() throws Exception {
    Mockito.when(collectionTypeService.countByCollectionTypes(Mockito.any()))
        .thenReturn(new CollectionTypeCount(new String[]{"hist", "arte"}, 492));

    mockMvc.perform(
            MockMvcRequestBuilders.get("/collections/count/hist,arte"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testCountCollectionTypesMultipleNotFound() throws Exception {
    Mockito.when(collectionTypeService.countByCollectionTypes(Mockito.any()))
        .thenReturn(new CollectionTypeCount(new String[]{"hist", "arte"}, 0));

    mockMvc.perform(
            MockMvcRequestBuilders.get("/collections/count/hist,arte"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void testCountCollectionTypesOk() throws Exception {
    Mockito.when(collectionTypeService.countByCollectionTypes(Mockito.any()))
        .thenReturn(new CollectionTypeCount(new String[]{"hist", "imag"}, 492));
    mockMvc.perform(
            MockMvcRequestBuilders.get("/collections/count/hist, imag"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testCountCollectionTypesError() throws Exception {
    Mockito.when(collectionTypeService.countByCollectionTypes(Mockito.any()))
        .thenReturn(new CollectionTypeCount(new String[]{"adddd"}, 0));

    mockMvc.perform(
            MockMvcRequestBuilders.get("/collections/count/adddd"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

}