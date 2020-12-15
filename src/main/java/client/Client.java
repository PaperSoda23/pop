package client;

import client.exception.ClientConfigurationException;
import client.exception.ClientConnectionException;
import client.exception.ClientDetachingException;

import java.io.*;
import java.net.Socket;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Client {
    public static final String TERMINATION_COMMAND = "QUIT";

    private Socket clientSocket;

    private PrintWriter writeToServer;
    private BufferedReader readFromServer;

    // required by mockito spy
    public Client() {}

    public Client(final String ip, final int port) throws IOException {
        try {
            this.clientSocket = new Socket(ip, port);
            this.writeToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            this.readFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException ioException) {
            throw new ClientConnectionException("Client(): error connecting to server", ioException);
        }
    }

    public void connect(
            final Supplier<String> inputProvider,
            final Consumer<String> outputConsumer
    ) throws IOException {
        outputConsumer.accept(readFromServer.readLine());
        run(inputProvider, outputConsumer);
        disconnect();
    }

     private void run(
            final Supplier<String> inputProvider,
            final Consumer<String> outputConsumer
    ) throws IOException
    {
        String userInput;
        do {
            userInput = inputProvider.get();
            String serverResponse = sendMessage(userInput);
            outputConsumer.accept(serverResponse);
        } while(!userInput.equals(TERMINATION_COMMAND));
    }

    private String sendMessage(final String clientMessage) throws IOException
    {
        writeToServer.println(clientMessage);
        final String serverResponse = readFromServer.readLine();
        return serverResponse;
    }

    public boolean isConnected() {
        return clientSocket != null && clientSocket.isConnected();
    }

    public void disconnect() {
        try {
            writeToServer.close();
            readFromServer.close();
            clientSocket.close();
        } catch (IOException ioException) {
            throw new ClientDetachingException("Client:disconnect(): failed to disconnect", ioException);
        }
    }

    public void setWriteToServer(PrintWriter writeToServer) {
        if (isConnected())
            throw new ClientConfigurationException("Client:setWriter(): socket already connected");
        this.writeToServer = writeToServer;
    }

    public void setClientSocket(Socket clientSocket) {
        if (isConnected())
            throw new ClientConfigurationException("Client:setSocket(): socket already connected");
        this.clientSocket = clientSocket;
    }

    public void setReadFromServer(BufferedReader readFromServer) {
        if (isConnected())
            throw new ClientConfigurationException("Client:setReader(): socket already connected");
        this.readFromServer = readFromServer;
    }
}
