package academy.kovalevskyi.javadeepdive.week0.day2;

import java.util.Arrays;
import java.util.Objects;

import static java.util.Arrays.stream;

public record Csv(String[] headers, String[][] values) {

  public static class Builder {
    private String[] headers;
    private String[][] values;

    public Builder header(String... headers) {
      this.headers = headers;
      return this;
    }

    public Builder values(String[][] values) {
      if(headers != null && headers.length != values[0].length) throw new InvalidCsvLineLengthException();
      if(stream(values).anyMatch(e -> e.length != values[0].length)) throw new InvalidCsvLineLengthException();
      this.values = values;
      return this;
    }

    public Csv build() {
      return new Csv(headers, values);
    }
  }

  public boolean withHeader() {
    return Objects.nonNull(headers);
  }

  @Override
  public boolean equals(Object o) {
    if(o == this) return true;
    if(o == null || o.getClass() != getClass()) return false;
    Csv that = (Csv) o;
    return Arrays.equals(headers, that.headers()) && Arrays.deepEquals(values, that.values());
  }

  @Override
  public int hashCode() {
    int result = Arrays.hashCode(headers);
    result = 31 * result + Arrays.deepHashCode(values);
    return result;
  }
}
