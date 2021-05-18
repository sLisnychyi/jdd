package academy.kovalevskyi.javadeepdive.week0.day3;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InsertRequest extends AbstractRequest<Csv> {
  private final String[] line;

  protected InsertRequest(Csv csv, String[] line) {
    super(csv);
    this.line = line;
  }

  @Override
  protected Csv execute() throws RequestException {
    if (csv.header().length != line.length) {
      throw new RequestException(
          "invalid column length[%s] in the inserted line[%s], should be[%s].",
          line.length, line, csv.header().length);
    }
    List<String[]> values = new ArrayList<>();
    if (csv.values() != null) {
      Collections.addAll(values, csv.values());
    }
    values.add(line);
    return new Csv.Builder().header(csv.header()).values(values.toArray(new String[][] {})).build();
  }

  public static class Builder {
    private String[] line;
    private Csv csv;

    public Builder insert(String[] line) {
      this.line = line;
      return this;
    }

    public Builder to(Csv csv) {
      this.csv = csv;
      return this;
    }

    public InsertRequest build() {
      return new InsertRequest(csv, line);
    }
  }
}
