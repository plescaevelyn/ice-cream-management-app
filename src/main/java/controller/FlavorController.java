package controller;

import model.Flavor;
import repository.Repository;

import java.util.List;

public class FlavorController {
    private final Repository repository;

    public FlavorController(Repository repository) {
        this.repository = repository;
    }

    public List<Flavor> getAllFlavors() {
        return repository.getAllFlavors();
    }

    public void addFlavor(Flavor newFlavor){
        repository.addFlavor(newFlavor);
    }

    public void updateFlavor(String name, float price){repository.updateFlavor(name, price);}

    public void removeFlavor(String flavor) {repository.removeFlavor(flavor);}

    public float getProfit() {
        return repository.getProfit();
    }

    public void updateProfit(float clientProfit) {
        repository.updateProfit(clientProfit);
    }

    public boolean doesFlavorReallyExist(String flavorname){
        List<Flavor> flavorList = getAllFlavors();

        for (Flavor flavor : flavorList){
            if (flavor.getName().equals(flavorname)) {
                return true;
            }
        }
        return false;
    }

    public float getFlavorPriceByType(String type){
        List<Flavor> flavorList = getAllFlavors();

        for (Flavor flavor : flavorList){
            if (flavor.getName().equals(type)) {
                return flavor.getPrice();
            }
        }
        return 0f;
    }
}