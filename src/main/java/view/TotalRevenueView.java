package view;

/**
 * The {@code TotalRevenueView} class is an implementation of {@link RevenueDisplay}.
 * Displays the total revenue by printing it to {@code System.out}.
 */
public class TotalRevenueView extends RevenueDisplay{
    
    /**
     * Prints the total revenue to the console.
     * @param totalRev The current total revenue to display.
     */
    @Override
    protected void printTotalRevenue (float totalRev) {
        System.out.println("Total revenue is: " + totalRev);
    }
}
