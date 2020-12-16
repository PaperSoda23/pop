package lt.papersoda.pop3.pojo;

public class ErrorResponse extends Response {
    public static final String prefix = "-ERR: ";

    public ErrorResponse(String response) {
        super(prefix + response);
    }
}
