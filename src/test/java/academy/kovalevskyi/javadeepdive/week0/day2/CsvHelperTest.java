package academy.kovalevskyi.javadeepdive.week0.day2;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class CsvHelperTest {

  @Test
  public void should_parseFile_when_headersExists() throws FileNotFoundException {
    // when
    Csv csv =
        CsvHelper.parseFile(
            new InputStreamReader(
                new ByteArrayInputStream(
                    "a,b,c\na1,b1,c1\na2,b2,c3".getBytes(StandardCharsets.UTF_8))),
            true,
            ',');
    // then
    assertThat(csv.header()).isEqualTo(new String[] {"a", "b", "c"});
    assertThat(csv.values()).isEqualTo(new String[][] {{"a1", "b1", "c1"}, {"a2", "b2", "c3"}});
  }
}
