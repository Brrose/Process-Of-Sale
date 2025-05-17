package controller;

import integration.InvalidIdException;
import dto.ItemDTO;
import dto.SaleDTO;
import integration.DatabaseFailureException;
import model.Item;
import model.Register;
import model.Sale;
import integration.Inventory;
import java.util.ArrayList;
import java.util.List;
import model.SaleObserver;
import view.ErrorMessageHandler;

/**
 * The @code Controller class control the operations between the different layers.
 * It takes care of the sale process which includes starting a sale, scanning the items, processing the payment and creating a receipt.
 */
public class Controller {

    private Sale sale;
    private Inventory inventory;
    private Register register;
    private ErrorMessageHandler logger;
    private List<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * Creates a new instance of the Controller with inventory and register intitialized.
     */
    public Controller() {
        this.inventory = new Inventory();
        this.register = new Register();
 
    }
    
    public void addSaleObserver(SaleObserver obs) {
        saleObservers.add(obs);
    }
    
    /**
     * Starts a new sale by creating a new {@link Sale} instance.
     */
    public void startSale() {
        this.sale = new Sale();
        for (SaleObserver obs : saleObservers) {
            sale.addObserver(obs);
        }
    }
    
    /**
     * Scans an item by its id.If the item is already in the current sale, its quantity increases. If it's not in the sale but exists in the inventory, it is added.
     * @param itemId The identifier of the item being scanned.
     * @return A {@link ItemDTO} that represents the scanned item, or {@code null} if the identifier is invalid.
     * @throws InvalidIdException
     */
    public ItemDTO scanItem(String itemId) throws InvalidIdException, DatabaseFailureException {
        Item item;
        if (sale.isItemInSale(itemId)) {
            item = sale.getItemFromSale(itemId);
            item.increaseQuantity(1);
        }
        else {
            inventory.isValidItem(itemId);
            ItemDTO itemDTO = inventory.getItem(itemId);
            item = new Item(itemDTO);
            sale.addItemToSale(item);
        }
        sale.increaseTotalPrice(item.getPrice());
        sale.calculateTotalVat(item.getVat(), item.getPrice());
        return item.generateDTO();
    }
    
    /**
     * Gets the total price of the sale including VAT.
     * @return A {@code String} representing the total sale price, formatted to two decimals.
     */
    public String getSaleTotal() {
        return String.format("%.2f", sale.getTotalPrice());
    }
    
    /**
     * Processes the payment made by the customer by updating the register and storing it in {@link Sale}. Calculates the change and stores it in {@link Sale} to be given to customer.
     * @param amountPaid The amount of money the customer paid with.
     */
    public void pay(float amountPaid) {
        sale.setCash(amountPaid);
        register.updateTotal(sale.getCash());
        sale.setChange(sale.getCash() - sale.getTotalPrice());
    }
    
    /**
     * Creates a receipt for the current sale.
     * @return A {@code String} with the receipt text.
     */
    public String getReceipt() {
        return sale.createReceipt();
    }
    
    /**
     * Retrieves a DTO that represents the current sale.
     * @return A {@link SaleDTO} that contains the current sale's data.
     */
    public SaleDTO getCurrentSale() {
        return this.sale.generateDTO();
    }
    
    public void endSale() {
        SaleDTO saleDTO = this.sale.generateDTO();
        for (SaleObserver obs : sale.getObservers()) {
            obs.newSale(saleDTO);
        }
    }
}
