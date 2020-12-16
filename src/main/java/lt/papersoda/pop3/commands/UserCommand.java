package lt.papersoda.pop3.commands;

import lt.papersoda.pop3.pojo.Response;

import java.util.List;

public class UserCommand implements ICommand {
    @Override
    public Response apply(List<String> arguments) {
        // check if user with name exists
        return new Response("response from user command");
    }
}
