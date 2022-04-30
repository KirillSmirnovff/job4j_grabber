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
            System.out.println("Enter \"1\" to get from cache or to load in cache, if file is not already there. \nEnter \"2\" to Exit.");
            String input = br.readLine();
            if ("1".equals(input)) {
                System.out.println("Enter filename:");
                String fileName = br.readLine();
                System.out.println(cache.load(fileName));
            } else if ("2".equals(input)) {
                run = false;
                System.out.println("Goodbye");
            } else {
                System.out.println("Wrong input");
            }
        }
    }
}
