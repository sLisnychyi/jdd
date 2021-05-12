package academy.kovalevskyi.javadeepdive.week0.day0;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;

// https://drive.google.com/file/d/15E8Q8_rYDWqO2MrR2CQLePEbQtL88MzE/view?usp=sharing
public class StdBufferedReader implements Closeable {
  private static final int DEFAULT_BUFFER_SIZE = 10;

  private final Reader reader;
  private char[] buffer;
  private int pointer = -1;

  public StdBufferedReader(Reader reader) {
    this(reader, DEFAULT_BUFFER_SIZE);
  }

  public StdBufferedReader(Reader reader, int bufferSize) {
    if (reader == null) {
      throw new NullPointerException("Reader can't be empty.");
    }
    if (bufferSize <= 0) {
      throw new IllegalArgumentException(String.format("Invalid buffer size[%s]", bufferSize));
    }
    this.reader = reader;
    this.buffer = new char[bufferSize];
  }

  public boolean hasNext() throws IOException {
    return reader.ready() || pointer >= 0;
  }

  public char[] readLine() throws IOException {
    char[] result = new char[buffer.length];
    int counter = 0;
    while (hasNext() || pointer > 0) {
      int read = pointer < 0 ? reader.read(buffer, 0, buffer.length) : buffer.length;
      for (int i = pointer + 1; i < read; i++) {
        char c = buffer[i];
        pointer++;
        if (isEOL(c) || isEOF(c)) {
          result = copyElements(result, counter, counter);
          if (isEOF(c)) {
            pointer = -1;
          }
          return result;
        }
        result[counter++] = c;
      }
      if (hasNext()) {
        result = copyElements(result, result.length + buffer.length, counter);
        buffer = new char[buffer.length];
      }
      pointer = -1;
    }
    result = copyElements(result, counter, counter);
    return result.length == 0 ? new char[0] : result;
  }

  private char[] copyElements(char[] arr, int size, int elements) {
    char[] res = new char[size];
    System.arraycopy(arr, 0, res, 0, elements);
    return res;
  }

  private boolean isEOL(char c) {
    return c == '\n' || c == '\r';
  }

  private boolean isEOF(char c) {
    return c == 0;
  }

  public void close() throws IOException {
    reader.close();
  }
}
