package org.makarenko.gamecities.component;

import org.makarenko.gamecities.repository.ErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ErrorService {

  private final ErrorRepository errorRepository;

  @Autowired
  public ErrorService(ErrorRepository errorRepository) {
    this.errorRepository = errorRepository;
  }

  public String getErrorMessageNoCity() {
    return errorRepository.getErrorMessageNoCity();
  }

  public String getErrorMessageWrongCity() {
    return errorRepository.getErrorMessageWrongCity();
  }

  public String getErrorMessageLost() {
    return errorRepository.getErrorMessageLost();
  }

  public String getErrorEmptyInput() {
    return errorRepository.getErrorEmptyInput();
  }
}
