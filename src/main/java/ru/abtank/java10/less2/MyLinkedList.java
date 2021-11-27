package ru.abtank.java10.less2;

import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T> {

    private Node first;
    private int size;

    public MyLinkedList() {
        this.first = null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<T> {
        Node current = new Node(null, first);

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            current = current.next;
            return current.value;
        }
    }

    // указатель
    private class Node {
        T value;
        Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(T value) {
            this.value = value;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    //вставка в начало списка
    public void insertFirst(T item) {
        Node newNode = new Node(item, first);
        first = newNode;
        size++;
    }

    //вставка по индексу
    public void insert(int index, T item) {
        if (index < 0 && index > size) {
            throw new RuntimeException("Incorrect index = " + index);
        }
        if (index == 0) {
            insertFirst(item);
            return;
        }
        Node corrent = first;
        int i = 0;
        //остановимся на элементе после которого будет вставлен элемент
        while (i < index - 1) {
            corrent = corrent.next;
            i++;
        }
        // создаем узел и говорим что он указывает на след элемент
        Node newNode = new Node(item, corrent.next);
        // говорим предыдущему что он указывает на новый элемент
        corrent.next = newNode;
        size++;
    }

    // удаление первого элемента
    public T deleteFirst() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty");
        }
        Node oldFirst = first;
        first = first.next;
        size--;
        return oldFirst.value;
    }

    //удаление по значению T
    public boolean delete(T item) {
        if (isEmpty()) {
            return false;
        }
        if (first.value.equals(item)) {
            deleteFirst();
            return true;
        }
        Node current = first;
        //пока следующий элемент не нулл и не равен искомому обьекту
        while (current.next != null && !current.next.value.equals(item)) {
            current = current.next;
        }
        if (current.next == null) {
            return false;
        }
        current.next = current.next.next;
        size--;
        return true;
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty");
        }
        return first.value;
    }

    //номер индекса по объекту
    public final int indexOf(T item) {
        Node current = first;
        int index = 0;
        while (current != null) {
            if (current.value.equals(item)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public boolean contains(T item) {
        return indexOf(item) > -1;
    }

    @Override
    public String toString() {
        Node current = first;
        StringBuilder sb = new StringBuilder("[");
        while (current != null) {
            sb.append(current.value).append(", ");
            current = current.next;
        }
        if (sb.length() > 2) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }
}