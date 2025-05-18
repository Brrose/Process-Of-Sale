package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * The {@code LogHandler} class handles logging exceptions to a file. It writes exception messages with timestamps to a file.
 */
public class LogHandler {
    private static final String LOG_FILE_NAME = "pos-log.txt";
    private PrintWriter logFile;
    
    /**
     * Creates a new {@code LogHandler} and opens the file to append messages.
     * @throws IOException If the file can't be created or opened.
     */
    public LogHandler() throws IOException {
        logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME, true), true);
    }
    
    /**
     * Logs an exception to the file with a timestamp.
     * @param exception The {@link Exception} that will be logged.
     */
    public void logException (Exception exception) {
        StringBuilder logMessageBuilder = new StringBuilder();
        logMessageBuilder.append(createTime());
        logMessageBuilder.append(", Exception was thrown: ");
        logMessageBuilder.append(exception.getMessage());
        logFile.println(logMessageBuilder);
        exception.printStackTrace(logFile);
        logFile.println("\n");
    }
    
    /**
     * Generates a timestamp for the current date and time of the exception.
     * @return A {@code String} representing the current date and time.
     */
    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}
