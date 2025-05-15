package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class LogHandler {
    private static final String LOG_FILE_NAME = "pos-log.txt";
    private PrintWriter logFile;
    
    public LogHandler() throws IOException {
        logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME, true), true);
    }
    
    public void logException (Exception exception) {
        StringBuilder logMessageBuilder = new StringBuilder();
        logMessageBuilder.append(createTime());
        logMessageBuilder.append(", Exception was thrown: ");
        logMessageBuilder.append(exception.getMessage());
        logFile.println(logMessageBuilder);
        exception.printStackTrace(logFile);
        logFile.println("\n");
    }

    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}
