package ru.abtank.java10.less3;

public class MyPingPong {
    private final Object mon = new Object();
    private static String startStr = "ping";
    private static String endStr = "pong";
    private int number = 50;

    public int getNumber() {
        return number;
    }

    public MyPingPong(int number) {
        this.number = number;
    }

    public void play(String kick) {
        synchronized (mon) {
            try {
                for (int i = 0; i < number; i++) {
                    while (!startStr.equals(kick)) {
                        mon.wait();
                    }
                    String tmp = startStr;
                    startStr = endStr;
                    endStr = tmp;
                    System.out.println(kick);
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
