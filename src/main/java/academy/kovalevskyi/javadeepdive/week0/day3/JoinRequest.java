package academy.kovalevskyi.javadeepdive.week0.day3;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JoinRequest extends AbstractRequest<Csv> {
  private final Csv from;
  private final Csv on;
  private final String by;

  protected JoinRequest(Csv from, Csv on, String by) {
    super(from);
    this.from = from;
    this.on = on;
    this.by = by;
  }

  @Override
  protected Csv execute() throws RequestException {
    int fromColumnIndex = getColumnIndex(from.headers(), by);
    int onColumnIndex = getColumnIndex(on.headers(), by);
    if (fromColumnIndex < 0 || onColumnIndex < 0) {
      throw new RequestException(
          "Column is not exists. from_csv[%s] on_csv[%s]", fromColumnIndex, onColumnIndex);
    }
    Set<List<String>> values = new HashSet<>();
    for (String[] value : from.values()) {
      String fromValue = value[fromColumnIndex];
      for (String[] onValues : on.values()) {
        String onValue = onValues[onColumnIndex];
        if (fromValue.equals(onValue)) {
          List<String> result = new ArrayList<>();
          result.addAll(Arrays.asList(value));
          result.addAll(
              Arrays.stream(onValues)
                  .filter(e -> !e.equals(fromValue))
                  .collect(Collectors.toList()));
          values.add(result);
        }
      }
    }
    String[][] valuesArray =
        values.stream().map(e -> e.toArray(String[]::new)).toArray(String[][]::new);
    return new Csv.Builder().header(getHeaders()).values(valuesArray).build();
  }

  private String[] getHeaders() {
    String[] result = new String[from.headers().length + on.headers().length - 1];
    System.arraycopy(from.headers(), 0, result, 0, from.headers().length);
    int counter = from.headers().length;
    for (int i = 0; i < on.headers().length; i++) {
      String headerValue = on.headers()[i];
      if (!headerValue.equals(by)) {
        result[counter++] = headerValue;
      }
    }
    return result;
  }

  public static class Builder {
    private Csv from;
    private Csv on;
    private String by;

    public Builder from(Csv from) {
      this.from = from;
      return this;
    }

    public Builder on(Csv on) {
      this.on = on;
      return this;
    }

    public Builder by(String by) {
      this.by = by;
      return this;
    }

    public JoinRequest build() {
      return new JoinRequest(from, on, by);
    }
  }
}
