package lt.papersoda.pop3;

import lt.papersoda.pop3.core.IRequestParser;
import lt.papersoda.pop3.core.IRequestProcessor;
import lt.papersoda.pop3.core.RequestProcessor;
import lt.papersoda.pop3.pojo.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements IServer {
    IRequestProcessor requestProcessor = new RequestProcessor();

    public void start(int port) throws IOException {
        var serverSocket = new ServerSocket(port);
        Socket clientSocket = serverSocket.accept();

        var writeToClient = new PrintWriter(clientSocket.getOutputStream(), true);
        var readFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        writeToClient.println("+OK POP3 connected");

        while(clientSocket.isConnected()) {
            String message = readFromClient.readLine();
            System.out.println("from client: " + message);
            Response response = requestProcessor.processClientRequest(message);
            writeToClient.println(response.getResponse());
        }

        stop(clientSocket, serverSocket, writeToClient, readFromClient);
        System.out.println("server stops");
    }

    private void stop(
            final Socket clientSocket,
            final ServerSocket serverSocket,
            final PrintWriter writeToClient,
            final BufferedReader readFromClient
    ) throws IOException
    {
        writeToClient.close();
        readFromClient.close();
        clientSocket.close();
        serverSocket.close();
    }
}
