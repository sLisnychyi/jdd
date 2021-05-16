package academy.kovalevskyi.javadeepdive.week0.day3;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import org.junit.jupiter.api.Test;


class SelectRequestTest {
  @Test
  public void should_selectValues_when_requestedFromCsv() throws RequestException {
    // given
    Csv csv =
        new Csv.Builder()
            .header("a", "b", "c")
            .values(
                new String[][] {
                  {"a1", "b1", "c1"},
                  {"a1", "b1", "c2"}
                })
            .build();
    SelectRequest selectRequest =
        new SelectRequest(
            csv,
            new Selector.Builder().value("b1").fieldName("b").build(),
            new String[] {"a", "c"});
    // when
    String[][] result = selectRequest.execute();
    // then
    assertThat(result)
        .isEqualTo(
            new String[][] {
              {"a1", "c1"},
              {"a1", "c2"}
            });
  }
}
