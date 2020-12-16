package lt.papersoda.pop3;

import lt.papersoda.pop3.core.IRequestProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Pop3Application implements CommandLineRunner {
    private static final int port = 3334;
    private final ApplicationContext applicationContext;

    public Pop3Application(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(Pop3Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        IRequestProcessor requestProcessor = applicationContext.getBean(IRequestProcessor.class);
        IServer server = new Server(port, requestProcessor);
        server.start();
    }
}
