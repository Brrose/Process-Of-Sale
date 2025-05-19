package view;

import controller.Controller;
import dto.ItemDTO;
import dto.SaleDTO;
import integration.DatabaseFailureException;
import integration.InvalidIdException;
import java.io.IOException;
import util.LogHandler;
import util.TotalRevenueFileOutput;

/**
 * The {@code View} class represents the user interface. It imitates interactions with the system,
 * like starting a sale, scanning items, paying and printing the receipt.
 */
public class View {
    
    private Controller controller;
    private ErrorMessageHandler errorMsgHandler = new ErrorMessageHandler();
    private LogHandler logHandler;

    /**
     * Creates a new {@code View} instance with a reference to the controller and log handler.
     * It also registers observers to track the total revenue.
     * @param controller The {@link Controller} that handles the program's logic.
     * @param logHandler The {@link LogHandler} responsible for logging exceptions.
     */
    public View(Controller controller, LogHandler logHandler) {
        this.controller = controller;
        this.logHandler = logHandler;
        try {
         controller.addSaleObserver(new TotalRevenueView());
         controller.addSaleObserver(new TotalRevenueFileOutput());
        }
        catch (IOException ioe) {
            System.out.println("File for total revenue could not be initialized.");
        }
    }

    /**
     * Simulates a complete purchase, including starting a sale, scanning items, 
     * printing the total cost, making a payment, printing the receipt and 
     * ending the sale with notifying the observers.
     * Exceptions like invalid item id or database failures are handled with error messages and logging.
     */
    public void samplePurchase(){
        try {
            controller.startSale();
            
            scanItem("abc123", 2);

            scanItem("def456", 1);
            
            System.out.println("End sale: \nTotal cost (incl VAT): " + controller.getSaleTotal() + "\n");
        
            controller.pay(100);

            String receipt = controller.getReceipt();
            System.out.println(receipt);
            controller.endSale();
        }
        catch (InvalidIdException iie) {
            errorMsgHandler.showErrorMsg(iie.getMessage());
        }
        catch (DatabaseFailureException dbfe) {
            errorMsgHandler.showErrorMsg("Server is not responding.");
            logHandler.logException(dbfe);
        }
        
    }
    
    /**
     * Simulates scanning a specific quantity of an item.
     * @param itemID The id of the item being scanned.
     * @param quantity The number of times to scan the item.
     * @throws InvalidIdException If the item id is invalid.
     * @throws DatabaseFailureException If the database is not running.
     */
    private void scanItem (String itemID, int quantity) throws InvalidIdException, DatabaseFailureException {
        for (int i = 0; i < quantity; i++) {
            ItemDTO item = controller.scanItem(itemID);
            printScannedItem(item);
        }
    }

    /**
     * Prints the details of a scanned item, or displays an error if the item is invalid.
     * @param item The {@link ItemDTO} to display.
     */
    private void printScannedItem(ItemDTO item) {
        if (item == null) {
            System.out.println("Item identifier is invalid!");
        } else {
            SaleDTO sale = controller.getCurrentSale();
            System.out.println("Add 1 item with item id " + item.id() + ":\n" + 
            "Item ID: " + item.id() + "\n" + "Item name: " + item.name() + 
            "\n" + "Item cost: " + String.format("%.2f",item.price()) + " SEK\n" + "VAT: " + 
            String.format("%.0f",item.vat()) + "% \r\n" + "Item description: " + item.description() + "\n" + 
            "Total cost (incl VAT): " + String.format("%.2f", sale.totalPrice()) + " SEK \n" + "Total VAT: " + 
            String.format("%.2f", sale.totalVAT()) + " SEK\n");
        }
    }
}
