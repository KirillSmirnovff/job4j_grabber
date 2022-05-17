package ru.job4j.design.srp;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHtmlGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportHtml(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append(System.lineSeparator())
                .append("<html lang=\"en\">")
                .append(System.lineSeparator())
                .append("<head>")
                .append(System.lineSeparator())
                .append("<meta charset=\"UTF-8\">")
                .append("</head>")
                .append(System.lineSeparator())
                .append("<body>")
                .append(System.lineSeparator())
                .append("<p>Name; Hired; Fired; Salary;<br>")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";<br>")
                .append("</p>")
                .append(System.lineSeparator())
                .append("</body>")
                .append(System.lineSeparator())
                .append("</html>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenUSDSalaryGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 650);
        store.add(worker);
        Report engine = new ReportSalaryUSD(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() / ReportSalaryUSD.COURSE_USD).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGeneratedForHR() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 650);
        Employee workerTwo = new Employee("Dimitry", now, now, 540);
        Employee workerThree = new Employee("Vasily", now, now, 780);
        store.add(worker);
        store.add(workerTwo);
        store.add(workerThree);
        Report engine = new ReportHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(workerThree.getName()).append(";")
                .append(workerThree.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(workerTwo.getName()).append(";")
                .append(workerTwo.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGeneratedXml() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Employee worker = new Employee("Ivan", now, now, 650);
        store.add(worker);
        Report engine = new ReportXml(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employees>\n")
                .append("    <employees>\n")
                .append("        <fired>")
                .append(format.format(worker.getFired().getTime()))
                .append("</fired>\n")
                .append("        <hired>")
                .append(format.format(worker.getHired().getTime()))
                .append("</hired>\n")
                .append("        <name>")
                .append(worker.getName())
                .append("</name>\n")
                .append("        <salary>")
                .append(worker.getSalary())
                .append("</salary>\n")
                .append("    </employees>\n")
                .append("</employees>\n");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGeneratedJson() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 650);
        store.add(worker);
        Report engine = new ReportJson(store);
        StringBuilder expect = new StringBuilder()
                .append("[{\"name\":\"")
                .append(worker.getName())
                .append("\",\"hired\":{\"year\":")
                .append(now.get(Calendar.YEAR))
                .append(",\"month\":")
                .append(now.get(Calendar.MONTH))
                .append(",\"dayOfMonth\":")
                .append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":")
                .append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":")
                .append(now.get(Calendar.MINUTE))
                .append(",\"second\":")
                .append(now.get(Calendar.SECOND))
                .append("},\"fired\":{\"year\":")
                .append(now.get(Calendar.YEAR))
                .append(",\"month\":")
                .append(now.get(Calendar.MONTH))
                .append(",\"dayOfMonth\":")
                .append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":")
                .append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":")
                .append(now.get(Calendar.MINUTE))
                .append(",\"second\":")
                .append(now.get(Calendar.SECOND))
                .append("},\"salary\":")
                .append(worker.getSalary())
                .append("}]");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}