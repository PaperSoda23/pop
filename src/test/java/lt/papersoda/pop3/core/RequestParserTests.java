package lt.papersoda.pop3.core;

import lt.papersoda.pop3.commands.enums.PopCommands;
import lt.papersoda.pop3.pojo.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class RequestParserTests {
    RequestParser requestParser;

    @BeforeEach
    public void init() {
        requestParser = new RequestParser();
    }

    @ParameterizedTest
    @MethodSource("providePopRequestData")
    void recognizes_all_valid_pop_commands(String rawRequest, PopCommands popCommand) {
        ClientRequest clientRequest = requestParser.parseClientRequest(rawRequest);

        assertThat(clientRequest.getCommand()).isEqualTo(popCommand);
    }

    static Stream<Arguments> providePopRequestData() {
        return Stream.of(
                arguments("USER jack",  PopCommands.USER),
                arguments("PASS 1234", PopCommands.PASS),
                arguments("RETR 1", PopCommands.RETR),
                arguments("QUIT", PopCommands.QUIT),
                arguments("DELE 2", PopCommands.DELE),
                arguments("STAT 1 3", PopCommands.STAT),
                arguments("TOP", PopCommands.TOP)
        );
    }

    @Test
    void resolves_to_UNRECOGNIZED_command_when_command_is_not_recognized() {
        String rawRequest = "RANDOM request";

        ClientRequest clientRequest = requestParser.parseClientRequest(rawRequest);

        assertThat(clientRequest.getCommand()).isEqualTo(PopCommands.UNRECOGNIZED);
    }

    @ParameterizedTest
    @MethodSource("commandArgumentData")
    void contains_expected_arguments(String rawRequest, List<String> expectedArguments) {
        ClientRequest clientRequest = requestParser.parseClientRequest(rawRequest);

        assertThat(clientRequest.getArguments()).isEqualTo(expectedArguments);
    }

    static Stream<Arguments> commandArgumentData() {
        return Stream.of(
                arguments("USER", List.of()),
                arguments("USER ARG1", List.of("ARG1")),
                arguments("USER ARG1 ARG2", List.of("ARG1", "ARG2"))
        );
    }

    @Test
    void has_no_arguments_when_command_is_not_recognized() {
        String rawRequest = "RANDOM request";

        ClientRequest clientRequest = requestParser.parseClientRequest(rawRequest);

        assertThat(clientRequest.getArguments()).isEqualTo(List.of());
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void returns_UNRECOGNIZED_on_forbidden_arguments(String rawRequest) {
        ClientRequest clientRequest = requestParser.parseClientRequest(rawRequest);

        assertThat(clientRequest.getCommand()).isEqualTo(PopCommands.UNRECOGNIZED);
    }
}
