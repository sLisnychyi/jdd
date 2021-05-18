package academy.kovalevskyi.javadeepdive.week1.day1;

import academy.kovalevskyi.javadeepdive.week1.day0.HttpRequestHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentHttpServer implements Runnable {
  public static final int DEFAULT_PORT = 8080;
  public static final int THREADS = 4;

  private final ExecutorService executorService;
  private final ServerSocket serverSocket;

  public ConcurrentHttpServer() {
    this.executorService = Executors.newFixedThreadPool(THREADS);
    try {
      this.serverSocket = new ServerSocket(DEFAULT_PORT);
    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalStateException(e.getMessage());
    }
  }

  public static void main(String[] args) {
    ConcurrentHttpServer server = new ConcurrentHttpServer();
    new Thread(server).start();
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
      executorService.submit(
          () -> {
            try {
              new HttpRequestHandler(serverSocket.accept()).executeRequest();
            } catch (IOException e) {
              // no-op
            }
          });
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
