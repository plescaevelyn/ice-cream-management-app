import controller.FlavorController;
import model.Flavor;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import repository.InMemoryRepository;
import ui.ConsoleUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
//    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws IOException {
        List<Flavor> flavorList = new ArrayList<>();

        InMemoryRepository inMemoryRepository = new InMemoryRepository(flavorList);
        FlavorController flavorController = new FlavorController(inMemoryRepository);
        ConsoleUI consoleUI = new ConsoleUI();

        consoleUI.run();
    }
}
