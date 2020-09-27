package org.makarenko.gamecities.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.makarenko.gamecities.exception.GameCitiesException;
import org.springframework.stereotype.Component;

@Component
public class AllCitiesRepository {

  private final List<String> allCities = new ArrayList<>();

  private void fillCitiesFromFileToList() {
    String filePath = Objects
        .requireNonNull(
            getClass()
                .getClassLoader()
                .getResource("database-cities.txt"))
        .getPath();
    Path path = Paths.get(filePath);
    try {
      Files.lines(path).forEach(allCities::add);
      Collections.sort(allCities);
    } catch (IOException e) {
      throw new GameCitiesException("wrong read files with cities", e);
    }
  }

  public List<String> getAllCities() {
    if (allCities.isEmpty()) {
      fillCitiesFromFileToList();
    }
    return allCities;
  }

  public boolean checkExistCityInList(String checkCity) {
    Objects.requireNonNull(checkCity, "city cannot null");
    Optional<String> finedCity = allCities.stream()
        .filter(findCity -> findCity.equals(checkCity))
        .findAny();
    finedCity.ifPresent(allCities::remove);
    return finedCity.isPresent();
  }

  public Optional<String> findCityByLastSymbol(Character lastSymbol) {
    Objects.requireNonNull(lastSymbol, "lastSymbol cannot null");
    return allCities.stream().filter(city -> city.startsWith(String.valueOf(lastSymbol)))
        .findFirst();
  }
}
