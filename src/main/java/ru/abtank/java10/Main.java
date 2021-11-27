package ru.abtank.java10;

import ru.abtank.java10.less1.*;
import ru.abtank.java10.less1.polymorphism.Circle;
import ru.abtank.java10.less1.polymorphism.Figure;
import ru.abtank.java10.less1.polymorphism.Square;
import ru.abtank.java10.less1.polymorphism.Triangle;
import ru.abtank.java10.less3.ClassCounter;
import ru.abtank.java10.less3.ClassLock;
import ru.abtank.java10.less3.MyPingPong;
import ru.abtank.java10.less2.MyArrayList;
import ru.abtank.java10.less2.MyLinkedList;

import java.util.List;
import java.util.Random;

public class Main {
    private static void fillList(Random random, MyArrayList<Integer> selectionSortList, int size) {
        for (int i = 0; i < size; i++) {
            selectionSortList.add(random.nextInt(1000000));
        }
    }


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

      
        /** Less2 */
        Random random = new Random();
        int size = 10_000;
        // ArrayList
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        fillList(random, myArrayList, size);
        System.out.println(myArrayList.size());
        System.out.println(myArrayList.contains(123));
        System.out.println(myArrayList.get(2));
        myArrayList.set(2,123);
        System.out.println(myArrayList.get(2));
        System.out.println(myArrayList.remove(123));
        System.out.println(myArrayList.getCapacity());
        for (int i = 0; i < 10; i++) {
            System.out.print(myArrayList.get(i)+", ");
        }
        System.out.println("\nsort");
        myArrayList.quickSort();
        for (int i = 0; i < 10; i++) {
            System.out.print(myArrayList.get(i)+", ");
        }

        // -=MyLinkedList=-
        System.out.println("-=MyLinkedList=-");
        MyLinkedList<String> myLinkedList = new MyLinkedList();
        System.out.println();
        System.out.println(myLinkedList);
        myLinkedList.insertFirst("Katia");
        myLinkedList.insertFirst("Petia");
        myLinkedList.insertFirst("Maria");
        System.out.println(myLinkedList);
        System.out.println(myLinkedList.deleteFirst());
        System.out.println(myLinkedList);
        System.out.println(myLinkedList.getFirst());

        myLinkedList.insert(0,"Maria");
        myLinkedList.insert(1,"Sasha");
        myLinkedList.insert(3,"Sasha");
        System.out.println(myLinkedList);
        System.out.println(myLinkedList.delete("Sasha"));
        System.out.println(myLinkedList.delete("Peggggggggg"));
        System.out.println(myLinkedList.indexOf("Peggggggggg"));
        System.out.println(myLinkedList.indexOf("Sasha"));
        System.out.println(myLinkedList.contains("Peggggggggg"));
        System.out.println(myLinkedList);
      
      
       /** Less3 */
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
