package ru.job4j.ood.srp.emplcomparator;

import java.util.Comparator;
import ru.job4j.ood.srp.model.Employee;

public class EmployeeComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee emp1, Employee emp2) {
        return Double.compare(emp2.getSalary(), emp1.getSalary());
    }
}
