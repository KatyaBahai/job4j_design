package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int inSize = 0;
    int outSize = 0;

    public T poll() {
        if (inSize == 0) {
            throw new NoSuchElementException();
        }
        T value = in.pop();
        inSize--;
        return value;

    }

    public void push(T value) {
        while (inSize != 0) {
            out.push(in.pop());
            inSize--;
            outSize++;
        }
        in.push(value);
        inSize++;
        while (outSize != 0) {
            in.push(out.pop());
            outSize--;
            inSize++;
        }

    }
}
