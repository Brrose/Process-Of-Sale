package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import controller.Controller;
import dto.ItemDTO;
import integration.DatabaseFailureException;
import integration.InvalidIdException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import startup.Main;
import util.LogHandler;
import view.TotalRevenueView;
import view.View;

public class ConsoleOutputTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testMainMethodPrintsOutput() throws IOException {
        Main.main(null);
        String output = outContent.toString();
        assertTrue(output.contains("Total cost (incl VAT):"));
        assertTrue(output.contains("Begin receipt"));
        assertTrue(output.contains("End receipt"));
        assertTrue(output.contains("Total revenue is:"));
    }

    @Test
    void testTotalRevenueViewPrintsTotal() {
        TotalRevenueView revenueView = new TotalRevenueView();
        revenueView.newSale(new dto.SaleDTO(200.0f, 50.0f, null, 0, 0));
        String output = outContent.toString();
        assertTrue(output.contains("Total revenue is: 200.0"));
    }

    @Test
    void testScanItemPrintsCorrectInfo() throws IOException, InvalidIdException, DatabaseFailureException {
        Controller controller = new Controller();
        View view = new View(controller, new LogHandler());
        controller.startSale();
        view.samplePurchase(); 
        String output = outContent.toString();
        assertTrue(output.contains("Item ID: abc123") || output.contains("Item ID: def456"));
        assertTrue(output.contains("Item name:"));
        assertTrue(output.contains("Item cost:"));
    }

    @Test
    void testReceiptPrintout() throws IOException {
        Controller controller = new Controller();
        View view = new View(controller, new LogHandler());
        view.samplePurchase();
        String output = outContent.toString();
        assertTrue(output.contains("Begin receipt"));
        assertTrue(output.contains("Time of Sale:"));
        assertTrue(output.contains("Cash:"));
        assertTrue(output.contains("Change:"));
        assertTrue(output.contains("End receipt"));
    }

    @Test
    void testInvalidItemPrintout() throws IOException, DatabaseFailureException {
        Controller controller = new Controller();
        View view = new View(controller, new LogHandler());
        controller.startSale();
        try {
            controller.scanItem("invalid123");
        } catch (InvalidIdException e) {
           
        }
        view.samplePurchase();
        String output = outContent.toString();
        assertTrue(output.contains("Total cost (incl VAT):") || output.contains("Server is not responding."));
    }
}
