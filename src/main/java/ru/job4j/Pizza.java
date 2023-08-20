package ru.job4j;

public class Pizza {

    public void cook() {
        System.out.println("Your pizza is ready!");
    }

    public String checkCustomerPreference(String vegetarian) {
       return "vegetarian".equals(vegetarian) ? "no meat" : "meat eater";
    }
}
