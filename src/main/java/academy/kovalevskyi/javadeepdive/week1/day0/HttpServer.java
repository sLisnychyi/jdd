package academy.kovalevskyi.javadeepdive.week1.day0;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class HttpServer implements Runnable {
  private static final int DEFAULT_PORT = 8080;

  private final ServerSocket serverSocket;

  public HttpServer() throws IOException {
    this.serverSocket = new ServerSocket(DEFAULT_PORT);
  }

  public static void main(String[] args) throws IOException {
    HttpServer httpServer = new HttpServer();
    CompletableFuture.runAsync(httpServer);
    System.out.println("input 'stop' to stop the server:");
    Scanner scanner = new Scanner(System.in);
    while (httpServer.isLive()) {
      String next = scanner.next();
      if ("stop".equalsIgnoreCase(next)) {
        httpServer.stop();
      }
    }
  }

  @Override
  public void run() {
    while (isLive()) {
      try (Socket socket = serverSocket.accept()) {
        HttpRequestHandler requestHandler = new HttpRequestHandler(socket);
        requestHandler.executeRequest();
      } catch (SocketException e) {
        System.out.println("Socket closed!!!");
      } catch (IOException e) {
        e.printStackTrace();
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
