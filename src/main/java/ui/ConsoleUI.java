package ui;

import controller.FlavorController;
import model.Flavor;
import repository.InFileRepository;
import repository.InMemoryRepository;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleUI {
    private FlavorController flavorController;

    public void run() throws IOException {
        System.out.println("Welcome to the...\n" +
                "\n╭━━┳━━━┳━━━╮╭━━━┳━━━┳━━━┳━━━┳━╮╭━╮╭━━━━┳━━━┳╮╱╭┳━━━┳╮╭━╮" +
                "\n╰┫┣┫╭━╮┃╭━━╯┃╭━╮┃╭━╮┃╭━━┫╭━╮┃┃╰╯┃┃┃╭╮╭╮┃╭━╮┃┃╱┃┃╭━╮┃┃┃╭" +
                "\n╱┃┃┃┃╱╰┫╰━━╮┃┃╱╰┫╰━╯┃╰━━┫┃╱┃┃╭╮╭╮┃╰╯┃┃╰┫╰━╯┃┃╱┃┃┃╱╰┫╰╯╯" +
                "\n╱┃┃┃┃╱╭┫╭━━╯┃┃╱╭┫╭╮╭┫╭━━┫╰━╯┃┃┃┃┃┃╱╱┃┃╱┃╭╮╭┫┃╱┃┃┃╱╭┫╭╮┃" +
                "\n╭┫┣┫╰━╯┃╰━━╮┃╰━╯┃┃┃╰┫╰━━┫╭━╮┃┃┃┃┃┃╱╱┃┃╱┃┃┃╰┫╰━╯┃╰━╯┃┃┃╰╮" +
                "\n╰━━┻━━━┻━━━╯╰━━━┻╯╰━┻━━━┻╯╱╰┻╯╰╯╰╯╱╱╰╯╱╰╯╰━┻━━━┻━━━┻╯╰━╯");

        System.out.println("Do you want to work from the console (1) or from our refined file database (2) ?[1/2]");
        Scanner scanner = new Scanner(System.in);

        int choice = scanner.nextInt();

        switch(choice) {
            case 1:
                InMemoryRepository inMemoryRepository = new InMemoryRepository();
                flavorController = new FlavorController(inMemoryRepository);
                break;
            case 2:
                InFileRepository inFileRepository = new InFileRepository("flavor_db.txt");
                flavorController = new FlavorController(inFileRepository);
                break;
            default:
                System.out.println("not ok");
                exitProgram();
                break;
        }

        OwnerUI ownerUI = new OwnerUI(flavorController);
        UserUI userUI = new UserUI(flavorController);
        while (true) {

            //printing the menu
            System.out.println("1. Enter the owner console");
            System.out.println("2. Enter the client console");
            System.out.println("3. Exit program");

            int option = scanner.nextInt(); // read user input

            switch (option) {
                case 1:
                    ownerUI.openOwnerUI(); //opening the owner console
                    break;
                case 2:
                    userUI.openClientUI(); //opening the client console
                    break;
                case 3:
                    exitProgram(); //exiting the program
                    break;
            }
        }
    }

    //exiting the program
    private void exitProgram() {
        System.exit(0);
    }
}