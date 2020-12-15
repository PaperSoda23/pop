package lt.papersoda.pop3.core;

import lt.papersoda.pop3.pojo.ClientRequest;
import lt.papersoda.pop3.pojo.Response;

@FunctionalInterface
public interface IRequestHandler {
    Response handleClientRequest(ClientRequest clientRequest);
}
