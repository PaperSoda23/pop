package lt.papersoda.pop3.core;

import lt.papersoda.pop3.commands.PassCommand;
import lt.papersoda.pop3.commands.RetrCommand;
import lt.papersoda.pop3.commands.UserCommand;
import lt.papersoda.pop3.commands.enums.PopCommands;
import lt.papersoda.pop3.pojo.ClientRequest;
import lt.papersoda.pop3.pojo.ErrorResponse;
import lt.papersoda.pop3.pojo.Response;
import lt.papersoda.pop3.user.UserSessionState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RequestHandlerTests {
    @Mock UserCommand userCommand;
    @Mock RetrCommand retrCommand;
    @Mock PassCommand passCommand;

    @InjectMocks
    RequestHandler requestHandler;

    @Mock ClientRequest mockClientRequest;
    @Mock Response mockResponse;
    @Mock UserSessionState mockUserSessionState;


    @Test
    public void handle_user_command() {
        when(mockClientRequest.getCommand()).thenReturn(PopCommands.USER);
        when(userCommand.apply(any(), any())).thenReturn(mockResponse);

        Response response = requestHandler.handleClientRequest(mockClientRequest, mockUserSessionState);

        assertThat(response).isEqualTo(mockResponse);
    }

    @Test
    public void handle_pass_command() {
        when(mockClientRequest.getCommand()).thenReturn(PopCommands.PASS);
        when(passCommand.apply(any(), any())).thenReturn(mockResponse);

        Response response = requestHandler.handleClientRequest(mockClientRequest, mockUserSessionState);

        assertThat(response).isEqualTo(mockResponse);
    }

    @Test
    public void handle_retr_command() {
        when(mockClientRequest.getCommand()).thenReturn(PopCommands.RETR);
        when(retrCommand.apply(any(), any())).thenReturn(mockResponse);

        Response response = requestHandler.handleClientRequest(mockClientRequest, mockUserSessionState);

        assertThat(response).isEqualTo(mockResponse);
    }

    @Test
    public void returns_error_when_command_is_null() {
        when(mockClientRequest.getCommand()).thenReturn(null);

        Response response = requestHandler.handleClientRequest(mockClientRequest, mockUserSessionState);

        assertThat(response).isInstanceOf(ErrorResponse.class);
        assertThat(response.getResponse()).contains("server error");
    }

    @Test
    public void returns_error_when_command_is_not_recognized() {
        when(mockClientRequest.getCommand()).thenReturn(PopCommands.UNRECOGNIZED);

        Response response = requestHandler.handleClientRequest(mockClientRequest, mockUserSessionState);

        assertThat(response).isInstanceOf(ErrorResponse.class);
    }
}
