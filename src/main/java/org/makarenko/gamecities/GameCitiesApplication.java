package org.makarenko.gamecities;

import org.makarenko.gamecities.controller.GameCitiesController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:error-message.properties", encoding = "UTF-8")
@SpringBootApplication
public class GameCitiesApplication {

  public static void main(String[] args) {
    SpringApplication.run(GameCitiesApplication.class, args);
  }
}
