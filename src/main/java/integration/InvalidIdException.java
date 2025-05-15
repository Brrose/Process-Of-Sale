package integration;


public class InvalidIdException extends Exception {
    
    public InvalidIdException (String itemWithInvalidId) {
        super("Unable to scan item with id " + itemWithInvalidId + " due to it being invalid.");
    }
}
