package academy.kovalevskyi.javadeepdive.week0.day3;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import org.junit.jupiter.api.Test;


class UpdateRequestTest {

  @Test
  public void should_updateCsvField_when_fieldExists() throws RequestException {
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
    UpdateRequest updateRequest =
        new UpdateRequest(
            csv,
            new Selector.Builder().fieldName("a").value("a1").build(),
            new Selector.Builder().fieldName("b").value("new_b").build());
    // when
    Csv result = updateRequest.execute();
    // then
    Csv expected =
        new Csv.Builder()
            .header("a", "b", "c")
            .values(
                new String[][] {
                  {"a1", "new_b", "c1"},
                  {"a1", "new_b", "c2"}
                })
            .build();
    assertThat(result).isEqualTo(expected);
  }
}
