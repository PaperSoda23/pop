package lt.papersoda.pop3;

public interface IServer {
    void start();
    void stop();
    boolean isRunning();
    int getPort();
}
