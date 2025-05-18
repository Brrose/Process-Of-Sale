package integration;

import dto.ItemDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }
    
    @Test
    void testGetItemReturnsCorrectItemForValidId() throws InvalidIdException, DatabaseFailureException {
        ItemDTO item = inventory.getItem("abc123");
        assertNotNull(item);
        assertEquals("abc123", item.id());
        assertEquals("BigWheel Oatmeal", item.name());
        assertEquals(29.90, item.price(), 0.01);
    }

    @Test
    void testGetItemThrowsInvalidIdExceptionForInvalidId() {
        assertThrows(InvalidIdException.class, () -> {
            inventory.isValidItem("abc23");
        });
    }

    @Test
    void testGetItemThrowsDatabaseFailureExceptionForSimulatedFailure() {
        assertThrows(DatabaseFailureException.class, () -> {
            inventory.isValidItem("fel123");
        });
    }
}

