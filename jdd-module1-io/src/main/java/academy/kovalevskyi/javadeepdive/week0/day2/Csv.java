package academy.kovalevskyi.javadeepdive.week0.day2;

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
  public int hashCode() {
    return 0;
  }

  @Override
  public boolean equals(Object obj) {
    return false;
  }
}
