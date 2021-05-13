package academy.kovalevskyi.javadeepdive.week0.day2;

import academy.kovalevskyi.javadeepdive.week0.day0.StdBufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

// https://drive.google.com/file/d/1Jq-rt-lnfNOs4sVsuY5zrcTKfLjBJ4Qs/view?usp=sharing
public class CsvHelper {
  private static final char DEFAULT_DELIMITER = ',';

  private CsvHelper() {}

  public static Csv parseFile(Reader reader) throws FileNotFoundException {
    return parseFile(reader, false, DEFAULT_DELIMITER);
  }

  public static Csv parseFile(Reader reader, boolean withHeader, char delimiter)
      throws FileNotFoundException {
    Csv.Builder csvBuilder = new Csv.Builder();
    try (StdBufferedReader bufferedReader = new StdBufferedReader(reader, 100)) {
      if (withHeader) {
        String[] header = getValues(delimiter, bufferedReader.readLine());
        csvBuilder.header(header);
      }
      List<String[]> values = new ArrayList<>();
      while (bufferedReader.hasNext()) {
        char[] line = bufferedReader.readLine();
        if (line != null && line.length > 0) {
          values.add(getValues(delimiter, line));
        }
      }
      csvBuilder.values(values.toArray(new String[][] {}));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return csvBuilder.build();
  }

  private static String[] getValues(char delimiter, char[] line) throws IOException {
    List<String> result = new ArrayList<>();
    StringBuilder value = new StringBuilder();
    for (char c : line) {
      if (c == delimiter) {
        result.add(value.toString());
        value = new StringBuilder();
      } else {
        value.append(c);
      }
    }
    if (!value.isEmpty()) {
      result.add(value.toString());
    }
    return result.toArray(new String[] {});
  }

  public static void writeCsv(Writer writer, Csv csv, char delimiter) throws IOException {
    try (writer) {
      if (csv.withHeader()) {
        writer.write(String.join(String.valueOf(delimiter), csv.header()));
        writer.write("\n");
      }
      for (String[] line : csv.values()) {
        writer.write(String.join(String.valueOf(delimiter), line));
        writer.write("\n");
      }
    }
  }
}
