package lt.papersoda.pop3.core;

import lt.papersoda.pop3.pojo.ClientRequest;
import lt.papersoda.pop3.pojo.Response;
import lt.papersoda.pop3.user.UserConnectionState;

public class RequestProcessor implements IRequestProcessor {
    final IRequestParser requestParser = new RequestParser();
    final IRequestHandler requestHandler = new RequestHandler();

    public Response processClientRequest(final String rawClientRequest, final UserConnectionState userConnectionState) {
        final ClientRequest clientRequest = requestParser.parseClientRequest(rawClientRequest);
        final Response response = requestHandler.handleClientRequest(clientRequest);
        return response;
    }
}
