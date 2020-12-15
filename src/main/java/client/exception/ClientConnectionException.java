package client.exception;

import java.io.IOException;

public class ClientConnectionException extends RuntimeException {
    public ClientConnectionException(String message, IOException cause) {
        super(message, cause);
    }
}
