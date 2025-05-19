import integration.DatabaseFailureException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseFailureExceptionTest {

    @Test
    public void testExceptionMessage() {
        String itemId = "fel123";
        DatabaseFailureException exception = new DatabaseFailureException("Database error while searching for item " + itemId);
        assertEquals("Database error while searching for item fel123", exception.getMessage());
    }

    @Test
    public void testExceptionIsThrown() {
        String itemId = "fel123";
        assertThrows(DatabaseFailureException.class, () -> {
            simulateDatabaseFailure(itemId);
        });
    }

    private void simulateDatabaseFailure(String itemId) throws DatabaseFailureException {
        if ("fel123".equals(itemId)) {
            throw new DatabaseFailureException("Database error while searching for item " + itemId);
        }
    }
}