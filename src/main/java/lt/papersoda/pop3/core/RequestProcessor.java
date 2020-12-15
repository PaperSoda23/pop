package lt.papersoda.pop3.core;

import lt.papersoda.pop3.pojo.ClientRequest;
import lt.papersoda.pop3.pojo.Response;

public class RequestProcessor implements IRequestProcessor {
    IRequestParser requestParser;
    IRequestHandler requestHandler;

    public RequestProcessor() {
        this.requestParser = new RequestParser();
        this.requestHandler = new RequestHandler();
    }

    public Response processClientRequest(final String rawClientRequest) {
        final ClientRequest clientRequest = requestParser.parseClientRequest(rawClientRequest);
        final Response response = requestHandler.handleClientRequest(clientRequest);
        return response;
    }
}
