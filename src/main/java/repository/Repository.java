package repository;

//  show all available ice cream flavors (with name and price)
//  add an ice cream flavor (with name and price)
//  if the name already exists show a message
//  update an ice cream flavor (find by name, update price)
//  if the name doesn't exist show a message
//  remove an ice cream flavor (find by name)
//  if the name doesn't exist show a message
//  show profit
//  exit Owner ui

import model.Flavor;

import java.util.List;

// https://docs.oracle.com/javase/tutorial/java/IandI/interfaceDef.html

public interface Repository {
    float getProfit();
    void updateProfit(float clientProfit);
    List<Flavor> getAllFlavors();
    void addFlavor(Flavor newFlavor);
    void removeFlavor(String name);
    void updateFlavor(String name, float price);
}
