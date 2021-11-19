package ru.abtank.java10.less1.polymorphism;

public class Circle extends Figure {

    public Circle(int r) {
        super();
        this.radius = r;
    }

    @Override
    public int area() {
        return (int) Math.pow(Math.PI*radius,2);
    }

    @Override
    public int perimeter() {
        return (int) (Math.PI*2*radius);
    }
}
