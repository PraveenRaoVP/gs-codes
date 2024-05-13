package assignments.assignment8_constructors_and_inheritance;

// 2. Write a hierarchy of geometric shape classes 'Circle', 'Rectangle', etc inherited from a common base class 'Shape'


class Shape {
    public void draw() {
        System.out.println("Drawing a shape");
    }
}

class Rectangle extends Shape {
    public void area(int a, int b) {
        System.out.println("Area of rectangle: " + a * b);
    }
}

class Circle extends Shape {
    public void area(int r) {
        System.out.println("Area of circle: " + 3.14 * r * r);
    }
}

public class ShapeDemo {
    public static void main(String[] args) {
        Shape shape = new Shape();
        shape.draw();

        Rectangle rectangle = new Rectangle();
        rectangle.draw();
        rectangle.area(10, 20);

        Circle circle = new Circle();
        circle.draw();
        circle.area(10);

    }
}
