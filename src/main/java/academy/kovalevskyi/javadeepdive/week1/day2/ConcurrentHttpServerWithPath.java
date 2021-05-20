package academy.kovalevskyi.javadeepdive.week1.day2;

import academy.kovalevskyi.javadeepdive.week0.day0.StdBufferedReader;
import academy.kovalevskyi.javadeepdive.week0.day3.RequestException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentHttpServerWithPath extends Thread {
  private static final int DEFAULT_PORT = 8080;

  private final ExecutorService executorService;
  private final Map<String, HttpRequestsHandler> handlers;
  private final ServerSocket serverSocket;

  public ConcurrentHttpServerWithPath() {
    this.handlers = new HashMap<>();
    this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    try {
      this.serverSocket = new ServerSocket(DEFAULT_PORT);
    } catch (IOException e) {
      throw new IllegalStateException("unable to create server, cause = " + e.getMessage());
    }
  }

  public void addHandler(HttpRequestsHandler handler) {
    this.handlers.put(handler.path(), handler);
  }

  @Override
  public void run() {
    while (isLive()) {
      try {
        Socket socket = serverSocket.accept();
        executorService.submit(
            () -> {
              try (StdBufferedReader bufferedReader =
                      new StdBufferedReader(new InputStreamReader(socket.getInputStream()));
                  OutputStream outputStream = socket.getOutputStream()) {
                HttpRequest httpRequest = HttpRequestBuilder.build(bufferedReader);
                HttpResponse httpResponse =
                    Optional.ofNullable(handlers.get(httpRequest.path()))
                        .filter(handler -> httpRequest.httpMethod() == handler.method())
                        .map(handler -> handler.process(httpRequest))
                        .orElse(HttpResponse.ERROR_404);

                outputStream.write(
                    HttpResponseBuilder.build(httpResponse).getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
              } catch (IOException | RequestException exception) {
                exception.printStackTrace();
              }
            });
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void stopServer() {
    try {
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean isLive() {
    return !serverSocket.isClosed();
  }
}
