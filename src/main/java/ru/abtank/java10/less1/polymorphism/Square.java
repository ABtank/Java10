package ru.abtank.java10.less1.polymorphism;

public class Square extends Figure {
    public Square(int r) {
        super();
        this.radius = r;
    }

    @Override
    public int area() {
        return (int) Math.pow(4*radius,2);
    }

    @Override
    public int perimeter() {
        return (int) Math.sqrt(area()*16);
    }
}
