package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import org.springframework.stereotype.Service;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;

/**
 * Class.
 */

@Service
public class MuseumService implements MuseumServiceInterface {

  MuseumFakeDatabase museumDatabase;

  /**
   * Constructor.
   */
  public MuseumService(MuseumFakeDatabase museumDatabase) {
    this.museumDatabase = museumDatabase;
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    double lat = coordinate.latitude();
    double lon = coordinate.longitude();
    isCoordinatesValid(lat, lon);
    return museumDatabase.getClosestMuseum(coordinate, maxDistance)
        .orElseThrow(MuseumNotFoundException::new);
  }

  @Override
  public Museum createMuseum(Museum museum) {
    double lat = museum.getCoordinate().latitude();
    double lon = museum.getCoordinate().longitude();
    isCoordinatesValid(lat, lon);
    return museumDatabase.saveMuseum(museum);
  }

  @Override
  public Museum getMuseum(Long id) {
    return null;
  }

  private static void isCoordinatesValid(double lat, double lon) {
    Coordinate coordinate = new Coordinate(lat, lon);
    if (!CoordinateUtil.isCoordinateValid(coordinate)) {
      throw new InvalidCoordinateException();
    }
  }
}