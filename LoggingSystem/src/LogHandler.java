import CommonEnums.LogLevel;

abstract class LogHandler {
    public static final int INFO = 1;
    public static final int DEBUG = 2;
    public static final int ERROR =3;

    protected int level;
    protected LogAppender logAppender;
    protected LogHandler nextLogger;

    public LogHandler(int level, LogAppender logAppender) {
        this.level = level;
        this.logAppender = logAppender;
    }

    public void setNextLogger(LogHandler nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message) {
        if(this.level>=level){
            LogLevel logLevel = intToLogLevel(level);
            LogMessage logMessage = new LogMessage(logLevel, message);

            if(logAppender!=null){
                logAppender.append(logMessage);
            }

        }
        else if(nextLogger!=null){
            nextLogger.logMessage(level, message);
        }
    }

    public LogLevel intToLogLevel(int level){
        switch (level) {
            case INFO:
                return LogLevel.INFO;
            case DEBUG:
                return LogLevel.DEBUG;
            case ERROR:
                return LogLevel.ERROR;
            default:
                return LogLevel.INFO;
        }
    }

    abstract protected void write (String message);
}
