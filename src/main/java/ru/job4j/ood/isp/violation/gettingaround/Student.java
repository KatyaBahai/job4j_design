package ru.job4j.ood.isp.violation.gettingaround;

public class Student implements ReachDestination {


    @Override
    public void goOnFoot() {

    }

    @Override
    public void drive() {
        throw new UnsupportedOperationException("Student doesn't have a car!");
    }

    @Override
    public void cycle() {

    }
}
