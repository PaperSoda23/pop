package lt.papersoda.pop3.pojo;

import lt.papersoda.pop3.enums.AvailableCommands;

import java.util.List;

public class ClientRequest {
    private final AvailableCommands command;
    private final String commandStr;
    private final List<String> arguments;

    public ClientRequest(
            final AvailableCommands command,
            final String commandStr,
            final List<String> arguments
    ) {
        this.command = command;
        this.commandStr = commandStr;
        this.arguments = arguments;
    }

    public AvailableCommands getCommand() {
        return command;
    }

    public String getCommandStr() {
        return commandStr;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
