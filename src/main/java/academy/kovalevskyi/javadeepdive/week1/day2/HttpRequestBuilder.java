package academy.kovalevskyi.javadeepdive.week1.day2;

import academy.kovalevskyi.javadeepdive.week0.day0.StdBufferedReader;
import academy.kovalevskyi.javadeepdive.week0.day3.RequestException;
import java.io.IOException;
import java.util.*;

public class HttpRequestBuilder {
  private static final String CONTENT_TYPE = "Content-Type";

  static HttpRequest build(StdBufferedReader bufferedReader) throws IOException, RequestException {
    HttpRequest.Builder builder = new HttpRequest.Builder();

    String[] requestHeaders = String.valueOf(bufferedReader.readLine()).split(" ");

    Optional.ofNullable(requestHeaders[0]).flatMap(HttpMethod::from).ifPresent(builder::method);
    Optional.ofNullable(requestHeaders[1]).filter(e -> !e.isEmpty()).ifPresent(builder::path);
    Optional.ofNullable(requestHeaders[2])
        .flatMap(HttpVersion::from)
        .ifPresent(builder::httpVersion);
    Optional.ofNullable(getHeaders(bufferedReader).get(CONTENT_TYPE))
        .flatMap(ContentType::from)
        .ifPresent(builder::contentType);

    StringBuilder body = new StringBuilder();
    while (bufferedReader.hasNext()) {
      char[] line = bufferedReader.readLine();
      body.append(line);
    }

    return builder.body(body.toString()).build();
  }

  private static Map<String, String> getHeaders(StdBufferedReader bufferedReader)
      throws IOException {
    Map<String, String> result = new HashMap<>();
    int counter = 2;
    while (counter > 0) {
      char[] line = bufferedReader.readLine();
      if (line.length == 0) {
        counter--;
      } else {
        String[] header = String.valueOf(line).split(":");
        result.put(header[0], header[1]);
        counter++;
      }
    }
    return result;
  }
}
