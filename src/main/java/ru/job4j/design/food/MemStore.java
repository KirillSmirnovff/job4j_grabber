package ru.job4j.design.food;

import java.util.LinkedHashMap;
import java.util.Map;

public class MemStore {

    private final Map<String, Store> allStorages = new LinkedHashMap<>();

    public Map<String, Store> getAllStorages() {
        return allStorages;
    }
}
