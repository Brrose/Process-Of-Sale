package view;

import dto.SaleDTO;
import model.SaleObserver;

/**
 * The {@code RevenueDisplay} class is an observer of sales that keeps track of the total revenue.
 * It updates the total revenue when a sale is completed.
 */
public abstract class RevenueDisplay implements SaleObserver {
    
    float totalRev = 0;
    
    /**
     * Updates the total revenue and calls the method {@code printTotalRevenue} to display it.
     * Is called when a sale is completed.
     * @param saleDTO A {@link SaleDTO} containing information about the completed sale.
     */
    @Override
    public void newSale (SaleDTO saleDTO) {
        totalRev += saleDTO.getTotalPrice();
        printTotalRevenue(totalRev);
    }
    
    /**
     * Displays the total revenue.
     * @param totalRev The current total revenue after the latest completed sale.
     */
    protected abstract void printTotalRevenue(float totalRev);
    
}
