package client.exception;

import java.io.IOException;

public class ClientDetachingException extends RuntimeException {
    public ClientDetachingException(String message, IOException cause) {
        super(message, cause);
    }
}
