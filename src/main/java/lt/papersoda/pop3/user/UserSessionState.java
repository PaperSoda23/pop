package lt.papersoda.pop3.user;

import lt.papersoda.pop3.db.entity.UserEntity;

public class UserSessionState {
    UserEntity user;
    UserConnectionState userConnectionState = UserConnectionState.AUTHORIZATION;

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }

    public UserConnectionState getUserConnectionState() {
        return userConnectionState;
    }

    public void setUserConnectionState(UserConnectionState userConnectionState) {
        this.userConnectionState = userConnectionState;
    }
}
