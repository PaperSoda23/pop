package lt.papersoda.pop3;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Pop3Application {
    public static void main(String[] args) {
        var server = new Server();

        try { server.start(3333); }
        catch (IOException e) { e.printStackTrace(); }
//        SpringApplication.run(Pop3Application.class, args);
    }

}
