package lt.papersoda.pop3.pojo;

import lt.papersoda.pop3.commands.enums.PopCommands;

import java.util.List;

public class ClientRequest {
    private final PopCommands command;
    private final List<String> arguments;

    public ClientRequest(
            final PopCommands command,
            final List<String> arguments
    ) {
        this.command = command;
        this.arguments = arguments;
    }

    public PopCommands getCommand() {
        return command;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
