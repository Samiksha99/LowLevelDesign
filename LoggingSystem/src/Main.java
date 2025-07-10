public class Main {
    private static LogHandler chaiOfHandlers(LogAppender appender){
        LogHandler errorLogger = new ErrorLogger(LogHandler.ERROR, appender);
        LogHandler debugLogger = new DebugLogger(LogHandler.DEBUG, appender);
        LogHandler infoLogger = new InfoLogger(LogHandler.INFO, appender);
        infoLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(errorLogger);
        return infoLogger;
    }

    public static void main(String[] args) {

        LogAppender fileAppender = new FileAppender("test.txt");
        LogAppender consoleAppender = new ConsoleAppender();
        LogHandler loggerChain = chaiOfHandlers(consoleAppender);

        loggerChain.logMessage(LogHandler.INFO, "This is info");
        loggerChain.logMessage(LogHandler.DEBUG, "This is debug");
        loggerChain.logMessage(LogHandler.ERROR, "This is error");

    }
}