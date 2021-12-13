package academy.kovalevskyi.javadeepdive.week0.day0;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;

public class StdBufferedReader implements Closeable {
  private static final int DEFAULT_BUFFER_SIZE = 10;

  private final Reader reader;
  private char[] buffer;
  private int pointer;

  public StdBufferedReader(Reader reader) {
    this(reader, DEFAULT_BUFFER_SIZE);
  }

  public StdBufferedReader(Reader reader, int bufferSize) {
    if (reader == null) throw new NullPointerException();
    if (bufferSize <= 1) throw new IllegalArgumentException();
    this.reader = reader;
    this.buffer = new char[bufferSize];
  }

  public boolean hasNext() throws IOException {
    return reader.ready();
  }

  public char[] readLine() throws IOException {
    char[] result = new char[buffer.length];
    int cursor = 0;

    if (pointer > 0) {
      while (pointer < buffer.length) {
        char elem = buffer[pointer++];
        if (elem == '\n' || elem == '\r' || elem == 0) {
          char[] dest = new char[cursor];
          System.arraycopy(result, 0, dest, 0, cursor);
          return elem == 0 && cursor == 0 ? null : dest;
        }
        result[cursor++] = elem;
      }
    }

    int read = reader.read(buffer, 0, buffer.length);
    while (read > 0) {
      if (cursor + pointer > result.length - 1) {
        char[] dest = new char[result.length + buffer.length];
        System.arraycopy(result, 0, dest, 0, result.length);
        result = dest;
      }
      pointer = 0;
      while (pointer < read) {
        char elem = buffer[pointer++];
        if (elem == '\n' || elem == '\r' || elem == 0) {
          char[] dest = new char[cursor];
          System.arraycopy(result, 0, dest, 0, cursor);
          return elem == 0 && cursor == 0 ? null : dest;
        }
        result[cursor++] = elem;
      }
      buffer = new char[buffer.length];
      read = reader.read(buffer, 0, buffer.length);
    }

    if (cursor == 0) return null;
    char[] dest = new char[cursor];
    System.arraycopy(result, 0, dest, 0, cursor);
    return dest;
  }

  @Override
  public void close() throws IOException {
    reader.close();
  }
}
