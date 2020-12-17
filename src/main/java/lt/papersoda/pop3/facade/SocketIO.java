package lt.papersoda.pop3.facade;

import java.io.*;
import java.net.Socket;

public class SocketIO {
    public static BufferedReader createSocketReader(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public static PrintWriter createSocketWriter(Socket socket) throws IOException {
        return new PrintWriter(socket.getOutputStream(), true);
    }

    public static void writeToClient(PrintWriter socketWriter, String message) {
        socketWriter.println(message);
    }

    public static String readFromClient(BufferedReader socketReader) throws IOException {
        return socketReader.readLine();
    }
}
