package ru.abtank.java10.less3;

public class ClassCounter {
    private static int count;

    public void doCount(){
        count = ++count;
    }

    public void doLogging(){
        System.out.println(count);
    }

    public ClassCounter() {
        count = 0;
    }

    public static int getCount() {
        return count;
    }
}
