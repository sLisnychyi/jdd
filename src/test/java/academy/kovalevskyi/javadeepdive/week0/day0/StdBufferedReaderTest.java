package academy.kovalevskyi.javadeepdive.week0.day0;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StdBufferedReaderTest {

  private static Stream<Arguments> args() {
    return Stream.of(
        Arguments.of("hello \n world", new String[] {"hello ", " world"}),
        Arguments.of("hello \r world", new String[] {"hello ", " world"}),
        Arguments.of("hello \n\r world", new String[] {"hello ", "", " world"}),
        Arguments.of("hellosdfgaw\n\r world", new String[] {"hellosdfgaw", "", " world"}),
        Arguments.of("not blank", new String[] {"not blank"}));
  }

  @ParameterizedTest
  @MethodSource("args")
  public void should_readLine_whenLineExits(String input, String[] expected) throws IOException {
    StdBufferedReader reader =
        new StdBufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
    for (String s : expected) {
      char[] result = reader.readLine();
      Assertions.assertThat(result).isEqualTo(s.toCharArray());
    }
  }
}
