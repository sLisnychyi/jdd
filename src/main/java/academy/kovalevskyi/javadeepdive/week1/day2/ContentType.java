package academy.kovalevskyi.javadeepdive.week1.day2;

import java.util.Arrays;
import java.util.Optional;

public enum ContentType {
  TEXT_HTML("text/html"),
  APPLICATION_JSON("application/json");

  private final String value;

  ContentType(String value) {
    this.value = value;
  }

  static Optional<ContentType> from(String value) {
    return Arrays.stream(values()).filter(e -> e.value.equals(value)).findFirst();
  }

  public String getValue() {
    return value;
  }
}
