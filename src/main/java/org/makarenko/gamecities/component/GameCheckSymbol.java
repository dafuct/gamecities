package org.makarenko.gamecities.component;

import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class GameCheckSymbol {

  private final List<Character> exceptionSymbols = List.of('ь', 'ъ', 'ы');

  public char checkSymbolOnException(String city) {
    Objects.requireNonNull(city, "city cannot null");
    int lastCharIndex = city.length() - 1;
    char lastChar = city.charAt(lastCharIndex);
    if (exceptionSymbols.contains(lastChar)) {
      lastChar = city.charAt(--lastCharIndex);
    }
    return Character.toUpperCase(lastChar);
  }
}
