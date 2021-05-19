package academy.kovalevskyi.javadeepdive.week1.day2;

import java.util.Arrays;
import java.util.Optional;

public enum HttpMethod {
  POST,
  PUT,
  GET;

  static Optional<HttpMethod> from(String value) {
    return Arrays.stream(values()).filter(e -> e.toString().equalsIgnoreCase(value)).findFirst();
  }
}
