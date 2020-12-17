package lt.papersoda.pop3.core;

import lt.papersoda.pop3.pojo.Response;
import lt.papersoda.pop3.user.UserSessionState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

@ExtendWith(MockitoExtension.class)
public class RequestProcessorTests {
    @Mock RequestParser mockRequestParser;
    @Mock RequestHandler mockRequestHandler;
    @Mock UserSessionState mockUserSessionState;
    @Mock Response mockResponse;
    private final String rawRequest = "request";

    @InjectMocks
    RequestProcessor requestProcessor;


    @Test
    public void passes_raw_client_request_to_request_parser() {
        requestProcessor.processClientRequest(rawRequest, mockUserSessionState);

        verify(mockRequestParser).parseClientRequest(argThat(arg -> arg.equals(rawRequest)));
    }

    @Test
    public void returns_response_received_from_request_handler() {
        when(mockRequestHandler.handleClientRequest(any(), any())).thenReturn(mockResponse);

        Response response = requestProcessor.processClientRequest(rawRequest, mockUserSessionState);

        assertThat(response).isEqualTo(mockResponse);
    }
}
