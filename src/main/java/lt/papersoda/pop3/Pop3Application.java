package lt.papersoda.pop3;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Pop3Application {
    private static final int port = 3333;

    public static void main(String[] args) {
        var server = new Server();
        try { server.start(port); }
        catch (IOException e) { e.printStackTrace(); }
//        SpringApplication.run(Pop3Application.class, args);
    }
}
