package ru.job4j.kiss;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void max() {
        List<Integer> list = List.of(5, 3, 9, 15, 2);
        int result = 15;
        MaxMin equalizer = new MaxMin();
        int out = equalizer.max(list, Integer::compare);
        assertEquals(out, result);
    }

    @Test
    public void min() {
        List<String> list = List.of("one", "three", "nine", "two", "ten");
        String result = "nine";
        MaxMin equalizer = new MaxMin();
        String out = equalizer.min(list, CharSequence::compare);
        assertEquals(out, result);
    }
}