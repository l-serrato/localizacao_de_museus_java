package com.betrybe.museumfinder.solution;


import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * CollectionTypeServiceTest class.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CollectionTypeServiceTest {

  @MockBean
  MuseumFakeDatabase museumFakeDatabase;

  @Autowired
  private CollectionTypeService collectionTypeService;

  @Test
  public void testCountCollectionTypes() {
    Mockito.when(museumFakeDatabase.countByCollectionType(Mockito.any()))
        .thenReturn(492L);
    CollectionTypeService service = new CollectionTypeService(museumFakeDatabase);
    service.countByCollectionTypes("hist");
    assert (service.countByCollectionTypes("hist").count() == 492);
    assert (service.countByCollectionTypes("hist").collectionTypes()[0].equals("hist"));
  }
}