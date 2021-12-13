//package academy.kovalevskyi.javadeepdive.week0.day0;
//
//import java.io.*;
//import java.util.Objects;
//import java.util.Random;
//import java.util.stream.IntStream;
//import java.util.stream.Stream;
//
//import academy.kovalevskyi.javadeepdive.StreamProvider;
//import com.google.common.truth.Truth;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.mockito.Mockito;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//
//class StdBufferedReaderTest {
//
////  private final String fourEmptyLines = String.format("%n%n%n%n");
//
////  private static Stream<Arguments> args() {
////    return Stream.of(
////        Arguments.of("hello \n world", new String[] {"hello ", " world"}),
////        Arguments.of("hello \r world", new String[] {"hello ", " world"}),
////        Arguments.of("hello \n\r world", new String[] {"hello ", "", " world"}),
////        Arguments.of("hellosdfgaw\n\r world", new String[] {"hellosdfgaw", "", " world"}),
////        Arguments.of("not blank", new String[] {"not blank"}));
////  }
////
////  @ParameterizedTest
////  @MethodSource("args")
////  public void should_readLine_whenLineExits(String input, String[] expected) throws IOException {
////    StdBufferedReader reader =
////        new StdBufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
////    for (String s : expected) {
////      char[] result = reader.readLine();
////      Assertions.assertThat(result).isEqualTo(s.toCharArray());
////    }
////  }
//
//  private final String empty;
//  private final String smallInputString;
//  private final String oneLine;
//  private final String fourEmptyLines;
//  private final String fourLines;
//  private final String longLine;
//  private final String superLongLine;
//
//  public StdBufferedReaderTest() {
//    StringBuilder longString = new StringBuilder(5000000);
//    String alphabet = "1234567890!@#$%^&*()abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZё";
//    IntStream.range(0, 300000).forEach((i) -> {
//      if ((new Random()).nextInt() < 10) {
//        longString.append(System.lineSeparator());
//      } else {
//        int lineSize = (new Random()).nextInt(alphabet.length());
//
//        for(int j = 0; j < lineSize; ++j) {
//          longString.append(alphabet.charAt((new Random()).nextInt(alphabet.length())));
//        }
//      }
//
//    });
//    this.superLongLine = longString.toString();
//    this.empty = "";
//    this.smallInputString = "Очень короткая строка.";
//    this.oneLine = "asdfasdfsafasdfsad asfasdfsadfsdafsda";
//    this.fourEmptyLines = String.format("%n%n%n%n");
//    this.fourLines = "Этих несколько строк будут щупать ваш мозг не один час!\n" +
//            "Но необходимо верить в свои силы!\n" +
//            "Сделайте перерыв, погладьте зверушку и расскажите ей о своей проблеме.\n" +
//            "Все у вас получиться!\n";
//    this.longLine = "sadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sdsadfasdfsadfh;asfhda;sd";
//  }
//
//  @Test
//  public void testClose() throws IOException {
//    Reader reader = (Reader) Mockito.mock(Reader.class);
//    StdBufferedReader bufferedReader = new StdBufferedReader(reader);
//    bufferedReader.close();
//    ((Reader)Mockito.verify(reader)).close();
//  }
//
//  @Test
//  public void testCloseable() throws IOException {
//    Reader reader = (Reader)Mockito.mock(Reader.class);
//    StdBufferedReader bufferedReader = new StdBufferedReader(reader);
//
//    try {
//      System.out.println("не уверен в том, что происходит в этом тесте...");
//    } catch (Throwable var6) {
//      try {
//        bufferedReader.close();
//      } catch (Throwable var5) {
//        var6.addSuppressed(var5);
//      }
//
//      throw var6;
//    }
//
//    bufferedReader.close();
//    ((Reader)Mockito.verify(reader)).close();
//  }
//
//  @Test
//  public void hasNextWhenEmpty() throws Exception {
//    StdBufferedReader bufferedReader = new StdBufferedReader(StreamProvider.getInputStreamFrom(this.empty), 256);
//    Truth.assertWithMessage("Checking if hasNext returns false for empty file").that(bufferedReader.hasNext()).isFalse();
//  }
//
//  @Test
//  public void hasNextWithSmallLine() throws Exception {
//    StdBufferedReader stdBufR = new StdBufferedReader(StreamProvider.getInputStreamFrom(this.smallInputString), 50);
//    Truth.assertWithMessage("Has next, when file contains one line?").that(stdBufR.hasNext()).isTrue();
//    stdBufR.readLine();
//    Truth.assertWithMessage("Has next after `readLine()`, when file contains one line?").that(stdBufR.hasNext()).isFalse();
//  }
//
//  @ParameterizedTest
//  @ValueSource(
//          ints = {99999, 100000000, 5555555, 6666666, 8974525}
//  )
//  public void hasNextWithSmallLineAndHugeBuffer(int bufSize) throws IOException {
//    StdBufferedReader stdBufR = new StdBufferedReader(StreamProvider.getInputStreamFrom(this.smallInputString), bufSize);
//    Truth.assertWithMessage("Has next, when file contains one line and huge buffer?").that(stdBufR.hasNext()).isTrue();
//    stdBufR.readLine();
//    Truth.assertWithMessage("Has next after `readLine()`, when file contains one line and huge buffer?").that(stdBufR.hasNext()).isFalse();
//  }
//
//  @Test
//  public void hasNextWhen1Liner() throws Exception {
//    StdBufferedReader bufferedReader = new StdBufferedReader(StreamProvider.getInputStreamFrom(this.oneLine), 256);
//    Truth.assertWithMessage("Checking if hasNext returns false for file with 1 line").that(bufferedReader.hasNext()).isTrue();
//  }
//
//  @ParameterizedTest
//  @ValueSource(
//          ints = {2, 4, 5, 6, 7, 8, 9, 10, 11}
//  )
//  public void hasNextWithFourLinesAndSmallBuffer(int bufSize) throws IOException {
//    StdBufferedReader stdBufR = new StdBufferedReader(StreamProvider.getInputStreamFrom(this.fourLines), bufSize);
//
//    for(int i = 1; i <= 4; ++i) {
//      Truth.assertWithMessage("Has next, when file contains four lines and small buffer?").that(stdBufR.hasNext()).isTrue();
//      stdBufR.readLine();
//    }
//
//    Truth.assertWithMessage("Has next after four `hasNext()`?").that(stdBufR.hasNext()).isFalse();
//  }
//
//  @Test
//  public void hasNextWithFourLines() throws Exception {
//    StdBufferedReader stdBufR = new StdBufferedReader(StreamProvider.getInputStreamFrom(this.fourLines));
//    Truth.assertWithMessage("Hase next, when file contains four line?").that(stdBufR.hasNext()).isTrue();
//
//    for(int i = 0; i < 3; ++i) {
//      System.out.println(stdBufR.readLine());
//      Truth.assertWithMessage("Has next after `readLine()`, when file contains four line?").that(stdBufR.hasNext()).isTrue();
//    }
//
//    stdBufR.readLine();
//    Truth.assertWithMessage("It is really has next after four iteration of `readLine()`, when file contains only four lines?!").that(stdBufR.hasNext()).isFalse();
//  }
//
//  @Test
//  public void callConstructorWithIllegalBufferSize() {
//    String message = String.format("Buffer size should be positive number and 2 bytes or above!%nIllegalArgumentException should be thrown!");
//    org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
//      new StdBufferedReader(InputStreamReader.nullReader(), 1);
//    }, message);
//    org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
//      new StdBufferedReader(InputStreamReader.nullReader(), 0);
//    }, message);
//    org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
//      new StdBufferedReader(InputStreamReader.nullReader(), -1);
//    }, message);
//  }
//
//  @Test
//  public void callConstructorWithNullReader() {
//    org.junit.jupiter.api.Assertions.assertThrows(NullPointerException.class, () -> {
//      new StdBufferedReader((Reader)null);
//    }, String.format("Reader instance should be non-null!%nNullPointerException should be thrown!"));
//  }
//
//  @Test
//  public void testReadLineWithEmptyLine() {
//    char[] actualResult = (char[]) assertDoesNotThrow(() -> {
//      return (new StdBufferedReader(StreamProvider.getInputStreamFrom(this.empty), 10)).readLine();
//    });
//    Truth.assertWithMessage("It was one empty line.").that(actualResult).isNull();
//  }
//
//  @Test
//  public void readLineWithOneLine() throws Exception {
//    char[] expected = this.oneLine.toCharArray();
//    StdBufferedReader bufferedReader = new StdBufferedReader(StreamProvider.getInputStreamFrom(this.oneLine), 256);
//    Truth.assertWithMessage(String.format("Text in file: %s%nReading one line", String.valueOf(expected))).that(bufferedReader.readLine()).isEqualTo(expected);
//  }
//
//  @Test
//  public void readLineWith4EmptyLines() throws Exception {
//    String incomingText = "Incoming text was: \"\\n\\n\\n\\n\" — Unix, \"\\r\\n\\r\\n\\r\\n\\r\\n\" — Windows";
//    String message = String.format("%s, \nBuffer size is: DEFAULT", incomingText);
//    BufferedReader javaBufferedReader = new BufferedReader(StreamProvider.getInputStreamFrom(this.fourEmptyLines));
//    StdBufferedReader stdBufferedReader = (StdBufferedReader) assertDoesNotThrow(() -> {
//      return new StdBufferedReader(StreamProvider.getInputStreamFrom(this.fourEmptyLines));
//    }, message);
//
//    for(int i = 0; i < 6; ++i) {
//      Objects.requireNonNull(stdBufferedReader);
//      char[] actual = (char[]) assertDoesNotThrow(stdBufferedReader::readLine);
//      String expected = javaBufferedReader.readLine();
//      if (expected != null) {
//        Truth.assertWithMessage(String.format("Compare the behavior of javaBufferedReader and stdBufferedReader. Reading #%d of 6.", i + 1)).that(actual).isEqualTo(expected.toCharArray());
//      } else {
//        Truth.assertWithMessage(String.format("Compare the behavior of javaBufferedReader and stdBufferedReader. Reading #%d of 6.", i + 1)).that(actual).isNull();
//      }
//    }
//
//  }
//
//  @Test
//  public void readLineWithLongLineAndSmallBuffer() throws Exception {
//    int bufSize = 2;
//    String message = String.format("Line in file is: %n\"%s\", %nBuffer size is: %d", this.longLine, bufSize);
//    BufferedReader javaBufferedReader = assertDoesNotThrow(() ->
//            new BufferedReader(StreamProvider.getInputStreamFrom(this.longLine), bufSize), message);
//    StdBufferedReader bufferedReader = assertDoesNotThrow(() ->
//            new StdBufferedReader(StreamProvider.getInputStreamFrom(this.longLine), bufSize), message);
//    char[] expectedResult = javaBufferedReader.readLine().toCharArray();
//    Objects.requireNonNull(bufferedReader);
//    char[] actualResult = assertDoesNotThrow(bufferedReader::readLine, message);
//    Truth.assertWithMessage(message).that(actualResult).isEqualTo(expectedResult);
//  }
//
//  @ParameterizedTest
//  @ValueSource(
//          ints = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11}
//  )
//  public void testReadLineWithSmallBufferAndFourLines(int bufSize) throws IOException {
//    String message = String.format("Lines in file was: %n\"%s\", %nBuffer size is: %d", this.fourLines, bufSize);
//    char[] expectedResult = (new BufferedReader(StreamProvider.getInputStreamFrom(this.fourLines), bufSize)).readLine().toCharArray();
//    char[] actualResult = (char[]) assertDoesNotThrow(() -> {
//      return (new StdBufferedReader(StreamProvider.getInputStreamFrom(this.fourLines), bufSize)).readLine();
//    }, message);
//    Truth.assertWithMessage(message).that(actualResult).isEqualTo(expectedResult);
//  }
//
//  @ParameterizedTest
//  @ValueSource(
//          ints = {99999, 100000000, 5555555, 6666666, 8974525}
//  )
//  public void testReadLineWithSmallLineAndHugeBuffer(int bufSize) throws IOException {
//    String message = String.format("Line in file is: %n\"%s\", %nBuffer size is: %d", this.smallInputString, bufSize);
//    char[] expectedResult = (new BufferedReader(StreamProvider.getInputStreamFrom(this.smallInputString), bufSize)).readLine().toCharArray();
//    char[] actualResult = (char[]) assertDoesNotThrow(() -> {
//      return (new StdBufferedReader(StreamProvider.getInputStreamFrom(this.smallInputString), bufSize)).readLine();
//    }, message);
//    Truth.assertWithMessage(message).that(actualResult).isEqualTo(expectedResult);
//  }
//
//  @Test
//  public void testReadLineWithOneSmallLine() throws IOException {
//    int bufSize = 22;
//    String message = String.format("Line in file was: %n\"%s\", %nBuffer size was: %d", this.smallInputString, Integer.valueOf(bufSize));
//    char[] expectedResult = (new BufferedReader(StreamProvider.getInputStreamFrom(this.smallInputString), bufSize)).readLine().toCharArray();
//    char[] actualResult = (char[]) assertDoesNotThrow(() -> {
//      return (new StdBufferedReader(StreamProvider.getInputStreamFrom(this.smallInputString), bufSize)).readLine();
//    }, message);
//    Truth.assertWithMessage(message).that(actualResult).isEqualTo(expectedResult);
//  }
//
//  @Test
//  public void testReadLineWithFourLinesAndDefaultBufferSize() throws IOException {
//    String message = String.format("Lines in file are: \n\"%s\", \nBuffer size is: DEFAULT", this.fourLines);
//    BufferedReader bufR = new BufferedReader(StreamProvider.getInputStreamFrom(this.fourLines));
//    StdBufferedReader stdBufR = (StdBufferedReader) assertDoesNotThrow(() -> {
//      return new StdBufferedReader(StreamProvider.getInputStreamFrom(this.fourLines));
//    }, message);
//
//    for(int i = 0; i < 4; ++i) {
//      char[] expectedResult = bufR.readLine().toCharArray();
//      Objects.requireNonNull(stdBufR);
//      char[] actualResult = (char[]) assertDoesNotThrow(stdBufR::readLine);
//      Truth.assertWithMessage(message).that(actualResult).isEqualTo(expectedResult);
//    }
//
//  }
//
//  @Test
//  public void readLineWithWarAndPeace() throws Exception {
//    StdBufferedReader stdReader = new StdBufferedReader(StreamProvider.getInputStreamFrom(this.superLongLine));
//    BufferedReader javaReader = new BufferedReader(StreamProvider.getInputStreamFrom(this.superLongLine));
//    int var3 = 0;
//
//    while(javaReader.ready()) {
//      char[] expectedResult = null;
//      String stringExpectedLine = javaReader.readLine();
//      if (stringExpectedLine != null) {
//        expectedResult = stringExpectedLine.toCharArray();
//      }
//
//      char[] actualResult = stdReader.readLine();
//      Truth.assertWithMessage(String.format("Reading war and peace, line number: %d", var3++)).that(actualResult).isEqualTo(expectedResult);
//    }
//
//  }
//
//  @Test
//  public void testReadLineWithEmptyLinesBeforeString() throws IOException {
//    this.checkWithDifficultString(this.fourEmptyLines + this.smallInputString, 5);
//  }
//
//  @Test
//  public void testReadLineWithEmptyLinesBetweenStrings() throws IOException {
//    this.checkWithDifficultString(this.smallInputString + this.fourEmptyLines + this.smallInputString, 5);
//  }
//
//  @Test
//  public void testReadLineWithEmptyLinesAfterString() throws IOException {
//    this.checkWithDifficultString(this.smallInputString + this.fourEmptyLines, 4);
//  }
//
//  private void checkWithDifficultString(String inputString, int numberOfLines) throws IOException {
//    String message = String.format("Lines in file are: %n\"%s\", %nBuffer size is: DEFAULT", inputString);
//    BufferedReader bufR = new BufferedReader(StreamProvider.getInputStreamFrom(inputString), 50);
//    StdBufferedReader stdBufR = (StdBufferedReader) assertDoesNotThrow(() -> {
//      return new StdBufferedReader(StreamProvider.getInputStreamFrom(inputString), 50);
//    }, message);
//
//    for(int i = 0; i < numberOfLines; ++i) {
//      char[] expectedResult = bufR.readLine().toCharArray();
//      Objects.requireNonNull(stdBufR);
//      char[] actualResult = (char[]) assertDoesNotThrow(stdBufR::readLine, message);
//      Truth.assertWithMessage("Number of the reading string: " + (i + 1)).that(actualResult).isEqualTo(expectedResult);
//    }
//
//  }
//
//
//
//
//
//
////
////  @Test
////  public void readLineWith4EmptyLines() throws Exception {
////    String incomingText = "Incoming text was: \"\\n\\n\\n\\n\" — Unix, \"\\r\\n\\r\\n\\r\\n\\r\\n\" — Windows";
////    String message = String.format("%s, \nBuffer size is: DEFAULT", incomingText);
////    BufferedReader javaBufferedReader = new BufferedReader(StreamProvider.getInputStreamFrom(this.fourEmptyLines));
////    StdBufferedReader stdBufferedReader = org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> new StdBufferedReader(StreamProvider.getInputStreamFrom(this.fourEmptyLines)), message);
////
////    for(int i = 0; i < 6; ++i) {
////      Objects.requireNonNull(stdBufferedReader);
////      char[] actual = org.junit.jupiter.api.Assertions.assertDoesNotThrow(stdBufferedReader::readLine);
////      String expected = javaBufferedReader.readLine();
////      if (expected != null) {
////        Truth.assertWithMessage(String.format("Compare the behavior of javaBufferedReader and stdBufferedReader. Reading #%d of 6.", i + 1)).that(actual).isEqualTo(expected.toCharArray());
////      } else {
////        Truth.assertWithMessage(String.format("Compare the behavior of javaBufferedReader and stdBufferedReader. Reading #%d of 6.", i + 1)).that(actual).isNull();
////      }
////    }
////
////  }
//}
