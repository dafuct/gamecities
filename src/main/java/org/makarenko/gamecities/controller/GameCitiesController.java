package org.makarenko.gamecities.controller;

import java.util.List;
import java.util.Random;
import org.makarenko.gamecities.component.ErrorService;
import org.makarenko.gamecities.service.GameCitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameCitiesController {

  private final ErrorService errorService;
  private final GameCitiesService citiesService;
  private String computerCity;

  private static final int RANDOM_NUMBER_TO = 3625;

  @Autowired
  private GameCitiesController(ErrorService errorService,
      GameCitiesService citiesService) {
    this.errorService = errorService;
    this.citiesService = citiesService;
  }

  @GetMapping({"/", "/begin"})
  public String begin(Model model) {
    List<String> listCities = citiesService.listCities();
    int randomCity = new Random().nextInt(RANDOM_NUMBER_TO);
    String city = listCities.get(randomCity);
    char lastSymbol = Character.toUpperCase(city.charAt(city.length() - 1));
    addCityToAttribute(city, lastSymbol, model);
    return "index";
  }

  @GetMapping("/next")
  public String next(@RequestParam("word") String word, Model model) {
    if (checkLastCompSymbolFirstUserSymbols(word, model)) {
      return "index";
    }

    if (checkIfExistCity(word, model)) {
      return "index";
    }

    findCityInList(word, model);
    return "index";
  }

  @PostMapping("/end")
  public String end() {
    return "end";
  }

  private void findCityInList(@RequestParam("word") String word, Model model) {
    char lastUserSymbol = citiesService.checkChar(word);
    citiesService.findCity(lastUserSymbol).ifPresentOrElse(city -> {
          char lastSymbol = Character.toUpperCase(city.charAt(city.length() - 1));
          addCityToAttribute(city, lastSymbol, model);
        },
        () -> {
          addAttributeLastSymbol(model);
          model.addAttribute("errorMessageLost", errorService.getErrorMessageLost());
        });
  }

  private boolean checkIfExistCity(String word, Model model) {
    if (!citiesService.existCity(word)) {
      addErrorToAttribute(model, "errorMessageNoCity", errorService.getErrorMessageNoCity());
      addAttributeLastSymbol(model);
      return true;
    }
    return false;
  }

  private boolean checkLastCompSymbolFirstUserSymbols(String word, Model model) {
    if (emptyInput(word, model)) {
      return true;
    }
    char computerCityLastSymbol = citiesService.checkChar(computerCity);
    char userFirstSymbol = word.charAt(0);
    if (computerCityLastSymbol != userFirstSymbol) {
      addErrorToAttribute(model, "errorMessageWrongCity",
          errorService.getErrorMessageWrongCity());
      addAttributeLastSymbol(model);
      return true;
    }
    return false;
  }

  private boolean emptyInput(String word, Model model) {
    if (word.length() == 0) {
      addErrorToAttribute(model, "emptyInput", errorService.getErrorEmptyInput());
      addAttributeLastSymbol(model);
      return true;
    }
    return false;
  }

  private void addAttributeLastSymbol(Model model) {
    int lastIndex = computerCity.length() - 1;
    char lastSymbol = Character.toUpperCase(computerCity.charAt(lastIndex));
    model.addAttribute("lastSymbol", lastSymbol);
  }

  private void addErrorToAttribute(Model model, String errorMessage, String errorObject) {
    model.addAttribute("city", computerCity);
    model.addAttribute(errorMessage, errorObject);
  }

  private void addCityToAttribute(String city, char lastSymbol, Model model) {
    citiesService.listCities().remove(city);
    computerCity = city;
    model.addAttribute("city", computerCity);
    model.addAttribute("lastSymbol", lastSymbol);
  }
}
