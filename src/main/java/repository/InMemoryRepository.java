package repository;

import model.Flavor;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository implements Repository{
    private final List<Flavor> flavorList;
    private float profit;

    public InMemoryRepository() {
        flavorList = new ArrayList<>();
        profit = 0.0f;
    }

    public InMemoryRepository(List<Flavor> flavorList){
        this.flavorList = flavorList;
        profit = 0.0f;
    }

    public float getProfit() {
        return profit;
    }

    public void updateProfit(float clientProfit) {
        profit += clientProfit;
    }

    @Override
    public List<Flavor> getAllFlavors() {
        return flavorList;
    }

    @Override
    public void addFlavor(Flavor newFlavor) {
        flavorList.add(newFlavor);
    }

    @Override
    public void removeFlavor(String name) {
        Boolean removed = false;

        for (Flavor flavor : flavorList){
            if (flavor.getName().equals(name)){
                flavorList.remove(flavor);
                removed = true;
            }
        }

        if (removed.equals(true)){
            System.out.println("We don't carry that ice cream flavor.");
        }
    }

    @Override
    public void updateFlavor(String name, float price) {
        Flavor auxFlavor = new Flavor();
        auxFlavor.setName(name);
        auxFlavor.setPrice(price);
        int position = -1;

        for (Flavor flavor : flavorList){
            position++;
            if (flavor.getName().equals(name)){
                flavorList.set(position, auxFlavor);
            }
            else {
                System.out.println("We didn't have that flavor anyway.");
            }
        }
    }
}
