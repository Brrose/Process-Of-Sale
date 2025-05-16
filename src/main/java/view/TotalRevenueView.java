package view;

public class TotalRevenueView extends RevenueDisplay{
    
    @Override
    protected void printTotalRevenue (float totalRev) {
        System.out.println("Total revenue is: " + totalRev);
    }
}
