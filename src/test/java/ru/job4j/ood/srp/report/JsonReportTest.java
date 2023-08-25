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
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
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
                  }
                ]""", jsonNow, jsonNow);
        assertThat(report.generate(em -> true)).isEqualTo(expect);
    }
}