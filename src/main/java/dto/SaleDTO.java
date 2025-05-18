package dto;

import java.util.ArrayList;

/**
 * A DTO representing a completed sale. It's used to transfer sale information without exposing internal model logic.
 * @param totalPrice The total price of the sale, including VAT.
 * @param totalVAT The total VAT amount for the sale.
 * @param items A list of items included in the sale.
 * @param cash The amount of cash paid by the customer.
 * @param change The amount of change to be returned to the customer.
 */
public record SaleDTO(float totalPrice, float totalVAT, ArrayList<ItemDTO> items, float cash, float change) {
   
    /**
     * Gets the total price of the sale.
     * @return The total price.
     */
    public float getTotalPrice () {
        return totalPrice;
    }
}
