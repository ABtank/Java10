package ru.abtank.java10.less2;


import java.util.Arrays;
import java.util.Comparator;

public class MyArrayList<T extends Comparable<T>> {
    private T[] list;
    private int size = 0;
    private final int DEFAULT_CAPACITY = 10;
    private int capacity = DEFAULT_CAPACITY;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public MyArrayList() {
        list = (T[]) new Comparable[DEFAULT_CAPACITY];
    }

    public void add(T item) {
        list[size] = item;
        size++;
        expandCapacity(capacity);
    }

    public void add(int index, T item) {
        checkCorrectIndex(index);
        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = item;
        size++;
        expandCapacity(capacity);
    }

    public boolean remove(T item) {
        int i = 0;
        while (i < size && !list[i].equals(item)) {
            i++;
        }
        if (i == size) return false;
        for (int j = i; j < size - 1; j++) {
            list[j] = list[j + 1];
        }
        size--;
        list[size] = null;
        return true;
    }

    public T get(int index) {
        checkCorrectIndex(index);
        return list[index];
    }

    public void set(int index, T item) {
        list[index] = item;
    }

    public int size() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public final int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) return i;
        }
        return -1;
    }

    public boolean contains(T item) {
        return indexOf(item) > -1;
    }


    private void checkCorrectIndex(int index) {
        if (index > size || index < 0) {
            throw new IllegalArgumentException("invalid index=" + index);
        }
    }

    private void expandCapacity(int oldCapacity) {
        if (size > oldCapacity * 0.75) {
            int newCapacity = list.length + (list.length >> 1);
            if (newCapacity - list.length < 0) newCapacity = MAX_ARRAY_SIZE;
            capacity = newCapacity;
            list = Arrays.copyOf(list, newCapacity);
        }
    }

    private boolean less(T item1, T item2) {
        return item1.compareTo(item2) < 0;
    }

    private void swap(int index1, int index2) {
        T temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }

    public void quickSort (){
        quickSort(0,size-1);
    }
    private void quickSort(int lo, int hi){
        if(lo>=hi) return;
        int mid = lo +(hi-lo)/2;
        T opora = list[mid];
        int i = lo;
        int j = hi;
        while (i<=j){
            while (less(list[i],opora)){
                i++;
            }
            while (less(opora,list[j])){
                j--;
            }
            if(i<=j){
                swap(i,j);
                i++;
                j--;
            }
        }
        if(lo<j){
            quickSort(lo,j);
        }
        if(hi>i){
            quickSort(i,hi);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = 0; i < size; i++) {
            sb.append(list[i]).append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(" ]");
        return sb.toString();
    }
}
