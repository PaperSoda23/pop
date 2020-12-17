package lt.papersoda.pop3.core;

import lt.papersoda.pop3.commands.PassCommand;
import lt.papersoda.pop3.commands.RetrCommand;
import lt.papersoda.pop3.commands.UserCommand;
import lt.papersoda.pop3.commands.enums.PopCommands;
import lt.papersoda.pop3.pojo.ClientRequest;
import lt.papersoda.pop3.pojo.ErrorResponse;
import lt.papersoda.pop3.pojo.Response;
import lt.papersoda.pop3.user.UserSessionState;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestHandler implements IRequestHandler {
    final UserCommand userCommand;
    final RetrCommand retrCommand;
    final PassCommand passCommand;

    @Autowired
    public RequestHandler(
            UserCommand userCommand,
            RetrCommand retrCommand,
            PassCommand passCommand
    ) {
        this.userCommand = userCommand;
        this.retrCommand = retrCommand;
        this.passCommand = passCommand;
    }

    public Response handleClientRequest(final ClientRequest clientRequest, final UserSessionState userSessionState) {
        if (ObjectUtils.isEmpty(clientRequest))
            return new  ErrorResponse("server error: client request is empty");

        PopCommands popCommand = clientRequest.getCommand();

        if (ObjectUtils.isEmpty(popCommand))
            return new  ErrorResponse("server error: client request is empty");

        Response response = switch(popCommand) {
            case USER -> userCommand.apply(clientRequest.getArguments(), userSessionState);
            case PASS -> passCommand.apply(clientRequest.getArguments(), userSessionState);
            case RETR -> retrCommand.apply(clientRequest.getArguments(), userSessionState);
            default -> new ErrorResponse("command not recognized");
        };

        return response;
    }
}
