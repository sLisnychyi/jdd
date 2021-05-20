package academy.kovalevskyi.javadeepdive.week1.day3;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpRequest;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpResponse;
import org.junit.jupiter.api.Test;

class CsvColGetHandlerTest {

  @Test
  public void should_processHttpRequest_when_columnExists() {
    // given
    CsvColGetHandler handler =
        new CsvColGetHandler(
            new Csv.Builder()
                .header("a", "b", "c")
                .values(
                    new String[][] {
                      {"a1", "b1", "c1"},
                      {"a2", "b1", "c1"}
                    })
                .build(),
            "a",
            "/");
    // when
    HttpResponse response = handler.process(new HttpRequest.Builder().build());
    // then
    assertThat(response.body()).isEqualTo("[\"a1\",\"a2\"]");
  }

  @Test
  public void should_processHttpRequest_when_columnExistsWithEmptyValues() {
    // given
    CsvColGetHandler handler =
        new CsvColGetHandler(
            new Csv.Builder().header("a", "b", "c").values(new String[][] {}).build(), "a", "/");
    // when
    HttpResponse response = handler.process(new HttpRequest.Builder().build());
    // then
    assertThat(response.body()).isEqualTo("[]");
  }
}
