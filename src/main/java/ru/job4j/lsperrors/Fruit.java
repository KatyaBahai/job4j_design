package ru.job4j.lsperrors;

class Fruit {
    public void eatFruit() {
        System.out.println("Fruit is eaten.");
    }
    public void peelFruit() {
        System.out.println("Fruit is peeled.");
    }
}

class Pear extends Fruit {
    @Override
    public void eatFruit() {
        System.out.println("Fruit is eaten.");
    }
    @Override
    public void peelFruit() {
        throw new IllegalStateException("Pears don't need peeling!");
    }
}
