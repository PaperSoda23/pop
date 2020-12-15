package client;

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

    public void setWriteToServer(PrintWriter writeToServer) throws IOException {
        if (isDisconnected())
            throw new IOException("socket already connected");
        this.writeToServer = writeToServer;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void setReadFromServer(BufferedReader readFromServer) throws IOException {
        if (isDisconnected())
            throw new IOException("socket already connected");
        this.readFromServer = readFromServer;
    }

    public Client(final Supplier<Socket> connectionProvider) throws IOException {
        this.clientSocket = connectionProvider.get();
        this.writeToServer = new PrintWriter(clientSocket.getOutputStream(), true);
        this.readFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void connect(
            final Supplier<String> inputProvider,
            final Consumer<String> outputConsumer
    ) throws IOException {
        outputConsumer.accept(readFromServer.readLine());
        System.out.println(readFromServer.readLine());
//        Scanner scanner = new Scanner(System.in);
        // TODO: make input to server as a flexible source
        // TODO: set custom termination command
        run(inputProvider, outputConsumer);
        System.out.println("disconnecting");
        disconnect();
    }

     private void run(
            final Supplier<String> inputProvider,
            final Consumer<String> outputConsumer
    ) throws IOException
    {
        String userInput;
        do {
//            userInput = scanner.nextLine().trim();
            userInput = inputProvider.get();
            String serverResponse = sendMessage(userInput);
            outputConsumer.accept(serverResponse);
            System.out.println(serverResponse);
        } while(!userInput.equals(TERMINATION_COMMAND));
    }

    private String sendMessage(final String clientMessage) throws IOException
    {
        writeToServer.println(clientMessage);
        final String serverResponse = readFromServer.readLine();
        return serverResponse;
    }

    public boolean isDisconnected() {
        return clientSocket != null && clientSocket.isConnected();
    }

    public void disconnect() throws IOException
    {
        writeToServer.close();
        readFromServer.close();
        clientSocket.close();
    }
}
