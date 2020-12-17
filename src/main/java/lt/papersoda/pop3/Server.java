package lt.papersoda.pop3;

import lt.papersoda.pop3.core.IRequestProcessor;
import lt.papersoda.pop3.facade.SocketCreator;
import lt.papersoda.pop3.user.UserSession;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable, IServer {
    private boolean isRunning;
    private final int port;
    private final ServerSocket serverSocket;
    private final IRequestProcessor requestProcessor;

    public Server(int port, IRequestProcessor requestProcessor) throws IOException {
        this.port = port;
        this.requestProcessor = requestProcessor;
        this.serverSocket = SocketCreator.createServerSocket(port);
    }

    @Override
    public void run() {
        while(isRunning) {
            try {
                Socket clientSocket = SocketCreator.acceptClientSocket(serverSocket);
                UserSession userSession = new UserSession(clientSocket, requestProcessor);
                new Thread(userSession).start();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                closeServerSocket();
            }
        }
    }

    public void closeServerSocket() {
        try { this.serverSocket.close(); }
        catch (IOException ioException) { ioException.printStackTrace(); }
    }

    public void start() {
        this.isRunning = true;
        new Thread(this).start();
    }

    public void stop() {
        this.isRunning = false;
        closeServerSocket();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public IRequestProcessor getRequestProcessor() {
        return requestProcessor;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    public int getPort() {
        return port;
    }
}
