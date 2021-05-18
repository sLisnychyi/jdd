package academy.kovalevskyi.javadeepdive.week1.day0;

import academy.kovalevskyi.javadeepdive.week0.day0.StdBufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class HttpRequestHandler {
  private final Socket socket;

  public HttpRequestHandler(Socket socket) {
    this.socket = socket;
  }

  public void executeRequest() {
    try (StdBufferedReader reader =
            new StdBufferedReader(new InputStreamReader(socket.getInputStream()));
         OutputStream outputStream = socket.getOutputStream()) {
      while (reader.hasNext()) {
        System.out.println(reader.readLine());
      }
      String res =
          "HTTP/1.1 200 OK\n"
              + "Server: HTTP server/0.1\n"
              + "Content-type: text/html\n"
              + "Content-Length: 20\n\n"
              + "<b>It works!</b>\r\n\r\n";
      outputStream.write(res.getBytes());
      outputStream.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
