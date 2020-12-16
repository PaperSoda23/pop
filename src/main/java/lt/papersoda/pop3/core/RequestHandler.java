package lt.papersoda.pop3.core;

import lt.papersoda.pop3.commands.PassCommand;
import lt.papersoda.pop3.commands.RetrCommand;
import lt.papersoda.pop3.commands.UserCommand;
import lt.papersoda.pop3.pojo.ClientRequest;
import lt.papersoda.pop3.pojo.Response;
import lt.papersoda.pop3.user.UserSessionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestHandler implements IRequestHandler {
    final UserCommand userCommand;
    final RetrCommand retrCommand;

    @Autowired
    public RequestHandler(UserCommand userCommand, RetrCommand retrCommand) {
        this.userCommand = userCommand;
        this.retrCommand = retrCommand;
    }

    public Response handleClientRequest(ClientRequest clientRequest, UserSessionState userSessionState) {
        Response response = switch(clientRequest.getCommand()) {
            case USER -> userCommand.apply(clientRequest.getArguments(), userSessionState);
            case PASS -> new PassCommand().apply(clientRequest.getArguments(), userSessionState);
            case RETR -> retrCommand.apply(clientRequest.getArguments(), userSessionState);
            default -> new Response("command not recognized");
        };

        return response;
    }
}
