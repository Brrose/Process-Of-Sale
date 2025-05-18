package model;

import dto.SaleDTO;

/**
 * The {@code SaleObserver} interface is implemented by classes that need to be notified when a sale has been completed.
 */
public interface SaleObserver {
    
    /**
     * Called when a sale has been completed.
     * @param totalRev A {@link SaleDTO} with details of the completed sale.
     */
    void newSale (SaleDTO totalRev);
}
