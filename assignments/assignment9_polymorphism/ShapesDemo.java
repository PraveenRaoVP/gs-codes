package assignments.assignment9_polymorphism;

abstract class Shape {
    public abstract void area();
    public abstract void perimeter();
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public void area() {
        System.out.println("Area of circle: " + Math.PI * radius * radius);
    }

    public void perimeter() {
        System.out.println("Perimeter of circle: " + 2 * Math.PI * radius);
    }
}

class Rectangle extends Shape {
    private double length;
    private double breadth;

    public Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    public void area() {
        System.out.println("Area of rectangle: " + length * breadth);
    }

    public void perimeter() {
        System.out.println("Perimeter of rectangle: " + 2 * (length + breadth));
    }
}

public class ShapesDemo {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        circle.area();
        circle.perimeter();

        Shape rectangle = new Rectangle(5, 10);
        rectangle.area();
        rectangle.perimeter();
    }
}
