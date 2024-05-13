package assignments.assignment9_polymorphism;
// 4. In Vehicle hierarchy, add methods like start and stop in Vehicle and override them in Child classes.

class Vehicle {
    public void start() {
        System.out.println("Vehicle started");
    }

    public void stop() {
        System.out.println("Vehicle stopped");
    }
}

class Car extends Vehicle {
    public void start() {
        System.out.println("Car started");
    }

    public void stop() {
        System.out.println("Car stopped");
    }
}

class Bike extends Vehicle {
    public void start() {
        System.out.println("Bike started");
    }

    public void stop() {
        System.out.println("Bike stopped");
    }
}

public class VehiclesDemo {
    public static void main(String[] args) {
        Vehicle vehicle = new Car();
        vehicle.start();
        vehicle.stop();

        vehicle = new Bike();
        vehicle.start();
        vehicle.stop();
        
        vehicle = new Vehicle();
        vehicle.start();
        vehicle.stop();
    }
}