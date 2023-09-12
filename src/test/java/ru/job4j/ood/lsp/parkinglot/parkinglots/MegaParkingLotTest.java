package ru.job4j.ood.lsp.parkinglot.parkinglots;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parkinglot.vehicles.Car;
import ru.job4j.ood.lsp.parkinglot.vehicles.Truck;
import ru.job4j.ood.lsp.parkinglot.vehicles.Vehicle;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MegaParkingLotTest {

    @Test
    void ifThereIsSpaceForCarThenCarIsParked() {
        ParkingLot megaPark = new MegaParkingLot(3, 1);
        Vehicle ferrariCar = new Car("ferrari");
        megaPark.park(ferrariCar);
        assertThat(megaPark.getVehicleList()).contains(ferrariCar);

    }

    @Test
    void ifThereIsNotSpaceForCarThenCarIsNotParked() {
        ParkingLot megaPark = new MegaParkingLot(0, 10);
        Vehicle ferrariCar = new Car("ferrari");
        assertFalse(megaPark.park(ferrariCar));
    }

    @Test
    void ifThereIsNotSpaceForTruckThenTruckIsNotParked() {
        ParkingLot megaPark = new MegaParkingLot(2, 0);
        Vehicle ferrariCar = new Car("ferrari");
        megaPark.park(ferrariCar);
        Vehicle fordTruck = new Truck("ford", 2);
        assertFalse(megaPark.park(fordTruck));
    }

    @Test
    void ifThereIsSpaceForTruckThenTruckIsParked() {
        ParkingLot megaPark = new MegaParkingLot(1, 1);
        Vehicle fordTruck = new Truck("ford", 2);
        megaPark.park(fordTruck);
        assertThat(megaPark.getVehicleList()).contains(fordTruck);
    }

    @Test
    void ifThereIsNotSpaceForTruckButThereIsEnoughCarSpacesThenTruckIsParked() {
        ParkingLot megaPark = new MegaParkingLot(3, 1);
        Vehicle ferrariCar = new Car("ferrari");
        megaPark.park(ferrariCar);
        Vehicle fordTruck = new Truck("ford", 2);
        megaPark.park(fordTruck);
        assertThat(megaPark.getVehicleList()).contains(ferrariCar, fordTruck);
    }

    @Test
    void ifCarLeavesParkingLotThenUnparked() {
        ParkingLot megaPark = new MegaParkingLot(3, 1);
        Vehicle ferrariCar = new Car("ferrari");
        megaPark.park(ferrariCar);
        Vehicle fordTruck = new Truck("ford", 2);
        megaPark.park(fordTruck);
        assertThat(megaPark.getVehicleList()).contains(ferrariCar, fordTruck);
        megaPark.unPark(ferrariCar);
        assertThat(megaPark.getVehicleList()).containsExactly(fordTruck);
    }

}