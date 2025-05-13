package model;

import dto.ItemDTO;

/**
 * The {@code Item} class represents an item in a sale, with an id, name, price, VAT and quantity.
 * It can be created from a {@link ItemDTO} or from raw values.
 */
public class Item {

    private String id;
    private String description;
    private String name;
    private float price;
    private float vat;
    private int quantity;
    
    /**
     * Creates a new {@code Item} with specified details.
     * @param id The unique identifier of the item.
     * @param description The description of the item.
     * @param name The name of the item.
     * @param price The price of the item, excluding VAT.
     * @param vat The VAT percentage applied to the item.
     */
    public Item(String id, String description, String name, float price, float vat) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;
        this.vat = vat;
        this.quantity = 1;
    }
    
    /**
     * Creates a new {@code Item} using data from a {@link ItemDTO}.
     * @param itemDTO The DTO containing information about the item.
     */
    public Item(ItemDTO itemDTO) {
        this.id = itemDTO.id();
        this.description = itemDTO.description();
        this.name = itemDTO.name();
        this.price = itemDTO.price();
        this.vat = itemDTO.vat();
        this.quantity = 1;
    }
    
    /**
     * Gets the item id.
     * @return The id of the item.
     */
    public String getId () {
        return id;
    }
    
    /**
     * Sets the item id.
     * @param id The new id of the item.
     */
    public void setId (String id) {
        this.id = id;
    }
    
    /**
     * Gets the description of the item.
     * @return The item's decsription
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the item's description.
     * @param description The new description of the item.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Gets the item's name.
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the item's name.
     * @param name The new name of the item.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the item's price (excluding VAT).
     * @return The price of the item.
     */
    public float getPrice() {
        return price;
    }
    
    /**
     * Sets the item's price.
     * @param price The new price of the item.
     */
    public void setPrice(float price) {
        this.price = price;
    }
    
    /**
     * Gets the VAT percentage of the item.
     * @return The VAT percentage.
     */
    public float getVat() {
        return vat;
    }
    
    /**
     * Sets the VAT percentage of the item.
     * @param vat The new VAT percentage.
     */
    public void setVat(float vat){
        this.vat = vat;
    }
    
    /**
     * Gets the quantity of this item in the sale
     * @return THe quantity of the item.
     */
    public int getQuantity() {
        return quantity;
    }
    
    /**
     * Sets the quantity of this item in the sale.
     * @param quantity The new quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * Increases the quantity of the item in the sale by given amount.
     * @param quantity The amount to increase the quantity with.
     */
    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }
    
    /**
     * Generates a DTO representing this item.
     * @return A {@link ItemDTO} containing the item's data.
     */
    public ItemDTO generateDTO() {
        return new ItemDTO(id, description, name, price, vat);
    }
}
