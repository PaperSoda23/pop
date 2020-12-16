package lt.papersoda.pop3;

import lt.papersoda.pop3.user.UserSession;
import org.springframework.beans.factory.BeanFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private boolean isRunning;
    private final int port;
    private final ServerSocket serverSocket;

    public Server(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while(isRunning) {
            try {
                Socket clientSocket = serverSocket.accept();
                UserSession userSession = new UserSession(clientSocket);
                new Thread(userSession).start();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            serverSocket.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void start() {
        this.isRunning = true;
        new Thread(this).start();
    }

    public void stop() {
        this.isRunning = false;
    }

    public int getPort() {
        return port;
    }
}
