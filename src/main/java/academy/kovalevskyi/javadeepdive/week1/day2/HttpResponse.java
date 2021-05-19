package academy.kovalevskyi.javadeepdive.week1.day2;

public record HttpResponse(ResponseStatus responseStatus, String body,
                          ContentType contentType, HttpVersion httpVersion) {

  public HttpResponse(
      ResponseStatus responseStatus,
      String body,
      ContentType contentType,
      HttpVersion httpVersion) {
    this.responseStatus = responseStatus;
    this.body = body;
    this.contentType = contentType;
    this.httpVersion = httpVersion;
  }

  public static final HttpResponse ERROR_404 =
      new Builder().status(ResponseStatus.ERROR_404).build();
  public static final HttpResponse OK_200 = new Builder().status(ResponseStatus.OK).build();
  public static final HttpResponse ERROR_500 =
      new Builder().status(ResponseStatus.ERROR_500).build();

  public static class Builder {
    private ResponseStatus status = ResponseStatus.OK;
    private String body;
    private ContentType contentType = ContentType.TEXT_HTML;
    private HttpVersion httpVersion = HttpVersion.HTTP_1_1;

    public Builder status(ResponseStatus status) {
      this.status = status;
      return this;
    }

    public Builder contentType(ContentType contentType) {
      this.contentType = contentType;
      return this;
    }

    public Builder body(String body) {
      this.body = body;
      return this;
    }

    public Builder httpVersion(HttpVersion httpVersion) {
      this.httpVersion = httpVersion;
      return this;
    }

    public HttpResponse build() {
      return new HttpResponse(status, body, contentType, httpVersion);
    }
  }

  public enum ResponseStatus {
    OK(200, "OK"),
    ERROR_404(404, "not found"),
    ERROR_500(500, "server error");
    private final int statusCode;
    private final String message;

    ResponseStatus(int statusCode, String message) {
      this.statusCode = statusCode;
      this.message = message;
    }

    public int getStatusCode() {
      return statusCode;
    }

    public String getMessage() {
      return message;
    }
  }
}
