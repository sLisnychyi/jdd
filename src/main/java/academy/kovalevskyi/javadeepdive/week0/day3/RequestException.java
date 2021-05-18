package academy.kovalevskyi.javadeepdive.week0.day3;

public class RequestException extends Exception {
  public RequestException(String message, Object... values) {
    super(String.format(message, values));
  }
}
