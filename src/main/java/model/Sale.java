package model;

import dto.ItemDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import dto.SaleDTO;

public class Sale {
    private float totalPrice;
    private float totalVAT;
    private ArrayList<Item> items;
    private float cash;
    private float change;

    public Sale() {
        this.totalPrice = 0;
        this.totalVAT = 0;
        this.items = new ArrayList<>();
        this.cash = 0;
        this.change = 0;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void increaseTotalPrice(float price) {
        this.totalPrice += price;
    }
    public float getTotalVAT() {
        return totalVAT;
    }
    public void setTotalVAT(float totalVAT) {
        this.totalVAT = totalVAT;
    }

    public void calculateTotalVat(float vat, float price) {
        this.totalVAT += price * (vat / 100);
    }
    public ArrayList<Item> getItems() {
        return items;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    public float getCash() {
        return cash;
    }
    public void setCash(float cash) {
        this.cash = cash;
    }
    public float getChange() {
        return change;
    }
    public void setChange(float change) {
        this.change = change;
    }

    public void addItemToSale(Item item) {
        items.add(item);
    }

    public boolean isItemInSale(String id) {
        for (Item i : items) {
            if (i.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

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

    public Item getItemFromSale(String id) {
        for (Item i : items) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }

    public SaleDTO generateDTO() {
        ArrayList<ItemDTO> itemsDTO = new ArrayList();
        for (Item item : this.items) {
            itemsDTO.add(item.generateDTO());
        }
        return new SaleDTO(this.totalPrice, this.totalVAT, itemsDTO, this.cash, this.change);
    }
}
