package ru.job4j.design.lsp;

public class ExampleOne {

    public class Animal {

        private int age;
        private String name;
        private double weight;

        public Animal(int age, String name, double weight) {
            this.age = age;
            this.name = name;
        }

        public Animal() {
        }
    }

    public class Hamster extends Animal {

        private int age;
        private String name;
        private double weight;

        public Hamster(int age, String name) {
            if (age > 5) {
                throw new IllegalArgumentException("Age is impossibly great for hamster!");
            }
            this.age = age;
            this.name = name;
        }
    }
}
