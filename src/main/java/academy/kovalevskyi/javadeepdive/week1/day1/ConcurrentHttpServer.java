package academy.kovalevskyi.javadeepdive.week1.day1;

import academy.kovalevskyi.javadeepdive.week1.day0.HttpRequestHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentHttpServer implements Runnable {
  public static final int DEFAULT_PORT = 8080;

  private final ExecutorService executorService;
  private final ServerSocket serverSocket;

  public ConcurrentHttpServer() {
    this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    try {
      this.serverSocket = new ServerSocket(DEFAULT_PORT);
    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalStateException(e.getMessage());
    }
  }

  public static void main(String[] args) {
    ConcurrentHttpServer server = new ConcurrentHttpServer();
    CompletableFuture.runAsync(server);
    System.out.println("input 'stop' to stop the server:");
    Scanner scanner = new Scanner(System.in);
    while (server.isLive()) {
      String next = scanner.next();
      if ("stop".equalsIgnoreCase(next)) {
        server.stop();
      }
    }
  }

  @Override
  public void run() {
    while (isLive()) {
      try {
        Socket socket = serverSocket.accept();
        executorService.submit(() -> new HttpRequestHandler(socket).executeRequest());
      } catch (SocketException e) {
        System.out.println("Socket closed!!!");
      } catch (IOException e) {
        // no-op
      }
    }
  }

  public void stop() {
    try {
      serverSocket.close();
      System.out.println("Server was stopped!!!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean isLive() {
    return !serverSocket.isClosed();
  }
}
