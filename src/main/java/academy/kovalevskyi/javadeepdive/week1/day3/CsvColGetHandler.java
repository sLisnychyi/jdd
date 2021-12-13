package academy.kovalevskyi.javadeepdive.week1.day3;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import academy.kovalevskyi.javadeepdive.week1.day2.ContentType;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpMethod;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpRequest;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpRequestsHandler;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class CsvColGetHandler implements HttpRequestsHandler {
  private final Csv csv;
  private final String colName;
  private final String path;

  public CsvColGetHandler(Csv csv, String colName, String path) {
    this.csv = csv;
    this.colName = colName;
    this.path = path;
  }

  @Override
  public String path() {
    return path;
  }

  @Override
  public HttpMethod method() {
    return HttpMethod.GET;
  }

  @Override
  public HttpResponse process(HttpRequest request) {
    return Optional.of(getColumnIndex(csv.headers(), colName))
        .filter(e -> e >= 0)
        .map(
            e ->
                "["
                    + Arrays.stream(csv.values())
                        .map(j -> "\"" + j[e] + "\"")
                        .collect(Collectors.joining(","))
                    + "]")
        .map(
            body ->
                new HttpResponse.Builder()
                    .contentType(ContentType.APPLICATION_JSON)
                    .body(body)
                    .build())
        .orElse(HttpResponse.ERROR_404);
  }

  private int getColumnIndex(String[] headers, String colName) {
    for (int i = 0; i < headers.length; i++) {
      if (headers[i].equalsIgnoreCase(colName)) {
        return i;
      }
    }
    return -1;
  }
}
