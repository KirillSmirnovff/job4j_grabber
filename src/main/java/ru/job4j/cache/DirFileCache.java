package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.StringJoiner;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key)  {
        String result = get(key);
        if (Objects.equals(result, null)) {
            try (BufferedReader br = new BufferedReader(new FileReader(cachingDir + key))) {
                StringJoiner string = new StringJoiner(System.lineSeparator());
                br.lines().forEach(string::add);
                put(key, string.toString());
                result = "File loaded to cache";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}