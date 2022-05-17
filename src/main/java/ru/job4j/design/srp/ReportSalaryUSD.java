package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportSalaryUSD implements Report {

    public static final double COURSE_USD = 65.00;
    private Store store;

    public ReportSalaryUSD(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            double salaryUSD = employee.getSalary() / COURSE_USD;
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(salaryUSD).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}