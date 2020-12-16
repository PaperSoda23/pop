package lt.papersoda.pop3.core;

import lt.papersoda.pop3.pojo.Response;
import lt.papersoda.pop3.user.UserConnectionState;

@FunctionalInterface
public interface IRequestProcessor {
    Response processClientRequest(final String rawClientRequest, final UserConnectionState userConnectionState);
}
