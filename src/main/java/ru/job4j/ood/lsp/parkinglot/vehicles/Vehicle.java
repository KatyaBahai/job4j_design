package ru.job4j.ood.lsp.parkinglot.vehicles;

public abstract class Vehicle {
    String name;
    int spaceRequired;

    public Vehicle(String name, int spaceRequired) {
        this.name = name;
        this.spaceRequired = spaceRequired;
    }


    @Override
    public String toString() {
        return "Vehicle{"
                + "name='" + name + '\''
                + ", spaceRequired=" + spaceRequired
                + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpaceRequired() {
        return spaceRequired;
    }

    public void setSpaceRequired(int spaceRequired) {
        this.spaceRequired = spaceRequired;
    }
}
