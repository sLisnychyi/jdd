package academy.kovalevskyi.javadeepdive.week0.day3;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import org.junit.jupiter.api.Test;


class DeleteRequestTest {

  @Test
  public void should_throwException_when_noSuchHeaderExists() {
    // given
    Csv csv = new Csv.Builder().header("a", "b", "c").build();
    Selector selector = new Selector.Builder().fieldName("d").build();
    DeleteRequest deleteRequest = new DeleteRequest(csv, selector);
    // when
    // then
    assertThrows(RequestException.class, deleteRequest::execute);
  }

  @Test
  public void should_removeLine_when_headerExists() throws RequestException {
    // given
    Csv csv =
        new Csv.Builder()
            .header("a", "b", "c")
            .values(
                new String[][] {
                  {"a1", "b1", "c1"},
                  {"a2", "b2", "c2"},
                })
            .build();
    Selector selector = new Selector.Builder().fieldName("c").value("c1").build();
    DeleteRequest deleteRequest = new DeleteRequest(csv, selector);
    // when
    Csv result = deleteRequest.execute();
    // then
    assertThat(result.values()).isEqualTo(new String[][] {{"a2", "b2", "c2"}});
  }
}
