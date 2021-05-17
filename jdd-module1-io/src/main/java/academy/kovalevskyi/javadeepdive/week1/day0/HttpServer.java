package academy.kovalevskyi.javadeepdive.week1.day0;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class HttpServer implements Runnable {
  public static final int DEFAULT_PORT = 8080;

  private ServerSocket serverSocket;

  public HttpServer() {
    try {
      this.serverSocket = new ServerSocket(DEFAULT_PORT);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    HttpServer httpServer = new HttpServer();
    new Thread(httpServer).start();
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
        final HttpRequestHandler requestHandler = new HttpRequestHandler(socket);
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
