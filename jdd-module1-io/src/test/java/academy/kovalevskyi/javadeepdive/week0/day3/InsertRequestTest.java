package academy.kovalevskyi.javadeepdive.week0.day3;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import org.junit.jupiter.api.Test;


class InsertRequestTest {

  @Test
  public void should_addNewLine_when_valuesExists() throws RequestException {
    // given
    Csv csv = new Csv.Builder().header("a", "b", "c").build();
    InsertRequest request = new InsertRequest(csv, new String[] {"a1", "b1", "c1"});
    // when
    Csv result = request.execute();
    // then
    Csv expected =
        new Csv.Builder().header("a", "b", "c").values(new String[][] {{"a1", "b1", "c1"}}).build();
    assertThat(result).isEqualTo(expected);
  }
}
