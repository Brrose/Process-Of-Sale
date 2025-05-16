package view;

import dto.SaleDTO;
import model.SaleObserver;

public abstract class RevenueDisplay implements SaleObserver {
    
    float totalRev = 0;
    
    @Override
    public void newSale (SaleDTO saleDTO) {
        totalRev += saleDTO.getTotalPrice();
        printTotalRevenue(totalRev);
    }
    
    protected abstract void printTotalRevenue(float totalRev);
    
}
