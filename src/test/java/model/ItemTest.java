package model;

import dto.ItemDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private Item item;

    @BeforeEach
    void setUp() {
        item = new Item("abc123", "Test item description", "Test Item", 9.99f, 6f);
    }

    @Test
    void constructor_ShouldInitializeCorrectly() {
        assertEquals("abc123", item.getId());
        assertEquals("Test item description", item.getDescription());
        assertEquals("Test Item", item.getName());
        assertEquals(9.99f, item.getPrice());
        assertEquals(6f, item.getVat());
        assertEquals(1, item.getQuantity());
    }

    @Test
    void constructor_FromDTO_ShouldInitializeCorrectly() {
        ItemDTO dto = new ItemDTO("id123", "DTO description", "DTO Name", 5.5f, 12f);
        Item itemFromDTO = new Item(dto);
        assertEquals("id123", itemFromDTO.getId());
        assertEquals("DTO description", itemFromDTO.getDescription());
        assertEquals("DTO Name", itemFromDTO.getName());
        assertEquals(5.5f, itemFromDTO.getPrice());
        assertEquals(12f, itemFromDTO.getVat());
        assertEquals(1, itemFromDTO.getQuantity());
    }

    @Test
    void increaseQuantity_ShouldAddCorrectAmount() {
        item.increaseQuantity(2);
        assertEquals(3, item.getQuantity());
    }

    @Test
    void setAndGetId_ShouldWorkCorrectly() {
        item.setId("newId");
        assertEquals("newId", item.getId());
    }

    @Test
    void setAndGetDescription_ShouldWorkCorrectly() {
        item.setDescription("New Description");
        assertEquals("New Description", item.getDescription());
    }

    @Test
    void setAndGetName_ShouldWorkCorrectly() {
        item.setName("New Name");
        assertEquals("New Name", item.getName());
    }

    @Test
    void setAndGetPrice_ShouldWorkCorrectly() {
        item.setPrice(19.99f);
        assertEquals(19.99f, item.getPrice());
    }

    @Test
    void setAndGetVat_ShouldWorkCorrectly() {
        item.setVat(25f);
        assertEquals(25f, item.getVat());
    }

    @Test
    void setAndGetQuantity_ShouldWorkCorrectly() {
        item.setQuantity(10);
        assertEquals(10, item.getQuantity());
    }

    @Test
    void generateDTO_ShouldReturnCorrectDTO() {
        ItemDTO dto = item.generateDTO();
        assertEquals(item.getId(), dto.id());
        assertEquals(item.getDescription(), dto.description());
        assertEquals(item.getName(), dto.name());
        assertEquals(item.getPrice(), dto.price());
        assertEquals(item.getVat(), dto.vat());
    }
}
