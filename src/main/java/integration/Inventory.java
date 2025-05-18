package integration;

import java.util.HashMap;
import dto.ItemDTO;

/**
 * The {@code Inventory} class acts as an external inventory system.
 * It provides methods to check if an item is valid and retrieve item data.
 */
public class Inventory {
    
    private HashMap<String, ItemDTO> inventory = new HashMap<>();
    
    /**
     * Creates a new {@code Inventory} instance and initializes it with a sample of items.
     */
    public Inventory() {
        createItems();
    }
    
    /**
     * Checks if an item exists in the inventory.
     * @param id The identifier of the item being checked.
     * @return {@code true} if the item exists, {@code false} if not.
     * @throws integration.InvalidIdException If the id is not found in the inventory.
     * @throws integration.DatabaseFailureException If the database is not running.
     */
    public boolean isValidItem(String id) throws InvalidIdException, DatabaseFailureException {
       if (id.equals("fel123")) {
           throw new DatabaseFailureException("Database could not be called due to not running.");
       }
       if (inventory.get(id) == null) {
           throw new InvalidIdException(id);
       }
       return inventory.get(id) != null;
    }
    
    /**
     * Retrieves an item from the inventory with its identifier.
     * @param id The identifier of the item being retrieved.
     * @return The {@link ItemDTO} corresponding to the identifier, or {@code null} if not found.
     */
    public ItemDTO getItem(String id) {
        return inventory.get(id);
    }
    
    /**
     * Fills the inventory with a set of items.
     * The method is called when the inventory is initialized.
     */
    private void createItems() {
        inventory.put("abc123", new ItemDTO("abc123", "BigWheel Oatmeal 500g, whole grain oats, high fiber, gluten free", "BigWheel Oatmeal", 29.90f, 6f));
        inventory.put("def456", new ItemDTO("def456", "YouGoGo Blueberry 240g, low sugar youghurt, blueberry flavour", "YouGoGo Blueberry", 14.90f, 6f));
    }
}
