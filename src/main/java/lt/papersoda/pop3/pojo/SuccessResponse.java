package lt.papersoda.pop3.pojo;

public class SuccessResponse extends Response {
    public static final String prefix = "+OK: ";
    public SuccessResponse(String response) {
        super(prefix + response);
    }
}
