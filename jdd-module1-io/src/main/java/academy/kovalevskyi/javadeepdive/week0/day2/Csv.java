package academy.kovalevskyi.javadeepdive.week0.day2;

import java.util.Arrays;
import java.util.Objects;

public record Csv(String[] header, String[][] values) {

  public static class Builder {
    private String[] header;
    private String[][] values;

    public Builder() {}

    public Builder(String[] header, String[][] values) {
      this.header = header;
      this.values = values;
    }

    public Builder header(String[] header) {
      this.header = header;
      return this;
    }

    public Builder values(String[][] values) {
      this.values = values;
      return this;
    }

    public Csv build() {
      return new Csv(header, values);
    }
  }

  public boolean withHeader() {
    return Objects.nonNull(header);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Csv csv = (Csv) o;
    return Arrays.equals(header, csv.header) && Arrays.deepEquals(values, csv.values);
  }

  @Override
  public int hashCode() {
    int result = Arrays.hashCode(header);
    result = 31 * result + Arrays.deepHashCode(values);
    return result;
  }
}
