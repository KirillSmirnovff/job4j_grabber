package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportJson implements Report {

    private Store store;

    public ReportJson(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        Gson gson = new GsonBuilder().create();
        return gson.toJson(employees);
    }

    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
        List<Employee> list = List.of(new Employee("hghf", now, now, 65.0));
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").create();
        System.out.println(gson.toJson(list));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(now.getTime()));
        int day = now.get(Calendar.DAY_OF_MONTH);
        System.out.println(day);
    }
}
