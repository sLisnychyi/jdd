package academy.kovalevskyi.javadeepdive.week1.day2;

import java.util.Arrays;
import java.util.Optional;

public enum HttpVersion {
  HTTP_1_1("HTTP/1.1");

  private final String value;

  HttpVersion(String value) {
    this.value = value;
  }

  static Optional<HttpVersion> from(String value) {
    return Arrays.stream(values()).filter(e -> e.value.equalsIgnoreCase(value)).findFirst();
  }

  public String getValue() {
    return value;
  }
}
