package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
        import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
        import ru.job4j.ood.srp.model.Employee;
        import ru.job4j.ood.srp.store.MemStore;

        import java.util.Calendar;

        import static org.assertj.core.api.Assertions.assertThat;

public class AccountingReportTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Report engine = new AccountingReport(store, parser, converter, Currency.RUB, Currency.EUR);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, worker.getSalary(), Currency.EUR))
                .append(System.lineSeparator());
        System.out.println(expect);
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}