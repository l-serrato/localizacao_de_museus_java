package com.betrybe.museumfinder.controller;


import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Class.
 */

@Controller
@RequestMapping("/museums")
public class MuseumController {
  MuseumServiceInterface serviceMuseum;

  @Autowired
  public MuseumController(MuseumServiceInterface serviceMuseum) {
    this.serviceMuseum = serviceMuseum;
  }

  /**
   * PostMapping.
   */
  @PostMapping
  public ResponseEntity<Museum> createMuseum(@RequestBody MuseumDto museumDto) {
    Museum museum = ModelDtoConverter.dtoToModel(museumDto);
    Museum createdMuseum = serviceMuseum.createMuseum(museum);
    return  ResponseEntity.status(201).body(createdMuseum);
  }

  /**
   * GetMapping.
   */
  @GetMapping("/closest")
  public ResponseEntity<MuseumDto> getClosestMuseum(
      @RequestParam("lat") Double latitude,
      @RequestParam("lng") Double longitude,
      @RequestParam("max_dist_km") Double maxDistance
  ) {
    Coordinate coor = new Coordinate(latitude, longitude);

    Museum closesMuseum = serviceMuseum.getClosestMuseum(coor, maxDistance);

    if (closesMuseum == null) {
      return ResponseEntity.notFound().build();
    }

    MuseumDto museumDto = ModelDtoConverter.modelToDto(closesMuseum);
    return ResponseEntity.ok(museumDto);
  }
}