package academy.kovalevskyi.javadeepdive.week0.day3;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UpdateRequest extends AbstractRequest<Csv> {
  private final Csv csv;
  private final Selector selector;
  private final Selector updateSelector;

  protected UpdateRequest(Csv csv, Selector selector, Selector updateSelector) {
    super(csv);
    this.csv = csv;
    this.selector = selector;
    this.updateSelector = updateSelector;
  }

  @Override
  protected Csv execute() throws RequestException {
    Optional<Integer> whereColumnIndex =
        Optional.ofNullable(selector)
            .map(selector -> getColumnIndex(csv.headers(), selector.fieldName()));
    int updateColumnIndex = getColumnIndex(csv.headers(), updateSelector.fieldName());
    if (updateColumnIndex < 0) {
      throw new RequestException(
          "invalid update request filed name = %s", updateSelector.fieldName());
    }
    List<String[]> values = new ArrayList<>();
    for (int i = 0; i < csv.values().length; i++) {
      String[] value = csv.values()[i];
      if (whereColumnIndex.map(index -> value[index].equals(selector.value())).orElse(true)) {
        value[updateColumnIndex] = updateSelector.value();
      }
      values.add(value);
    }
    return new Csv.Builder().header(csv.headers()).values(values.toArray(new String[][] {})).build();
  }

  public static class Builder {
    private Csv csv;
    private Selector selector;
    private Selector updateSelector;

    public Builder where(Selector selector) {
      this.selector = selector;
      return this;
    }

    public Builder update(Selector updateSelector) {
      this.updateSelector = updateSelector;
      return this;
    }

    public Builder from(Csv csv) {
      this.csv = csv;
      return this;
    }

    public UpdateRequest build() {
      return new UpdateRequest(csv, selector, updateSelector);
    }
  }
}
