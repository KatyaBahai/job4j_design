package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;

        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        MapEntry<K, V> mapEntry = new MapEntry<>(key, value);
        int index = buckIndex(key);
        if (table[index] == null) {
            table[index] = mapEntry;
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int buckIndex(K key) {
        return key == null ? 0 : indexFor(hash(key.hashCode()));
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> kvMapEntry : table) {
            if (kvMapEntry != null) {
                newTable[buckIndex(kvMapEntry.key)] = kvMapEntry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V value = null;
        int index = buckIndex(key);
        int keyHashCode = Objects.hashCode(key);
            if (table[index] != null
                    && Objects.hashCode(table[index].key) == keyHashCode
                    && Objects.equals(table[index].key, key)) {
            value = table[index].value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = buckIndex(key);
        int keyHashCode = Objects.hashCode(key);
        if (table[index] != null
                && Objects.hashCode(table[index].key) == keyHashCode
                && Objects.equals(table[index].key, key)) {
           table[index] = null;
           modCount++;
           count--;
           rsl = true;
       }
       return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int modCountCheck = modCount;
            int index = 0;
            int number = 0;
            @Override
            public boolean hasNext() {
                if (modCount != modCountCheck) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return number < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                number++;
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}