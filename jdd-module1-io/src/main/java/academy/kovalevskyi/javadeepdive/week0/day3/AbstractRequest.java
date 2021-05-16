package academy.kovalevskyi.javadeepdive.week0.day3;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;

public abstract class AbstractRequest<T> {
  protected final Csv csv;

  protected AbstractRequest(Csv csv) {
    this.csv = csv;
  }

  protected abstract T execute() throws RequestException;

  protected int getColumnIndex(String[] header, String fieldName) {
    for (int i = 0; i < header.length; i++) {
      if (header[i].equals(fieldName)) {
        return i;
      }
    }
    return -1;
  }
}
