package ru.abtank.java10;

import ru.abtank.java10.less1.Person;

public class Main {


    public static void main(String[] args) {
        System.out.println("Hello World");

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
    }
}
