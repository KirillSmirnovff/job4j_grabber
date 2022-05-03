package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Emulator {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter absolute cache folder path:");
        String path = br.readLine();
        DirFileCache cache = new DirFileCache(path);
        boolean run = true;
        while (run) {
            System.out.println("""
                    Enter "1" to get from cache.
                    Enter "2" to load in cache.
                    Enter "3" to Exit.""");
            String input = br.readLine();
            if ("1".equals(input)) {
                System.out.println("Enter filename:");
                String fileName = br.readLine();
                System.out.println(cache.get(fileName));
            } else if ("2".equals(input)) {
                System.out.println("Enter filename:");
                String fileName = br.readLine();
                String value = cache.load(fileName);
                cache.put(fileName, value);
                System.out.println("File loaded in cache");
            } else if ("3".equals(input)) {
                run = false;
                System.out.println("Goodbye");
            } else {
                System.out.println("Wrong input");
            }
        }
    }
}
