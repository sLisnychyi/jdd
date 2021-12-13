package academy.kovalevskyi.javadeepdive.week0.day2;

import academy.kovalevskyi.javadeepdive.week0.day0.StdBufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CsvHelper {
  private static final char DEFAULT_DELIMITER = ',';

  private CsvHelper() {}

  public static Csv parseCsvFrom(Reader reader) throws IOException {
    return parseCsvFrom(reader, false, DEFAULT_DELIMITER);
  }

  public static Csv parseCsvFrom(Reader reader, boolean withHeader, char delimiter)
      throws IOException {
    Csv.Builder csvBuilder = new Csv.Builder();
    try (StdBufferedReader bufferedReader = new StdBufferedReader(reader)) {
      if (withHeader && bufferedReader.hasNext()) {
        String[] header = new String(bufferedReader.readLine()).split(String.valueOf(delimiter));
        csvBuilder.header(header);
      }
      List<List<String>> values = new ArrayList<>();
      while (bufferedReader.hasNext()) {
        char[] line = bufferedReader.readLine();
        List<String> lineValues = new ArrayList<>();
        boolean quote = false;
        String value = "";
        for (char elem : line) {
          if (elem == DEFAULT_DELIMITER && !quote) {
            lineValues.add(value);
            value = "";
          } else if (elem == '\"') {
            quote = !quote;
          } else {
            value += elem;
          }
        }
        if (value.length() > 0) lineValues.add(value);
        values.add(lineValues);
      }
      csvBuilder.values(
          values.stream()
                  .map(e -> e.toArray(String[]::new))
                  .toArray(String[][]::new));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return csvBuilder.build();
  }

  public static void writeCsvTo(Writer writer, Csv csv, char delimiter) throws IOException {
    try (writer) {
      if (csv.withHeader()) {
        writer.write(String.join(String.valueOf(delimiter), csv.headers()));
        writer.write("\r");
        writer.write(System.lineSeparator());
      }
      for (int i = 0; i < csv.values().length; i++) {
        writer.write(String.join(String.valueOf(delimiter), csv.values()[i]));
        if (i != csv.values().length - 1) {
          writer.write("\r");
          writer.write(System.lineSeparator());
        }
      }
    }
  }
}
