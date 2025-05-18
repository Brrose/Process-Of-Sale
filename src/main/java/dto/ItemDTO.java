package dto;
/**
 * A DTO representing an item. Used to transfer item data without exposing internal model logic.
 * @param id The unique identifier of the item.
 * @param description A description of the item.
 * @param name The name of the item.
 * @param price The price of the item, excluding VAT.
 * @param vat The VAT percentage applied to the item.
 */
public record ItemDTO(String id, String description, String name, float price, float vat) {
   
}
