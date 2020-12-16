package lt.papersoda.pop3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Pop3Application {
    private static final int port = 3333;

    public static void main(String[] args) {
        try {
            Server server = new Server(port);
            server.start();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        SpringApplication.run(Pop3Application.class, args);
    }
}
