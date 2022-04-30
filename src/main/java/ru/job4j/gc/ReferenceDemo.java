package ru.job4j.gc;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ReferenceDemo {
    public static void main(String[] args) {
        List<SoftReference<String>> softStrings = new ArrayList<>();
        softStrings.add(new SoftReference<>("soft one"));
        softStrings.add(new SoftReference<>("soft two"));
        softStrings.add(new SoftReference<>("soft three"));
        List<WeakReference<String>> weakStrings = new ArrayList<>();
        weakStrings.add(new WeakReference<>("weak one"));
        weakStrings.add(new WeakReference<>("weak two"));
        weakStrings.add(new WeakReference<>("weak three"));
        for (SoftReference<String> string : softStrings) {
            String strong = string.get();
            if (strong != null) {
                System.out.println(strong);
            }
        }
        for (WeakReference<String> string : weakStrings) {
            String strong = string.get();
            if (strong != null) {
                System.out.println(strong);
            }
        }
    }
}
