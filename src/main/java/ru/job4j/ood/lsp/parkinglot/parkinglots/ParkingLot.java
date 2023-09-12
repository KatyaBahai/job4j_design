package ru.job4j.ood.lsp.parkinglot.parkinglots;

import ru.job4j.ood.lsp.parkinglot.vehicles.Vehicle;

import java.util.List;

public interface ParkingLot {

    boolean park(Vehicle vehicle);

    List<Vehicle> getVehicleList();

    void unPark(Vehicle vehicle);
}
