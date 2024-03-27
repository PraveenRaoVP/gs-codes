package assignments.assignment9_polymorphism;

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