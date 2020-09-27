package org.makarenko.gamecities.service.impl;

import java.util.List;
import java.util.Optional;
import org.makarenko.gamecities.component.GameCheckSymbol;
import org.makarenko.gamecities.repository.AllCitiesRepository;
import org.makarenko.gamecities.service.GameCitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameCitiesServiceImpl implements GameCitiesService {

  private final AllCitiesRepository citiesRepository;
  private final GameCheckSymbol gameCheckSymbol;

  @Autowired
  public GameCitiesServiceImpl(AllCitiesRepository citiesRepository,
      GameCheckSymbol gameCheckSymbol) {
    this.citiesRepository = citiesRepository;
    this.gameCheckSymbol = gameCheckSymbol;
  }

  public List<String> listCities() {
    return citiesRepository.getAllCities();
  }

  @Override
  public boolean existCity(String city) {
    return citiesRepository.checkExistCityInList(city);
  }

  @Override
  public Optional<String> findCity(Character lastSymbol) {
    return citiesRepository.findCityByLastSymbol(lastSymbol);
  }

  @Override
  public char checkChar(String city) {
    return gameCheckSymbol.checkSymbolOnException(city);
  }
}
