package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import view.RevenueDisplay;

/**
 * The {@code TotalRevenueFileOutput} class logs the total revenue to a file.
 * It extends the {@link RevenueDisplay} class.
 */
public class TotalRevenueFileOutput extends RevenueDisplay {
    private static final String LOG_FILE_NAME = "totalRev.txt";
    private PrintWriter logFile;
    
    /**
     * Creates a new {@code TotalRevenueFileOutput} instance and opens the file to add the total revenue logs.
     * @throws IOException If the file can't be created or opened.
     */
    public TotalRevenueFileOutput () throws IOException{
        logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME, true), true);
    }
    
    /**
     * Writes the total revenue to the file.
     * @param totalRev The total revenue amount to be logged.
     */
    @Override
    protected void printTotalRevenue(float totalRev) {
        logFile.println("Total Revenue is: " + totalRev);
    }
    
}
