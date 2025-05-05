package model;

/**
 * The {@code Register} class represents a cash register that stores the money earned from sales.
 */
public class Register {

    private float total;

    /**
     * Creates a new {@code Register} instance with an initial total of 0.
     */
    public Register() {
        this.total = 0;
    }
    
    /**
     * Gets the current total amount of money stored in the register.
     * @return The total money in the register.
     */
    public float getTotal() {
        return total;
    }
    
    /**
     * Sets the total amount of money in the register.
     * @param total The new total to set.
     */
    public void setTotal(float total) {
        this.total = total;
    }
    
    /**
     * Updates the total in the register by adding the specified amount.
     * @param amount The amount of money to add to the total.
     */
    public void updateTotal(float amount) {
        total += amount;
    }
}
