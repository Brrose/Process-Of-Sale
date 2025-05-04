package view;

import controller.Controller;
import dto.ItemDTO;
import dto.SaleDTO;

public class View {
    
    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void samplePurchase() {
        controller.startSale();
        
        scanItem("abc123", 2);
        
        scanItem("def456", 1);
        
        System.out.println("End sale: \nTotal cost (incl VAT): " + controller.getSaleTotal() + "\n");
        
        controller.pay(100);
        
        String receipt = controller.getReceipt();
        System.out.println(receipt);
    }
    
    private void scanItem (String itemID, int quantity) {
        for (int i = 0; i < quantity; i++) {
            ItemDTO item = controller.scanItem(itemID);
            printScannedItem(item);
        }
    }

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
