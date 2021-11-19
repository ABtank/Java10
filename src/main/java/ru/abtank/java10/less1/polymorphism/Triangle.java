package ru.abtank.java10.less1.polymorphism;

public class Triangle extends Figure {
    int a;
    int b;
    int c;
    public Triangle(int a,int b, int c, int r) {
        super();
        this.radius = r;
        this.a=a;
        this.b=b;
        this.c=c;
    }

    @Override
    public int area() {
        return (int) (radius*(perimeter()*0.5));
    }

    @Override
    public int perimeter() {
        return a+b+c;
    }
}
