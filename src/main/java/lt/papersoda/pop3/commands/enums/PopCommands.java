package lt.papersoda.pop3.commands.enums;

public enum PopCommands {
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
    /**
     * State: TRANSACTION
     * params: 1 (mail id)
     * example RETR 3
     */
    RETR,
    TOP,
    DELE,
    QUIT,

    UNRECOGNIZED
}
