package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if ((float) count / (float) capacity >= LOAD_FACTOR) {
            expand();
        }
        MapEntry<K, V> mapEntry = new MapEntry<>(key, value);
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = mapEntry;
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        MapEntry<K, V>[] tempTable = table;
        table = newTable;
        for (MapEntry<K, V> kvMapEntry : tempTable) {
            if (kvMapEntry != null) {
                int index = kvMapEntry.key == null ? 0 : indexFor(hash(kvMapEntry.key.hashCode()));
                table[index] = kvMapEntry;
            }
        }
    }

    @Override
    public V get(K key) {
        V value = null;
        int keyHashCode = key == null ? 0 : key.hashCode();
        for (MapEntry<K, V> kvMapEntry : table) {
            if (kvMapEntry == null) {
                continue;
            }
            if (kvMapEntry.key == null && key == null) {
                value = kvMapEntry.value;
                break;
            }
            if (kvMapEntry.key != null && kvMapEntry.key.hashCode() == keyHashCode && kvMapEntry.key.equals(key)) {
                value = kvMapEntry.value;
                break;
            }
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
       int index = key == null ? 0 : indexFor(hash(key.hashCode()));
       if (table[index] != null) {
           table[index] = null;
           modCount++;
           count--;
           rsl = true;
       }
       return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int modCountCheck = modCount;
            int number = 0;
            int index = 0;
            @Override
            public boolean hasNext() {
                if (modCount != modCountCheck) {
                    throw new ConcurrentModificationException();
                }
                return number < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                K key = null;
                for (int i = index; i < table.length; i++) {
                    index++;
                    if (table[i] != null) {
                        key = table[i].key;
                        number++;
                        break;
                }
                }
                return key;
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