package ui;

import controller.FlavorController;
import model.Flavor;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class OwnerUI {
    private FlavorController flavorController;

    public OwnerUI(FlavorController flavorController) {
        this.flavorController = flavorController;
    }

    public void openOwnerUI() throws IOException {
        System.out.println("1. Show all available ice cream flavors (with name and price)\n" +
                "2. Add an ice cream flavor (with name and price)\n" +
                "3. Update an ice cream flavor (find by name, update price)\n" +
                "4. Remove an ice cream flavor (find by name)\n" +
                "5. Show profit\n" +
                "0. Exit the console interface");
        Scanner scanner = new Scanner(System.in);

        String option = scanner.nextLine();

        while (!option.equals("0")) {
            switch (option) {
                case "1":
                    showIceCreamFlavors();
                    break;
                case "2":
                    addIceCream();
                    break;
                case "3":
                    updateIceCream();
                    break;
                case "4":
                    removeIceCream();
                    break;
                case "5":
                    showProfit();
                    break;
            }
            System.out.println("What do you want to do next?");
            option = scanner.nextLine();
        }
    }

    private void showIceCreamFlavors() {
        List<Flavor> allFlavors = flavorController.getAllFlavors();

        if (!allFlavors.isEmpty()) {
            for (Flavor flavor : allFlavors) {
                System.out.println("Flavor: " + flavor.getName() + "\tPrice: " + flavor.getPrice());
            }
        }
        else {
            System.out.println("We do not have any ice creams in our menu yet!");
        }
    }

    private void addIceCream(){
        Scanner scanner = new Scanner(System.in);

        Flavor newIceCream = new Flavor();

        System.out.println("What is the flavor of the ice cream that you want to add?");
        String iceCreamName = scanner.nextLine();
        newIceCream.setName(iceCreamName);

        System.out.println("What is the price of the new ice cream flavor?");
        float iceCreamPrice = scanner.nextFloat();
        newIceCream.setPrice(iceCreamPrice);

        flavorController.addFlavor(newIceCream);
    }

    private void updateIceCream() {
        List<Flavor> flavorList = flavorController.getAllFlavors();
        Scanner scanner = new Scanner(System.in);

        if (flavorList.isEmpty()) {
            System.out.println("We do not carry any ice creams at the moment, therefore you have nothing to update.");
        }
        else {
            System.out.println("What is the name of the ice cream whose price you would like to update?");
            String toUpdate = scanner.nextLine();

            System.out.println("What will be the new price of this ice cream?");
            float newPrice = scanner.nextFloat();

            flavorController.updateFlavor(toUpdate, newPrice);
        }
    }

    private void showProfit(){
        System.out.println("Your total profit is\t" + flavorController.getProfit());
    }

    private void removeIceCream() {
        List<Flavor> flavorList = flavorController.getAllFlavors();
        Scanner scanner = new Scanner(System.in);

        if (flavorList.isEmpty()) {
            System.out.println("Uh-oh, empty menu!");
        }
        else {
            System.out.println("What kind of ice cream would you like to delete from our varied menu?");
            String toDelete = scanner.nextLine();

            flavorController.removeFlavor(toDelete);
        }
    }
}
