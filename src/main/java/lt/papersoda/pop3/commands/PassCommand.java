package lt.papersoda.pop3.commands;

import lt.papersoda.pop3.pojo.ErrorResponse;
import lt.papersoda.pop3.pojo.Response;
import lt.papersoda.pop3.pojo.SuccessResponse;
import lt.papersoda.pop3.user.UserConnectionState;
import lt.papersoda.pop3.user.UserSessionState;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class PassCommand implements ICommand {
    @Override
    public Response apply(List<String> arguments, UserSessionState userSessionState) {
        if (userSessionState.getUserConnectionState() != UserConnectionState.AUTHORIZATION)
            return new ErrorResponse("invalid connection state, already authorized");

        if (argumentsNotValid(arguments))
            return new ErrorResponse("invalid arguments provided");

        if (ObjectUtils.isEmpty(userSessionState.getUser()))
            return new ErrorResponse("use USER command before PASS");

        if (!userSessionState.getUser().getPassword().equals(arguments.get(0)))
            return new ErrorResponse("invalid password provided");

        return new SuccessResponse("authorization successful")
                .setShouldUserConnectionStateChange(true)
                .setUserConnectionState(UserConnectionState.TRANSACTION);
    }

    private boolean argumentsNotValid(List<String> arguments) {
        return arguments.size() != 1;
    }
}
