package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> nextNode = head;
            for (int i = 0; i < size; i++) {
                if (nextNode.next == null) {
                    nextNode.next = newNode;
                    break;
                }
                nextNode = nextNode.next;
            }
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        int count = 0;
        Node<E> prevNode = head;
        Node<E> nextNode = head;
        while (count < index) {
            prevNode = prevNode.next;
            count++;
        }
        return prevNode.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int pointer = 0;
            private int expectedModCount = modCount;
            Node<E> prevNode = head;
            Node<E> nextNode = null;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> resultNode = head;
                if (pointer != 0) {
                    nextNode = prevNode.next;
                    prevNode = nextNode;
                }
                pointer++;
                return prevNode.item;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}