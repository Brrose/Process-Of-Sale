package integration;

import dto.ItemDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
    }

    @Test
    public void testIsValidItem() {
        assertTrue(inventory.isValidItem("abc123"), "abc123 should be a valid item");
        assertFalse(inventory.isValidItem("invalidID"), "invalidID should not be a valid item");
    }

    @Test
    public void testGetItem() {
        ItemDTO item = inventory.getItem("abc123");
        assertNotNull(item, "Item with ID abc123 should exist");
        assertEquals("BigWheel Oatmeal", item.name(), "Item name should match");

        ItemDTO invalidItem = inventory.getItem("invalidID");
        assertNull(invalidItem, "Item with invalidID should not exist");
    }
}
