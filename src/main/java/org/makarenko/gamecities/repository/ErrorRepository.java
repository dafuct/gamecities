package org.makarenko.gamecities.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ErrorRepository {

  @Value("${error.message.no.city}")
  private String errorMessageNoCity;
  @Value("${error.message.wrong.city}")
  private String errorMessageWrongCity;
  @Value("${error.message.lost}")
  private String errorMessageLost;
  @Value("${error.empty.input}")
  private String errorEmptyInput;

  public String getErrorMessageNoCity() {
    return errorMessageNoCity;
  }

  public String getErrorMessageWrongCity() {
    return errorMessageWrongCity;
  }

  public String getErrorMessageLost() {
    return errorMessageLost;
  }

  public String getErrorEmptyInput() {
    return errorEmptyInput;
  }
}
