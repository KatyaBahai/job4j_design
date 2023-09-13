package ru.job4j.ood.lsp.parkinglot.parkinglots;

import ru.job4j.ood.lsp.parkinglot.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MegaParkingLot implements ParkingLot {
    private Map<Vehicle, Integer> vehicleMap = new HashMap<>();
    private int carSpaceCount;
    private int truckSpaceCount;

    public MegaParkingLot(int carSpaceCount, int truckSpaceCount) {
        this.carSpaceCount = carSpaceCount;
        this.truckSpaceCount = truckSpaceCount;
    }

    @Override
    public boolean park(Vehicle vehicle) {
        boolean parked = false;
        int spaceRequired = vehicle.getSpaceRequired();
        if (spaceRequired > 1 && truckSpaceCount > 0) {
            vehicleMap.put(vehicle, 1);
            truckSpaceCount--;
            parked = true;
        }
        if (carSpaceCount > 0 && carSpaceCount >= spaceRequired) {
            vehicleMap.put(vehicle, spaceRequired);
            carSpaceCount -= spaceRequired;
            parked = true;
        }
        return parked;
    }

    @Override
    public List<Vehicle> getVehicleList() {
        return new ArrayList<>(vehicleMap.keySet());
    }

    @Override
    public void unPark(Vehicle vehicle) {
        int spaceRequired = vehicle.getSpaceRequired();
        if (spaceRequired > 1 && vehicleMap.get(vehicle) == 1) {
            truckSpaceCount++;
        }
        if ((spaceRequired == 1) || (spaceRequired > 1 && vehicleMap.get(vehicle) > 1)) {
            carSpaceCount += spaceRequired;
        }
        vehicleMap.remove(vehicle);
    }

    public int getCarSpaceCount() {
        return carSpaceCount;
    }

    public int getTruckSpaceCount() {
        return truckSpaceCount;
    }
}
