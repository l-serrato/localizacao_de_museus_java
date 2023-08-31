package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import org.springframework.stereotype.Service;

/**
 * Class.
 */
@Service
public class MuseumService implements MuseumServiceInterface {
  MuseumFakeDatabase museumFakeDatabase;
  public MuseumService(MuseumFakeDatabase museumFakeDatabase) {

    this.museumFakeDatabase = museumFakeDatabase;
  }
  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {

    double lon = coordinate.longitude();
    double lat = coordinate.latitude();

    Coordinate coor = new Coordinate(lat, lon);

    if (!CoordinateUtil.isCoordinateValid(coor)) {
      throw  new InvalidCoordinateException();
    }

    return museumFakeDatabase.getClosestMuseum(coordinate, maxDistance)
        .orElseThrow(MuseumNotFoundException::new);
  }

  @Override
  public Museum createMuseum(Museum museum) {
    double lon = museum.getCoordinate().longitude();
    double lat = museum.getCoordinate().latitude();

    Coordinate coor = new Coordinate(lat, lon);

    if (!CoordinateUtil.isCoordinateValid(coor)) {
      throw  new InvalidCoordinateException();
    }

    return museumFakeDatabase.saveMuseum(museum);
  }

  @Override
  public Museum getMuseum(Long id) {
    return null;
  }
}