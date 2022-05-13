package model;

public class Flavor {
    private String name;
    private float price;

    public Flavor() {
    }

    public Flavor(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        Flavor flavorObj = (Flavor) o; //se numeste cast

        if (flavorObj.name.equals(this.name)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString(){
        return name + " " + price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
