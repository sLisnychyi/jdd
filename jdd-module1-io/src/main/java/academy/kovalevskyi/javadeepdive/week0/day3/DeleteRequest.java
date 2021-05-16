package academy.kovalevskyi.javadeepdive.week0.day3;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DeleteRequest extends AbstractRequest<Csv> {
  private final Csv csv;
  private final Selector whereSelector;

  protected DeleteRequest(Csv csv, Selector whereSelector) {
    super(csv);
    this.csv = csv;
    this.whereSelector = whereSelector;
  }

  @Override
  protected Csv execute() throws RequestException {
    int columnIndex = getColumnIndex(csv.header(), whereSelector.fieldName());
    if (columnIndex == -1) {
      throw new RequestException("Unable to find column = %s.", whereSelector.fieldName());
    }
    String[][] values =
        Arrays.stream(csv.values())
            .filter(e -> !e[columnIndex].equals(whereSelector.value()))
            .collect(Collectors.toList())
            .toArray(new String[][] {});
    return new Csv.Builder().header(csv.header()).values(values).build();
  }

  public static class Builder {
    private Csv csv;
    private Selector whereSelector;

    public Builder where(Selector selector) {
      this.whereSelector = selector;
      return this;
    }

    public Builder from(Csv csv) {
      this.csv = csv;
      return this;
    }

    public DeleteRequest build() {
      return new DeleteRequest(csv, whereSelector);
    }
  }
}
