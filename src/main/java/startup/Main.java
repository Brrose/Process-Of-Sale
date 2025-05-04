package startup;
import controller.Controller;
import view.View;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        View view = new View(controller);
        view.samplePurchase();
    }
}
