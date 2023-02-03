package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        growIfFull();
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
        }

    @Override
    public T remove(int index) {
    Objects.checkIndex(index, size);
    T removed = container[index];
    System.arraycopy(container, index + 1, container, index, container.length - index - 1);
    container[container.length - 1] = null;
    modCount++;
    size--;
    return removed;
    }

    @Override
    public T get(int index) {
    Objects.checkIndex(index, size);
    return container[index];
    }

    @Override
    public int size() {
    return size;
    }

    private void growIfFull() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, 1);
        }
        if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }

        };
    }
}