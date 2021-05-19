package academy.kovalevskyi.javadeepdive.week1.day2;

import java.util.Optional;

public record HttpRequest(String path, HttpMethod httpMethod, Optional<String> body,
                          ContentType contentType, HttpVersion httpVersion) {

  public static class Builder {
    public static final String DEFAULT_PATH = "/";

    private String path = DEFAULT_PATH;
    private HttpMethod httpMethod = HttpMethod.GET;
    private Optional<String> body;
    private ContentType contentType = ContentType.TEXT_HTML;
    private HttpVersion httpVersion = HttpVersion.HTTP_1_1;

    public Builder path(String path) {
      this.path = path;
      return this;
    }

    public Builder method(HttpMethod method) {
      this.httpMethod = method;
      return this;
    }

    public Builder body(String body) {
      this.body = Optional.ofNullable(body);
      return this;
    }

    public Builder contentType(ContentType contentType) {
      this.contentType = contentType;
      return this;
    }

    public Builder httpVersion(HttpVersion httpVersion) {
      this.httpVersion = httpVersion;
      return this;
    }

    public HttpRequest build() {
      return new HttpRequest(path, httpMethod, body, contentType, httpVersion);
    }
  }
}
