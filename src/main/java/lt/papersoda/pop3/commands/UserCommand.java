package lt.papersoda.pop3.commands;

import lt.papersoda.pop3.db.entity.UserEntity;
import lt.papersoda.pop3.db.repository.IUserRepository;
import lt.papersoda.pop3.pojo.Response;
import lt.papersoda.pop3.user.UserConnectionState;
import lt.papersoda.pop3.user.UserSessionState;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCommand implements ICommand {
    final IUserRepository userRepository;

    @Autowired
    public UserCommand(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Response apply(List<String> arguments, UserSessionState userSessionState) {
        if (userSessionState.getUserConnectionState() != UserConnectionState.AUTHORIZATION)
            return new Response("-ERR: invalid connection state");

        if (ObjectUtils.isNotEmpty(userSessionState.getUser()))
            return new Response("-ERR: user already set");

        if (argumentsNotValid(arguments))
            return new Response("-ERR: invalid command arguments");

        final Optional<UserEntity> user = userRepository.findUserByName(arguments.get(0));

        if (user.isEmpty())
            return new Response("-ERR: user not found");

        userSessionState.setUser(user.get());

        return new Response("+OK: response from user command");
    }

    private boolean argumentsNotValid(List<String> arguments) {
        return arguments.size() != 1;
    }
}
