public class ErrorLogger extends LogHandler {
    public ErrorLogger(int level, LogAppender logAppender) {
        super(level, logAppender);
    }

    @Override
    protected void write(String message) {
        System.out.println("ERROR: " + message);
    }
}
