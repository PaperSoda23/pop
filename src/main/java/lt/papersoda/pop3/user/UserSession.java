package lt.papersoda.pop3.user;

import lt.papersoda.pop3.core.IRequestProcessor;
import lt.papersoda.pop3.core.RequestProcessor;
import lt.papersoda.pop3.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserSession implements Runnable {
    private final Socket clientSocket;
    private final PrintWriter writeToClient;
    private final BufferedReader readFromClient;
    private final IRequestProcessor requestProcessor = new RequestProcessor();
    private UserConnectionState userConnectionState = UserConnectionState.AUTHORIZATION;

    public UserSession(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.writeToClient = new PrintWriter(clientSocket.getOutputStream(), true);
        this.readFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    // TODO: wrap sockets in a wrapper
    @Override
    public void run() {
        writeToClient.println("+OK POP3 connected");

        while(clientSocket.isConnected()) {
            String message = null;
            try {
                message = readFromClient.readLine();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println("from client: " + message);
            Response response = requestProcessor.processClientRequest(message, userConnectionState);
            writeToClient.println(response.getResponse());
        }

        try {
            stop();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void stop() throws IOException {
        this.writeToClient.close();
        this.readFromClient.close();
        this.clientSocket.close();
    }
}
