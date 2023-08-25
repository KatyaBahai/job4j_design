package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.CalendarJsonParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.function.Predicate;

public class JsonReport implements Report {

    private final Store store;
    private final CalendarJsonParser parser;
    private final GsonBuilder gsonBuilder;

    public JsonReport(Store store, CalendarJsonParser parser, GsonBuilder gsonBuilder) {
        this.store = store;
        this.parser = parser;
        this.gsonBuilder = gsonBuilder;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        gsonBuilder.registerTypeAdapter(Calendar.class, parser);
        gsonBuilder.registerTypeAdapter(GregorianCalendar.class, parser);
        Gson gsonEmployees = gsonBuilder.setPrettyPrinting().create();
        return gsonEmployees.toJson(store.findBy(filter));
    }
}
