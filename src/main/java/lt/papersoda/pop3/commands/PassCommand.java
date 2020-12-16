package lt.papersoda.pop3.commands;

import lt.papersoda.pop3.pojo.Response;
import lt.papersoda.pop3.user.UserConnectionState;
import lt.papersoda.pop3.user.UserSessionState;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class PassCommand implements ICommand {
    @Override
    public Response apply(List<String> arguments, UserSessionState userSessionState) {
        if (userSessionState.getUserConnectionState() != UserConnectionState.AUTHORIZATION)
            return new Response("-ERR: invalid connection state, already authorized");

        if (argumentsNotValid(arguments))
            return new Response("-ERR: invalid arguments provided");

        if (ObjectUtils.isEmpty(userSessionState.getUser()))
            return new Response("-ERR: use USER command before PASS");

        if (!userSessionState.getUser().getPassword().equals(arguments.get(0)))
            return new Response("-ERR: invalid password provided");

        // check if passwords match
        return new Response("+OK: authorization successful")
                .setShouldUserConnectionStateChange(true)
                .setUserConnectionState(UserConnectionState.TRANSACTION);
    }

    private boolean argumentsNotValid(List<String> arguments) {
        return arguments.size() != 1;
    }
}
