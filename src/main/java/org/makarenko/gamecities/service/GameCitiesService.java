package org.makarenko.gamecities.service;

import java.util.List;
import java.util.Optional;

public interface GameCitiesService {

  List<String> listCities();

  boolean existCity(String city);

  Optional<String> findCity(Character symbol);

  char checkChar(String city);
}
