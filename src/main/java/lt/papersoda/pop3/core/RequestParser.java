package lt.papersoda.pop3.core;

import lt.papersoda.pop3.enums.AvailableCommands;
import lt.papersoda.pop3.pojo.ClientRequest;

import java.util.Arrays;

public class RequestParser implements IRequestParser {

    public ClientRequest parseClientRequest(final String rawRequest) {

        final String[] words = rawRequest.split(" ");

        return new ClientRequest(
                AvailableCommands.valueOf(words[0]),
                words[0],
                Arrays.asList(words).subList(1, words.length)
        );
    }
}
