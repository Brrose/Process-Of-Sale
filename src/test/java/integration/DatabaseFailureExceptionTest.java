import integration.DatabaseFailureException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the functionality of the DatabaseFailureException class.
 */
public class DatabaseFailureExceptionTest {

    /**
     * Tests that the exception message is correctly passed and stored in the exception.
     */
    @Test
    public void testExceptionMessage() {
        String itemId = "failDB";
        DatabaseFailureException exception = new DatabaseFailureException("Database error while searching for item " + itemId);
        assertEquals("Database error while searching for item failDB", exception.getMessage());
    }

    /**
     * Tests that the DatabaseFailureException is thrown when a database failure is simulated.
     */
    @Test
    public void testExceptionIsThrown() {
        String itemId = "failDB";
        assertThrows(DatabaseFailureException.class, () -> {
            simulateDatabaseFailure(itemId);
        });
    }

    private void simulateDatabaseFailure(String itemId) throws DatabaseFailureException {
        if ("failDB".equals(itemId)) {
            throw new DatabaseFailureException("Database error while searching for item " + itemId);
        }
    }
}