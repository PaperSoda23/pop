package lt.papersoda.pop3.core;

import lt.papersoda.pop3.pojo.ClientRequest;

@FunctionalInterface
public interface IRequestParser {
    ClientRequest parseClientRequest(final String rawRequest);
}