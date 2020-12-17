package lt.papersoda.pop3;

import lt.papersoda.pop3.core.IRequestProcessor;
import lt.papersoda.pop3.facade.SocketCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.net.ServerSocket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ServerTests {
    @Mock ServerSocket mockServerSocket;
    @Mock IRequestProcessor mockRequestProcessor;
    private final int port = 3330;

    public void mockStaticSocketCreation() throws IOException {
        mockStatic(SocketCreator.class)
                .when((MockedStatic.Verification) SocketCreator
                        .createServerSocket(port))
                            .thenReturn(mockServerSocket);
    }

    @Test
    public void server_is_initialized() throws IOException {
        mockStaticSocketCreation();

        var server = new Server(port, mockRequestProcessor);

        assertThat(server.getIsRunning()).isFalse();
        assertThat(server.getServerSocket()).isEqualTo(mockServerSocket);
        assertThat(server.getPort()).isEqualTo(port);
        assertThat(server.getRequestProcessor()).isEqualTo(mockRequestProcessor);
    }

    @Test
    public void server_starts_and_stops() throws IOException {
        var server = new Server(port, mockRequestProcessor);


        server.start();
        assertThat(server.getIsRunning()).isTrue();

        server.stop();
        assertThat(server.getIsRunning()).isFalse();
    }
}
