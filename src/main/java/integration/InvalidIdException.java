package integration;

/**
 * Thrown to show that an item id that was scanned is invalid.
 * This exception is thrown when the program fails to find an item in the inventory with the provided id.
 */
public class InvalidIdException extends Exception {
    
    /**
     * Creates a new instance of {@code InvalidIdException} with an error message.
     * @param itemWithInvalidId The id of the item that caused the exception.
     */
    public InvalidIdException (String itemWithInvalidId) {
        super("Unable to scan item with id " + itemWithInvalidId + " due to it being invalid.");
    }
}
