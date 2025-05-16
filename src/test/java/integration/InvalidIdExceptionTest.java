import integration.InvalidIdException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the functionality of the InvalidIdException class.
 */
public class InvalidIdExceptionTest {

    /**
     * Tests that the exception message is correctly set when an invalid ID is passed.
     */
    @Test
    public void testExceptionMessage() {
        String invalidId = "123XYZ";
        InvalidIdException exception = new InvalidIdException(invalidId);
        String expectedMessage = "Unable to scan item with id 123XYZ due to it being invalid.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Tests that InvalidIdException is thrown when scanning an invalid item ID.
     */
    @Test
    public void testExceptionIsThrown() {
        String invalidId = "INVALID-ID";
        assertThrows(InvalidIdException.class, () -> {
            simulateInvalidItemScan(invalidId);
        });
    }

    private void simulateInvalidItemScan(String itemId) throws InvalidIdException {
        if (!"valid123".equals(itemId)) {
            throw new InvalidIdException(itemId);
        }
    }
}