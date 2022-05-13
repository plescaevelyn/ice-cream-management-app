package ui;

import controller.FlavorController;
import model.Flavor;

import java.util.List;
import java.util.Scanner;

public class UserUI {
    FlavorController flavorController;

    public UserUI(FlavorController flavorController) {
        this.flavorController = flavorController;
    }

    //opening the client console
    public void openClientUI() {
        //print menu
        System.out.println("" +
                "1. Show all available ice cream flavors (with name and price)\n" +
                "2. Order an ice cream\n" +
                "0. Exit UI\n");

        //getting the user input
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        //checking if the option is valid
        while (!option.equals("0")) {
            switch (option) {
                case "1":
                    showIceCreamFlavors(); //displaying all ice cream flavors
                    break;
                case "2":
                    orderIceCream(); //clients' UI for ordering ice cream
                    break;
                default:
                    break;
            }

            System.out.println("\nWhat do you want to do next?");
            option = scanner.nextLine();
        }
    }

    private void showIceCreamFlavors() {
        List<Flavor> allFlavors = flavorController.getAllFlavors();

        if (!allFlavors.isEmpty()) {
            for (Flavor flavor : allFlavors) {
                System.out.println("Flavor: " + flavor.getName() + "\tPrice: " + flavor.getPrice());
            }
        } else {
            System.out.println("We do not have any ice creams in our menu yet!");
        }
    }

    private void orderImprovedIceCream(){
        UserIceCreamContainer userIceCreamContainer = new UserIceCreamContainer();
        Scanner scanner = new Scanner(System.in);
        int mistakesCount = 0;
        int globeNumber = 0;


        while (mistakesCount < 3 && globeNumber < 3) {
            System.out.println("Do you want some ice cream? What flavor?");
            String type = scanner.nextLine();

            if (flavorController.doesFlavorReallyExist(type)) {
                System.out.println(type + "costs you " + flavorController.getFlavorPriceByType(type) + "and a health potion. Are you willing to give this all up? [Y/N]");
                String option = scanner.next();

                if (option.equals("Y")){
                    userIceCreamContainer.setTotalPrice(userIceCreamContainer.getTotalPrice() + flavorController.getFlavorPriceByType(type));
                    globeNumber++;
                    System.out.println("Warned you but good choice!");
                } else if (option.equals("N")){
                    System.out.println("Do you want to exit? [Y/N]");
                    option = scanner.next();

                    if (option.equals("Y")){
                        break; //si dupa ce zice da il las sa iasa daca doreste
                    }
                }
            } else {
                System.out.println("Flavor does not exist.");
                mistakesCount++;
            }
        }

        //todo finish this
    }



    private void orderIceCream() {
        UserIceCreamContainer user = new UserIceCreamContainer();
        List<Flavor> allFlavors = flavorController.getAllFlavors();
        char globeNumber = 0;
        float currentTotalPrice = user.getTotalPrice();
        Boolean anotherGlobe = true;
        Boolean iceCreamFound = false;

        while ((globeNumber < 3) && (anotherGlobe.equals(true))) {
            System.out.println("What kind of ice cream would you like?");
            Scanner scanner = new Scanner(System.in);

            if (scanner.hasNextLine()) {
                String response = scanner.nextLine();
                for (Flavor flavor : allFlavors) {
                    String currentFlavorName = flavor.getName();
                    if (currentFlavorName.equals(response)) {
                        iceCreamFound = true;
                        System.out.println("The price of this ice cream per globe is " + flavor.getPrice() +
                                ". Do you want to proceed with buying this ice cream?[Y/N]");
                        response = scanner.nextLine();
                        if (response.equals("Y")) {
                            ++globeNumber;
                            currentTotalPrice += flavor.getPrice();
                            System.out.println("Great choice!");
                        } else if (response.equals("N")) {
                            System.out.println("Too bad.");
                        } else {
                            System.out.println("Wrong input!!!");
                            response = scanner.nextLine();
                        }
                    }
                }

                if (globeNumber > 0) {

                    System.out.println("Do you want to add another globe? [Y/N]");
                    response = scanner.nextLine();

                    int retries = 0;
                    while (retries < 3) {
                        if (response.equals("Y")) {
                            System.out.print("Alrighty.\t");
                            break;
                        } else if (response.equals("N")) {
                            anotherGlobe = false;
                            break;
                        } else {
                            System.out.println("Incoherent response buddy. Tell me again.");
                            retries++;
                            response = scanner.nextLine();
                        }
                    }
                    if (retries >= 3) {
                        System.out.println("Seems like you can't decide, come back later.");
                    }
                }
            }

            if (iceCreamFound.equals(false)) {
                System.out.println("Sorry, we do not carry that type of ice cream.");
                break;
            }
        }

        user.setTotalPrice(currentTotalPrice);
        flavorController.updateProfit(currentTotalPrice);
    }
}