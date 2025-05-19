import integration.InvalidIdException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InvalidIdExceptionTest {

    @Test
    public void testExceptionMessage() {
        String invalidId = "abc23";
        InvalidIdException exception = new InvalidIdException(invalidId);
        String expectedMessage = "Unable to scan item with id abc23 due to it being invalid.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testExceptionIsThrown() {
        String invalidId = "abc23";
        assertThrows(InvalidIdException.class, () -> {
            simulateInvalidItemScan(invalidId);
        });
    }

    private void simulateInvalidItemScan(String itemId) throws InvalidIdException {
        if (!"abc123".equals(itemId)) {
            throw new InvalidIdException(itemId);
        }
    }
}