package lt.papersoda.pop3.pojo;

import lt.papersoda.pop3.user.UserConnectionState;

public class Response {
    private final String response;
    private UserConnectionState userConnectionState;


    public Response(final String response) {
        this.response = response;
    }


    public String getResponse() {
        return this.response;
    }

    public UserConnectionState getUserConnectionState() {
        return userConnectionState;
    }
}
