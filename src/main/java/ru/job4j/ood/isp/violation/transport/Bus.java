package ru.job4j.ood.isp.violation.transport;

public class Bus implements Vehicle {
    @Override
    public void startEngine() {

    }

    @Override
    public void fly() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drive() {

    }

    @Override
    public void stopEngine() {

    }
}
