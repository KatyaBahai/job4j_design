package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class XMLReportTest {

    @Test
    public void whenXMLGenerated() throws JAXBException {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Ilya", now, now, 200);
        Employee worker3 = new Employee("Anton", now, now, 300);
        store.add(worker);
        store.add(worker2);
        store.add(worker3);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        String xmlNow = parser.parse(now);
        JAXBContext context = JAXBContext.newInstance(MemStore.class);
        XMLReport report = new XMLReport(store, context);
        String expected = String.format("""
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <Employees>
                    <Employee>
                        <name>Ivan</name>
                        <hired>%s</hired>
                        <fired>%s</fired>
                        <salary>100.0</salary>
                    </Employee>
                    <Employee>
                        <name>Ilya</name>
                        <hired>%s</hired>
                        <fired>%s</fired>
                        <salary>200.0</salary>
                    </Employee>
                    <Employee>
                        <name>Anton</name>
                        <hired>%s</hired>
                        <fired>%s</fired>
                        <salary>300.0</salary>
                    </Employee>
                </Employees>
                """, xmlNow, xmlNow, xmlNow, xmlNow, xmlNow, xmlNow);
        assertThat(report.generate(em -> true)).isEqualTo(expected);

    }


}