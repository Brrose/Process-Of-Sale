package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import view.RevenueDisplay;

public class TotalRevenueFileOutput extends RevenueDisplay {
    private static final String LOG_FILE_NAME = "totalRev.txt";
    private PrintWriter logFile;
    
    public TotalRevenueFileOutput () throws IOException{
        logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME, true), true);
    }
    @Override
    protected void printTotalRevenue(float totalRev) {
        logFile.println("Total Revenue is: " + totalRev);
    }
    
}
