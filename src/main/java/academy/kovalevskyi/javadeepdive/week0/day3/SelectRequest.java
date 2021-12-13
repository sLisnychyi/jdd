package academy.kovalevskyi.javadeepdive.week0.day3;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SelectRequest extends AbstractRequest<String[][]> {
  private final Csv csv;
  private final Selector selector;
  private final String[] columns;

  public SelectRequest(Csv csv, Selector selector, String[] columns) {
    super(csv);
    this.csv = csv;
    this.selector = selector;
    this.columns = columns;
  }

  @Override
  protected String[][] execute() throws RequestException {
    Optional<Integer> whereColumnIndex =
        Optional.ofNullable(selector)
            .map(selector -> getColumnIndex(csv.headers(), selector.fieldName()));
    Set<Integer> columnIndexes = new HashSet<>();
    Set<String> columns = Set.of(this.columns);
    for (int i = 0; i < csv.headers().length; i++) {
      if (columns.contains(csv.headers()[i])) {
        columnIndexes.add(i);
      }
    }
    return Arrays.stream(csv.values())
        .filter(e -> whereColumnIndex.map(index -> e[index].equals(selector.value())).orElse(true))
        .map(
            e ->
                IntStream.range(0, e.length)
                    .filter(columnIndexes::contains)
                    .mapToObj(i -> e[i])
                    .toArray(String[]::new))
        .toArray(String[][]::new);
  }

  public static class Builder {
    private Csv csv;
    private Selector selector;
    private String[] columns;

    public Builder where(Selector selector) {
      this.selector = selector;
      return this;
    }

    public Builder select(String[] columns) {
      this.columns = columns;
      return this;
    }

    public Builder from(Csv csv) {
      this.csv = csv;
      return this;
    }

    public SelectRequest build() {
      Objects.nonNull(csv);
      return new SelectRequest(csv, selector, columns);
    }
  }
}
