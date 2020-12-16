package lt.papersoda.pop3.core;

import lt.papersoda.pop3.pojo.ClientRequest;
import lt.papersoda.pop3.pojo.Response;
import lt.papersoda.pop3.user.UserSessionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestProcessor implements IRequestProcessor {
    final IRequestParser requestParser;
    final IRequestHandler requestHandler;

    @Autowired
    public RequestProcessor(IRequestParser requestParser, IRequestHandler requestHandler) {
        this.requestParser = requestParser;
        this.requestHandler = requestHandler;
    }

    public Response processClientRequest(final String rawClientRequest, final UserSessionState userSessionState) {
        final ClientRequest clientRequest = requestParser.parseClientRequest(rawClientRequest);
        final Response response = requestHandler.handleClientRequest(clientRequest, userSessionState);
        return response;
    }
}
