package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {
    private List<T> data;
    private int index;

    public CyclicIterator(List<T> data) {
        this.data = data;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        if (index >= data.size()) {
            index = 0;
        }
        if (data.size() != 0) {
            rsl = true;
        }
        return rsl;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data.get(index++);
    }
}