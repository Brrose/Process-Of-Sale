package startup;

import controller.Controller;
import java.io.IOException;
import view.View;
import util.LogHandler;

/**
 * The {@code Main} class serves as the start point for the program.
 * It initializes the controller and view and runs a sample purchase.
 */
public class Main {
    
    /**
     * Starts the program by creating the controller and view, then running two sample purchases.
     * @param args
     * @throws IOException If an error occurs during logging.
     */
    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        LogHandler logHandler = new LogHandler();
        View view = new View(controller, logHandler);
        view.samplePurchase();
        view.samplePurchase();
    }
}
