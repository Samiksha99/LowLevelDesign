public class DebugLogger extends LogHandler {
    public DebugLogger(int level, LogAppender logAppender) {
        super(level, logAppender);
    }

    @Override
    protected void write(String message) {
        System.out.println("DEBUG: " + message);
    }
}
