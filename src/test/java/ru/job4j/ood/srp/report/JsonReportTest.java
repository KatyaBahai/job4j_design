package ru.job4j.ood.srp.report;

import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.CalendarJsonParser;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;
import java.util.Calendar;
import static org.assertj.core.api.Assertions.*;

class JsonReportTest {

    @Test
    public void whenGenerateThenGetJson() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        CalendarJsonParser jsonParser = new CalendarJsonParser();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Ilya", now, now, 200);
        Employee worker3 = new Employee("Anton", now, now, 300);
        store.add(worker);
        store.add(worker2);
        store.add(worker3);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        String jsonNow = parser.parse(now);
        GsonBuilder builder = new GsonBuilder();
        JsonReport report = new JsonReport(store, jsonParser, builder);
        System.out.println(report.generate(em -> true));
        String expect = String.format("""
                [
                  {
                    "name": "Ivan",
                    "hired": "%s",
                    "fired": "%s",
                    "salary": 100.0
                  },
                  {
                    "name": "Ilya",
                    "hired": "%s",
                    "fired": "%s",
                    "salary": 200.0
                  },
                  {
                    "name": "Anton",
                    "hired": "%s",
                    "fired": "%s",
                    "salary": 300.0
                  }
                ]""", jsonNow, jsonNow, jsonNow, jsonNow, jsonNow, jsonNow);
        assertThat(report.generate(em -> true)).isEqualTo(expect);
    }
}