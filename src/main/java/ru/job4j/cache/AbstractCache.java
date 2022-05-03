package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        SoftReference<V> reference = cache.getOrDefault(key, new SoftReference<>(null));
        V result = reference.get();
        if (Objects.equals(result, null)) {
            result = load(key);
            put(key, result);
        }
        return result;
    }

    protected abstract V load(K key);

}