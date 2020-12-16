package lt.papersoda.pop3.commands;

import lt.papersoda.pop3.pojo.Response;
import lt.papersoda.pop3.user.UserSessionState;

import java.util.List;

public interface ICommand {
    Response apply(List<String> arguments, UserSessionState userSessionState);
}
