package academy.kovalevskyi.javadeepdive.week1.day2;

import java.util.Optional;

public class HttpResponseBuilder {

  public static String build(HttpResponse httpResponse) {
    Optional<String> body = Optional.ofNullable(httpResponse.body());
    StringBuilder result =
        new StringBuilder(
            String.format(
                "%s %s %s\n" + "Content-type: %s\n" + "Content-Length: %s\n\n",
                httpResponse.httpVersion().getValue(),
                httpResponse.responseStatus().getStatusCode(),
                httpResponse.responseStatus().getMessage(),
                httpResponse.contentType().getValue(),
                body.map(String::length).map(e -> e + 4).orElse(0)));
    body.ifPresent(e -> result.append(String.format("%s\r\n\r\n", e)));
    return result.toString();
  }
}
