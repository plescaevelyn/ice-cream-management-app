package repository;

import model.Flavor;
import ui.UserIceCreamContainer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TODO de separat cu read si store functiile - read = getAll, store restul

public class InFileRepository implements Repository{
    private List<Flavor> flavorList;
    private float profit;
    private String filename;

    public InFileRepository(String filename){
        this.filename = filename;
        this.flavorList = read(); // pot si sa scriu doar read()
        this.profit = getProfit();
    }

    private List<Flavor> read(){
        this.flavorList = new ArrayList<>();

        File file = new File(this.filename);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] newLine = line.split(" ");
                String flavorName = newLine[0];
                float flavorPrice = Float.parseFloat(newLine[1]);
                Flavor newFlavor = new Flavor(flavorName,flavorPrice);

                this.flavorList.add(newFlavor);

                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.flavorList;
    }

    private void store(){
        try{
            FileWriter fw = new FileWriter(this.filename);
            fw.write("");

            for (Flavor flavor : this.flavorList){
                fw.write(flavor.toString() + "\n");
            }
        } catch (IOException e){
            System.out.println(e);
        }
    } //vede toate modificarile noi ale listei

    @Override
    public float getProfit() {
        return 0;
    }

    @Override
    public void updateProfit(float clientProfit) {}

    @Override
    public List<Flavor> getAllFlavors() {
        return this.flavorList;
    }

    @Override
    public void addFlavor(Flavor flavor) {
        this.flavorList.add(flavor);
        store();
    }

    @Override
    public void removeFlavor(String flavorName) {
        //modific lista
        store();
    }

    @Override
    public void updateFlavor(String name, float price) {
        //scriu logica (modificarile pe lista)
        store();
    }
}
