package lt.papersoda.pop3.core;

import lt.papersoda.pop3.commands.enums.PopCommands;
import lt.papersoda.pop3.pojo.ClientRequest;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Service
public class RequestParser implements IRequestParser {

    public ClientRequest parseClientRequest(final String rawRequest) {
        var unrecognizedRequest = new ClientRequest(PopCommands.UNRECOGNIZED, List.of());

        if (StringUtils.isEmpty(rawRequest))
            return unrecognizedRequest;

        final String[] words = rawRequest.trim().split(" ");
        final Predicate<String[]> hasArguments = (elements) -> elements.length > 1;

        if (
                ArrayUtils.isEmpty(words)
                || !EnumUtils.isValidEnum(PopCommands.class, words[0])
        ) {
            return unrecognizedRequest;
        }

        if (hasArguments.test(words)) {
            return new ClientRequest(
                    PopCommands.valueOf(words[0]),
                    Arrays.asList(words).subList(1, words.length)
            );
        }

        return new ClientRequest(
                PopCommands.valueOf(words[0]),
                List.of()
        );
    }
}
