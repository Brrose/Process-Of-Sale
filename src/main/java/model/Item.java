package model;

import dto.ItemDTO;

public class Item {

    private String id;
    private String description;
    private String name;
    private float price;
    private float vat;
    private int quantity;

    public Item(String id, String description, String name, float price, float vat) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;
        this.vat = vat;
        this.quantity = 1;
    }
    
    public Item(ItemDTO itemDTO) {
        this.id = itemDTO.id();
        this.description = itemDTO.description();
        this.name = itemDTO.name();
        this.price = itemDTO.price();
        this.vat = itemDTO.vat();
        this.quantity = 1;
    }
    
    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getVat() {
        return vat;
    }

    public void setVat(float vat){
        this.vat = vat;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }
    
    public ItemDTO generateDTO() {
        return new ItemDTO(id, description, name, price, vat);
    }
}
