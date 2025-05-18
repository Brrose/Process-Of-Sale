package view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * The {@code ErrorMessageHandler} class displays error messages to the user.
 */
public class ErrorMessageHandler {
    
    /**
     * Displays an error message with the current timestamp.
     * @param msg The error message to display.
     */
    void showErrorMsg(String msg) {
        StringBuilder errorMsgBuilder = new StringBuilder();
        errorMsgBuilder.append(createTime());
        errorMsgBuilder.append(", ERROR: ");
        errorMsgBuilder.append(msg);
        System.out.println(errorMsgBuilder);
    }
    
    /**
     * Generates the current time.
     * @return A {@code String} representing the current date and time.
     */
    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}
