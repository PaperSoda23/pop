package lt.papersoda.pop3.core;

import lt.papersoda.pop3.commands.PassCommand;
import lt.papersoda.pop3.commands.UserCommand;
import lt.papersoda.pop3.pojo.ClientRequest;
import lt.papersoda.pop3.pojo.Response;


public class RequestHandler implements IRequestHandler {

    public Response handleClientRequest(ClientRequest clientRequest) {

        return switch(clientRequest.getCommand()) {
            case USER -> new UserCommand().apply(clientRequest.getArguments());
            case PASS -> new PassCommand().apply(clientRequest.getArguments());
            default -> new Response("command not recognized");
        };
    }
}
