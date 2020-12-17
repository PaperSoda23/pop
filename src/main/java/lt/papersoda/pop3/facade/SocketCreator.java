package lt.papersoda.pop3.facade;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketCreator {
    public static ServerSocket createServerSocket(int port) throws IOException {
        return new ServerSocket(port);
    }

    public static Socket acceptClientSocket(ServerSocket serverSocket) throws IOException {
        return serverSocket.accept();
    }

    public static void closeServerSocket(ServerSocket serverSocket) throws IOException {
        serverSocket.close();
    }
}
