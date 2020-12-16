package lt.papersoda.pop3.pojo;

import lt.papersoda.pop3.db.entity.UserEntity;
import lt.papersoda.pop3.user.UserConnectionState;

public class Response {
    private final String response;
    private UserEntity user;
    private UserConnectionState userConnectionState = UserConnectionState.AUTHORIZATION;
    private boolean shouldUserConnectionStateChange = false;

    public boolean getShouldUserConnectionStateChange() {
        return shouldUserConnectionStateChange;
    }

    public Response setShouldUserConnectionStateChange(boolean shouldUserConnectionStateChange) {
        this.shouldUserConnectionStateChange = shouldUserConnectionStateChange;
        return this;
    }

    public Response(final String response) {
        this.response = response;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }

    public String getResponse() {
        return this.response;
    }

    public Response setUserConnectionState(UserConnectionState userConnectionState) {
        this.userConnectionState = userConnectionState;
        return this;
    }

    public UserConnectionState getUserConnectionState() {
        return userConnectionState;
    }

}
