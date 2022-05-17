package ru.job4j.design.ocp;

import java.util.ArrayList;

public class ElementCollector {

    public ArrayList<Character> collect(String income) {
        ArrayList<Character> result = new ArrayList<>();
        for (Character ch : income.toCharArray()) {
            result.add(ch);
        }
        return result;
    }
}
