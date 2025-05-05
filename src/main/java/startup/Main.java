package startup;

import controller.Controller;
import view.View;

/**
 * The {@code Main} class serves as the start point for the program.
 * It initializes the controller and view and runs a sample purchase.
 */
public class Main {
    
    /**
     * Starts the program by creating the controller and view, then running a sample purchase.
     * @param args 
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        View view = new View(controller);
        view.samplePurchase();
    }
}
