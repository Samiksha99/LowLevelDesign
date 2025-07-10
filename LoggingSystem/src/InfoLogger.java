public class InfoLogger extends LogHandler {

    public InfoLogger(int level, LogAppender logAppender) {
        super(level, logAppender);
    }

    @Override
    protected void write(String message) {
        System.out.println("INFO: " + message);
    }
}
