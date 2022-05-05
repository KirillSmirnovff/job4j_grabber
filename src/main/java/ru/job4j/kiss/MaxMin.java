package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findValue(value, (i, j) -> comparator.compare(i, j) > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findValue(value, (i, j) -> comparator.compare(i, j) < 0);
    }

    private  <T> T findValue(List<T> list, BiPredicate<T, T> predicate) {
        T result = list.get(0);
        for (T element : list) {
            if (predicate.test(element, result)) {
                result = element;
            }
        }
        return result;
    }
}