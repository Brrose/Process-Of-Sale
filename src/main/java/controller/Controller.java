package controller;

import dto.ItemDTO;
import dto.SaleDTO;
import model.Item;
import model.Register;
import model.Sale;
import integration.Inventory;

/**
 * The @code Controller class control the operations between the different layers.
 * It takes care of the sale process which includes starting a sale, scanning the items, processing the payment and creating a receipt.
 */
public class Controller {

    private Sale sale;
    private Inventory inventory;
    private Register register;

    /**
     * Creates a new instance of the Controller with inventory and register intitialized.
     */
    public Controller() {
        this.inventory = new Inventory();
        this.register = new Register();
    }
    
    /**
     * Starts a new sale by creating a new @link Sale instance.
     */
    public void startSale() {
        this.sale = new Sale();
    }
    
    /**
     * Scans an item by its id. If the item is already in the current sale, its quantity increases. If it's not in the sale but exists in the inventory, it is added.
     * @param itemId The identifier of the item being scanned.
     * @return A @link ItemDTO that represents the scanned item, or @code null if the identifier is invalid.
     */
    public ItemDTO scanItem(String itemId) {
        Item item;
        if (sale.isItemInSale(itemId)) {
            // öka kvantitet
            item = sale.getItemFromSale(itemId);
            item.increaseQuantity(1);
        }
        else {
            if (inventory.isValidItem(itemId)) {
                // lägg till i sale
                ItemDTO itemDTO = inventory.getItem(itemId);
                item = new Item(itemDTO);
                sale.addItemToSale(item);
            }
            else {
                // returnera error
                return null;
            }
        }
        // öka runningtotal
        sale.increaseTotalPrice(item.getPrice());
        sale.calculateTotalVat(item.getVat(), item.getPrice());
        // returnera data
        return item.generateDTO();
    }
    
    /**
     * Gets the total price of the sale including VAT.
     * @return A @code String representing the total sale price, formatted to two decimals.
     */
    public String getSaleTotal() {
        // returnera totalprice incl VAT
        return String.format("%.2f", sale.getTotalPrice());
    }
    
    /**
     * Processes the payment made by the customer by updating the register and storing it in @link Sale. Calculates the change and stores it in @link Sale to be given to customer.
     * @param amountPaid The amount of money the customer paid with.
     */
    public void pay(float amountPaid) {
        sale.setCash(amountPaid);
        register.updateTotal(sale.getCash());
        // calculate change och returnera change
        sale.setChange(sale.getCash() - sale.getTotalPrice());
    }
    
    /**
     * Creates a receipt for the current sale.
     * @return A @code String with the receipt text.
     */
    public String getReceipt() {
        return sale.createReceipt();
    }
    
    /**
     * Retrieves a DTO that represents the current sale.
     * @return A @link SaleDTO that contains the current sale's data.
     */
    public SaleDTO getCurrentSale() {
        return this.sale.generateDTO();
    }
}
