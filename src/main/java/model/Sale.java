package model;

import dto.ItemDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import dto.SaleDTO;
import java.util.List;
import model.SaleObserver;

/**
 * The {@code Sale} class represents a sale in progress, 
 * including items, prices, VAT and payment details.
 */
public class Sale {
    private float totalPrice;
    private float totalVAT;
    private ArrayList<Item> items;
    private float cash;
    private float change;
    private List<SaleObserver> observers = new ArrayList<>();
    
    /**
     * Creates a new {@code Sale} instance with an 
     * empty list of items and zeroed values.
     */
    public Sale() {
        this.totalPrice = 0;
        this.totalVAT = 0;
        this.items = new ArrayList<>();
        this.cash = 0;
        this.change = 0;
    }

    /**
     * Gets the current total price of the sale.
     * @return The total price including all items.
     */
    public float getTotalPrice() {
        return totalPrice;
    }
    
    /**
     * Sets the total price of the sale.
     * @param totalPrice The new total price.
     */
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Increases the total price by a specified amount.
     * @param price The amount to add to the total price.
     */
    public void increaseTotalPrice(float price) {
        this.totalPrice += price;
    }
    
    /**
     * Gets the total VAT accumulated in the sale.
     * @return The total VAT amount.
     */
    public float getTotalVAT() {
        return totalVAT;
    }
    
    /**
     * Sets the total VAT amount.
     * @param totalVAT The new VAT value.
     */
    public void setTotalVAT(float totalVAT) {
        this.totalVAT = totalVAT;
    }
    
    /**
     * Calculates and adds the VAT for a specific item to the total VAT.
     * @param vat The VAT percentage.
     * @param price The price of the item.
     */
    public void calculateTotalVat(float vat, float price) {
        this.totalVAT += price * (vat / 100);
    }
    
    /**
     * Gets the list of items in the sale.
     * @return The list of {@link Item} objects.
     */
    public ArrayList<Item> getItems() {
        return items;
    }
    
    /**
     * Sets the list of items in the sale.
     * @param items The new list of items.
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    
    /**
     * Gets the amount of cash received from the customer.
     * @return The amount of cash paid.
     */
    public float getCash() {
        return cash;
    }
    
    /**
     * Sets the amount of cash received from the customer.
     * @param cash The cash amount.
     */
    public void setCash(float cash) {
        this.cash = cash;
    }
    
    /**
     * Gets the change to return to the customer.
     * @return The change amount.
     */
    public float getChange() {
        return change;
    }
    
    /**
     * Sets the change amount to return to the customer.
     * @param change The new change amount.
     */
    public void setChange(float change) {
        this.change = change;
    }

    /**
     * Adds an item to the sale.
     * @param item The {@link Item} to add.
     */
    public void addItemToSale(Item item) {
        items.add(item);
    }

    /**
     * Checks if an item is already in the sale with its id.
     * @param id The id of the item being checked.
     * @return {@code true} if the item is already in the sale, {@code false} if not.
     */
    public boolean isItemInSale(String id) {
        for (Item i : items) {
            if (i.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves an item from the sale by its id.
     * @param id The id of the item to retrieve.
     * @return The {@link Item} if found, or {@code null} if not found.
     */
     public Item getItemFromSale(String id) {
        for (Item i : items) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }
     
     /**
      * Creates a text representation of the receipt, 
      * including time, items, prices, VAT, payment and change.
      * @return A formatted receipt string.
      */
    public String createReceipt() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = now.format(formatter);
        String receipt = "------------------ Begin receipt -------------------\n";
        receipt += "Time of Sale: " + formattedDate + "\n";
        for (Item i : items) {
            receipt += i.getName() + " " + i.getQuantity() + " x " + String.format("%.2f", i.getPrice()) + " " + String.format("%.2f", i.getQuantity()*i.getPrice()) + " SEK" + "\n";
        }
        receipt += "\nTotal: " + String.format("%.2f", getTotalPrice()) + " SEK" + 
        "\nVAT: " + String.format("%.2f", getTotalVAT()) + 
        "\n\nCash: " + String.format("%.2f", getCash()) + " SEK" + 
        "\nChange: " + String.format("%.2f", getChange()) + " SEK\n" + 
        "------------------ End receipt ---------------------";
        return receipt;
    }

    /**
     * Generates a {@link SaleDTO} object containing the current sale.
     * @return A DTO with the sale's details.
     */
    public SaleDTO generateDTO() {
        ArrayList<ItemDTO> itemsDTO = new ArrayList();
        for (Item item : this.items) {
            itemsDTO.add(item.generateDTO());
        }
        return new SaleDTO(this.totalPrice, this.totalVAT, itemsDTO, this.cash, this.change);
    }
    
    /**
     * Adds a sale observer that will be notified when the sale ends.
     * @param obs The observer being added.
     */
    public void addObserver (SaleObserver obs) {
        observers.add(obs);
    }
    
    /**
     * Returns the list of observers attached to the sale.
     * @return A list of {@link SaleObserver} instances.
     */
    public List<SaleObserver> getObservers() {
        return observers;
    }
}
