package ru.job4j.design.srp;

public class Task {

    private int id;
    private String desc;
    private String author;

    public Task(int id, String desc, String author) {
        this.id = id;
        this.desc = desc;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void edit(Task task, String updateDesc) {
        task.setDesc(updateDesc);
    }
}
