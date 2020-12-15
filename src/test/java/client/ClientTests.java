package client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.*;
import java.net.Socket;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientTests {
    @Spy Client spyClient;

    @Mock BufferedReader mockReaderFromServer;
    @Mock PrintWriter mockWriterToServer;
    @Mock Socket mockSocket;

    @BeforeEach
    public void init() {
        spyClient.setWriteToServer(mockWriterToServer);
        spyClient.setReadFromServer(mockReaderFromServer);
        spyClient.setClientSocket(mockSocket);
        when(mockSocket.isConnected()).thenReturn(true);
    }

    @AfterEach
    public void destroy() {
        when(mockSocket.isConnected()).thenReturn(false);
        spyClient.setWriteToServer(null);
        spyClient.setReadFromServer(null);
        spyClient.setClientSocket(null);
    }

    @Test
    public void consumer_retrieves_messages_from_server() throws Exception {
        var initialMessageFromServer = "hello";
        var otherServerResponse = "otherResponse";
        when(mockReaderFromServer.readLine())
                .thenReturn(initialMessageFromServer)
                .thenReturn(otherServerResponse);

        Consumer<String> outputConsumer = mock(Consumer.class);


        spyClient.connect(() -> Client.TERMINATION_COMMAND, outputConsumer);


        verify(outputConsumer)
                .accept(argThat(arg -> arg.equals(initialMessageFromServer)));
        verify(outputConsumer)
                .accept(argThat(arg -> arg.equals(otherServerResponse)));

        verifyNoMoreInteractions(outputConsumer);
    }

    @Test
    public void sends_message_to_server() throws IOException {
        Supplier<String> inputProvider = mock(Supplier.class);
        when(inputProvider.get()).thenReturn(Client.TERMINATION_COMMAND);

        spyClient.connect(inputProvider, mock(Consumer.class));

        verify(mockWriterToServer).println((String) argThat(arg -> arg.equals(Client.TERMINATION_COMMAND)));
    }

    @Test
    public void stops_running_when_termination_command_is_passed() throws IOException {
        Supplier<String> inputProvider = mock(Supplier.class);
        when(inputProvider.get())
                .thenReturn("USER")
                .thenReturn(Client.TERMINATION_COMMAND);

        spyClient.connect(inputProvider, mock(Consumer.class));

        verify(mockWriterToServer, times(2)).println(anyString());
        verify(spyClient, times(1)).disconnect();
    }
}