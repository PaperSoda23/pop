package lt.papersoda.pop3.core;

import lt.papersoda.pop3.commands.PassCommand;
import lt.papersoda.pop3.commands.RetrCommand;
import lt.papersoda.pop3.commands.UserCommand;
import lt.papersoda.pop3.pojo.ClientRequest;
import lt.papersoda.pop3.pojo.Response;
import org.springframework.stereotype.Service;

@Service
public class RequestHandler implements IRequestHandler {

    public Response handleClientRequest(ClientRequest clientRequest) {
        Response response = switch(clientRequest.getCommand()) {
            case USER -> new UserCommand().apply(clientRequest.getArguments());
            case PASS -> new PassCommand().apply(clientRequest.getArguments());
            case RETR -> new RetrCommand().apply(clientRequest.getArguments());
            default -> new Response("command not recognized");
        };

        return response;
    }
}
