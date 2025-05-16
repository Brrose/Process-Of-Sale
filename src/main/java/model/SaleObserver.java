package model;

import dto.SaleDTO;

public interface SaleObserver {
    
    void newSale (SaleDTO totalRev);
}
