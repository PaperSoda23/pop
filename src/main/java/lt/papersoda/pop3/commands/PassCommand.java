package lt.papersoda.pop3.commands;

import lt.papersoda.pop3.pojo.Response;

import java.util.List;

public class PassCommand implements ICommand {
    @Override
    public Response apply(List<String> arguments) {
        return new Response("response from pass command");
    }
}
