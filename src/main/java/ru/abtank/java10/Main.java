package ru.abtank.java10;

import ru.abtank.java10.less1.*;
import ru.abtank.java10.less1.polymorphism.Circle;
import ru.abtank.java10.less1.polymorphism.Figure;
import ru.abtank.java10.less1.polymorphism.Square;
import ru.abtank.java10.less1.polymorphism.Triangle;
import ru.abtank.java10.less3.ClassCounter;
import ru.abtank.java10.less3.ClassLock;
import ru.abtank.java10.less3.MyPingPong;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        System.out.println("Hello World");
        //-=1=-
        Person person = Person.builder()
                .firstName("fn")
                .lastName("ln")
                .middleName("mn")
                .country("ctr")
                .address("add")
                .phone("ph")
                .age(12)
                .gender("helicopter")
                .build();
        System.out.println(person);

//      -=3=-
        Figure circle = new Circle(10);
        Figure square = new Square(20);
        Figure triangle = new Triangle(4, 8, 10, 20);
        List<Figure> list = List.of(circle, square, triangle);
        for (Figure figure : list) {
            System.out.println(figure.getClass().getSimpleName() + "\nПлощадь = " + figure.area() + "\nПериметр = " + figure.perimeter());
        }

//        Lock
        ClassCounter classCounter = new ClassCounter();
        new ClassLock(classCounter,(int) (Math.random()*10)).start();
        new ClassLock(classCounter,(int) (Math.random()*10)).start();

//        Ping -Pong
        int round = (int) (Math.random()*10);
        MyPingPong myPingPong = new MyPingPong(round);
        new Thread(()->{
            myPingPong.play("ping");
        }).start();
        new Thread(() ->{
            myPingPong.play("pong");
        }).start();

    }
}
