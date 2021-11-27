package ru.abtank.java10.less3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class ClassLock extends Thread {
    private final ClassCounter classCounter;
    private final Lock lock;
    private int loopCount;

    public ClassLock(ClassCounter classCounter, int loopCount) {
        this.classCounter = classCounter;
        this.lock = new ReentrantLock();
        this.loopCount = loopCount;
    }

    @Override
    public void run() {
        try {
            while (ClassCounter.getCount() < loopCount) {
                if (lock.tryLock(10, TimeUnit.SECONDS)) {
                    sleep(100);
                    System.out.println("Thread = "+getName());
                    classCounter.doCount();
                    classCounter.doLogging();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            interrupt();
        }
    }
}
