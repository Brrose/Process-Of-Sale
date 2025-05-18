package controller;

import dto.ItemDTO;
import dto.SaleDTO;
import integration.DatabaseFailureException;
import integration.InvalidIdException;
import model.SaleObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Controller class. This test verifies that
 * the controller correctly handles sale operations such as scanning items,
 * calculating totals, processing payments, and managing observers.
 */
public class ControllerTest {

    private Controller controller;

    /**
     * Initializes a new Controller and starts a sale before each test.
     */
    @BeforeEach
    void setUp() {
        controller = new Controller();
        controller.startSale();
    }

    /**
     * Verifies that starting a sale initializes the current sale properly.
     */
    @Test
    void testStartSaleInitializesSaleCorrectly() {
        assertNotNull(controller.getCurrentSale(), "Sale should be initialized after calling startSale.");
    }

    /**
     * Verifies that scanning a valid item returns the correct item data and updates the total.
     */
    @Test
    void testScanValidItemAddsToSale() throws InvalidIdException, DatabaseFailureException {
        ItemDTO item = controller.scanItem("abc123");
        assertNotNull(item);
        assertEquals("abc123", item.id());
        assertEquals("BigWheel Oatmeal", item.name());
        assertEquals("29,90", controller.getSaleTotal());
    }

    /**
     * Verifies that scanning the same item twice increases its quantity but not the list size.
     */
    @Test
    void testScanSameItemIncreasesQuantity() throws InvalidIdException, DatabaseFailureException {
        controller.scanItem("abc123");
        controller.scanItem("abc123");
        SaleDTO saleDTO = controller.getCurrentSale();
        assertEquals(1, saleDTO.items().size(), "Should still be one item in sale.");
    }

    /**
     * Verifies that scanning an invalid item ID throws an InvalidIdException.
     */
    @Test
    void testScanInvalidItemThrowsInvalidIdException() {
        assertThrows(InvalidIdException.class, () -> {
            controller.scanItem("invalid001");
        });
    }

    /**
     * Verifies that scanning an item when the database fails throws a DatabaseFailureException.
     */
    @Test
    void testScanItemWithDatabaseFailureThrowsDatabaseFailureException() {
        assertThrows(DatabaseFailureException.class, () -> {
            controller.scanItem("fel123");
        });
    }

    /**
     * Verifies that paying updates the register with cash and calculates the correct change.
     */
    @Test
    void testPayUpdatesRegisterAndChange() throws InvalidIdException, DatabaseFailureException {
        controller.scanItem("abc123");
        controller.pay(100f);
        SaleDTO saleDTO = controller.getCurrentSale();
        assertEquals(100f, saleDTO.cash());
        assertEquals(100f - saleDTO.totalPrice(), saleDTO.change(), 0.001);
    }

    /**
     * Verifies that the receipt generated after payment contains correct item information.
     */
    @Test
    void testGetReceiptReturnsNonNullString() throws InvalidIdException, DatabaseFailureException {
        controller.scanItem("abc123");
        controller.pay(50f);
        String receipt = controller.getReceipt();
        assertNotNull(receipt);
        assertTrue(receipt.contains("BigWheel Oatmeal"));
    }

    /**
     * Verifies that ending a sale notifies registered observers without throwing exceptions.
     */
    @Test
    void testEndSaleNotifiesObserversWithoutErrors() {
        SaleObserver observer = saleDTO -> assertNotNull(saleDTO);
        controller.addSaleObserver(observer);
        controller.endSale(); 
    }
}
