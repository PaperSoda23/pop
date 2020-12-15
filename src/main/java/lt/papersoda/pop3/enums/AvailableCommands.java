package lt.papersoda.pop3.enums;

public enum AvailableCommands {
    /**
     * State: AUTHORIZATION
     * params: 1 (user name)
     * example: USER john321
     */
    USER,
    /**
     * State: AUTHORIZATION
     * params: 1 (password)
     * example: PASS pass1234
     */
    PASS,
    STAT,
    RETR,
    TOP,
    DELE,
    QUIT
}
