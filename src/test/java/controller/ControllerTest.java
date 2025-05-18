package controller;

import dto.ItemDTO;
import dto.SaleDTO;
import integration.DatabaseFailureException;
import integration.InvalidIdException;
import model.SaleObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    private Controller controller;

    @BeforeEach
    void setUp() {
        controller = new Controller();
        controller.startSale();
    }

    @Test
    void testStartSaleInitializesSaleCorrectly() {
        assertNotNull(controller.getCurrentSale(), "Sale should be initialized after calling startSale.");
    }

    @Test
    void testScanValidItemAddsToSale() throws InvalidIdException, DatabaseFailureException {
        ItemDTO item = controller.scanItem("abc123");
        assertNotNull(item);
        assertEquals("abc123", item.id());
        assertEquals("BigWheel Oatmeal", item.name());
        assertEquals("29,90", controller.getSaleTotal());
    }

    @Test
    void testScanSameItemIncreasesQuantity() throws InvalidIdException, DatabaseFailureException {
        controller.scanItem("abc123");
        controller.scanItem("abc123");
        SaleDTO saleDTO = controller.getCurrentSale();
        assertEquals(1, saleDTO.items().size(), "Should still be one item in sale.");
    }

    @Test
    void testScanInvalidItemThrowsInvalidIdException() {
        assertThrows(InvalidIdException.class, () -> {
            controller.scanItem("invalid001");
        });
    }

    @Test
    void testScanItemWithDatabaseFailureThrowsDatabaseFailureException() {
        assertThrows(DatabaseFailureException.class, () -> {
            controller.scanItem("fel123");
        });
    }

    @Test
    void testPayUpdatesRegisterAndChange() throws InvalidIdException, DatabaseFailureException {
        controller.scanItem("abc123");
        controller.pay(100f);
        SaleDTO saleDTO = controller.getCurrentSale();
        assertEquals(100f, saleDTO.cash());
        assertEquals(100f - saleDTO.totalPrice(), saleDTO.change(), 0.001);
    }

    @Test
    void testGetReceiptReturnsNonNullString() throws InvalidIdException, DatabaseFailureException {
        controller.scanItem("abc123");
        controller.pay(50f);
        String receipt = controller.getReceipt();
        assertNotNull(receipt);
        assertTrue(receipt.contains("BigWheel Oatmeal"));
    }

    @Test
    void testEndSaleNotifiesObserversWithoutErrors() {
        SaleObserver observer = saleDTO -> assertNotNull(saleDTO);
        controller.addSaleObserver(observer);
        controller.endSale(); 
    }
}
