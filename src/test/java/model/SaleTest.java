package model;

import dto.ItemDTO;
import dto.SaleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    private Sale sale;
    private Item item1;
    private Item item2;

    @BeforeEach
    void setUp() {
        sale = new Sale();
        item1 = new Item("abc123", "Oats", "BigWheel Oatmeal", 10.0f, 6.0f);
        item2 = new Item("def456", "Yogurt", "YouGoGo Blueberry", 20.0f, 6.0f);
    }

    @Test
    void constructor_ShouldInitializeFieldsCorrectly() {
        assertEquals(0f, sale.getTotalPrice(), 0.0001f);
        assertEquals(0f, sale.getTotalVAT(), 0.0001f);
        assertEquals(0f, sale.getCash(), 0.0001f);
        assertEquals(0f, sale.getChange(), 0.0001f);
        assertTrue(sale.getItems().isEmpty());
    }

    @Test
    void addItemToSale_ShouldAddItemCorrectly() {
        sale.addItemToSale(item1);
        assertTrue(sale.getItems().contains(item1));
    }

    @Test
    void isItemInSale_ShouldReturnCorrectly() {
        assertFalse(sale.isItemInSale("abc123"));
        sale.addItemToSale(item1);
        assertTrue(sale.isItemInSale("abc123"));
    }

    @Test
    void getItemFromSale_ShouldReturnCorrectItem() {
        sale.addItemToSale(item1);
        assertEquals(item1, sale.getItemFromSale("abc123"));
        assertNull(sale.getItemFromSale("not_exist"));
    }

    @Test
    void increaseTotalPrice_ShouldUpdateTotalPriceCorrectly() {
        sale.increaseTotalPrice(10f);
        assertEquals(10f, sale.getTotalPrice(), 0.0001f);
        sale.increaseTotalPrice(5.5f);
        assertEquals(15.5f, sale.getTotalPrice(), 0.0001f);
    }

    @Test
    void calculateTotalVat_ShouldUpdateVATCorrectly() {
        sale.calculateTotalVat(6f, 10f); // 10 * 6% = 0.6
        assertEquals(0.6f, sale.getTotalVAT(), 0.0001f);
    }

    @Test
    void setAndGetCash_ShouldWorkCorrectly() {
        sale.setCash(50f);
        assertEquals(50f, sale.getCash(), 0.0001f);
    }

    @Test
    void setAndGetChange_ShouldWorkCorrectly() {
        sale.setChange(20f);
        assertEquals(20f, sale.getChange(), 0.0001f);
    }

    @Test
    void createReceipt_ShouldContainCorrectDetails() {
        sale.addItemToSale(item1);
        sale.increaseTotalPrice(item1.getPrice());
        sale.calculateTotalVat(item1.getVat(), item1.getPrice());
        sale.setCash(50f);
        sale.setChange(40f);
        String receipt = sale.createReceipt();

        assertTrue(receipt.contains("BigWheel Oatmeal"));
        assertTrue(receipt.contains("Total:"));
        assertTrue(receipt.contains("Cash:"));
        assertTrue(receipt.contains("Change:"));
    }

    @Test
    void generateDTO_ShouldReturnCorrectData() {
        sale.addItemToSale(item1);
        sale.increaseTotalPrice(item1.getPrice());
        sale.calculateTotalVat(item1.getVat(), item1.getPrice());
        sale.setCash(100f);
        sale.setChange(90f);

        SaleDTO dto = sale.generateDTO();
        ArrayList<ItemDTO> itemDTOs = dto.items();

        assertEquals(1, itemDTOs.size());
        assertEquals("abc123", itemDTOs.get(0).id());
        assertEquals(10f, dto.totalPrice(), 0.0001f);
        assertEquals(0.6f, dto.totalVAT(), 0.0001f);
        assertEquals(100f, dto.cash(), 0.0001f);
        assertEquals(90f, dto.change(), 0.0001f);
    }
}