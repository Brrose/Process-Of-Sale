package integration;

/**
 * Thrown to indicate a failure in the database.
 * This is exceptions is used to test error handling when the system can't get data from the inventory database.
 */
public class DatabaseFailureException extends Exception{
    
    /**
     * Creates a new instance of {@code DatabaseFailureException} with an error message.
     * @param msg The message describing what caused the exception.
     */
    public DatabaseFailureException (String msg) {
        super(msg);
    }
    
}
