package academy.kovalevskyi.javadeepdive.week0.day3;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import org.junit.jupiter.api.Test;


class JoinRequestTest {
  @Test
  public void should_joinTwoCsv_when_existsValuesWithSameColumn() throws RequestException {
    // given
    Csv from =
        new Csv.Builder()
            .header("a", "b", "c")
            .values(
                new String[][] {
                  {"a1", "b1", "c1"},
                  {"a1", "b1", "c2"}
                })
            .build();
    Csv on =
        new Csv.Builder()
            .header("d", "e", "c")
            .values(
                new String[][] {
                  {"d1", "e1", "c1"},
                  {"d2", "e2", "c1"},
                  {"d1", "e1", "c1"},
                  {"d1", "e2", "c2"},
                })
            .build();
    JoinRequest joinRequest = new JoinRequest(from, on, "c");
    // when
    Csv result = joinRequest.execute();
    // then
    Csv expected =
        new Csv.Builder()
            .header("a", "b", "c", "d", "e")
            .values(
                new String[][] {
                  {"a1", "b1", "c2", "d1", "e2"},
                  {"a1", "b1", "c1", "d1", "e1"},
                  {"a1", "b1", "c1", "d2", "e2"},
                })
            .build();
    assertThat(result).isEqualTo(expected);
  }
}
