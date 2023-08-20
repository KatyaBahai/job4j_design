package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.emplcomparator.EmployeeComparator;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.*;

class HRReportTest {

    @Test
    public void whenGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker0 = new Employee("Ivan", now, now, 300);
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Ivan", now, now, 200);
        store.add(worker0);
        store.add(worker1);
        store.add(worker2);
        Comparator<Employee> comparator = new EmployeeComparator();
        Report engine = new HRReport(store, comparator);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker0.getName()).append(" ")
                .append(worker0.getSalary())
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary())
                .append(System.lineSeparator());
        System.out.println(expect);
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}