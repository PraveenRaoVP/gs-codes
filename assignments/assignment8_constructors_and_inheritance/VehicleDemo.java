package assignments.assignment8_constructors_and_inheritance;

// Vehicle Hierarchy

class Vehicle {
    String vehicleName;
    String vehicleModel;
    String company;

    public Vehicle(String vehicleName, String vehicleModel, String company) {
        this.vehicleName = vehicleName;
        this.vehicleModel = vehicleModel;
        this.company = company;
    }
}

class Car extends Vehicle {
    String fuelType;
    int mileage;

    public Car(String vehicleName, String vehicleModel, String company, String fuelType, int mileage) {
        super(vehicleName, vehicleModel, company);
        this.fuelType = fuelType;
        this.mileage = mileage;
    }
}

class Bike extends Vehicle {
    String fuelType;
    int mileage;

    public Bike(String vehicleName, String vehicleModel, String company, String fuelType, int mileage) {
        super(vehicleName, vehicleModel, company);
        this.fuelType = fuelType;
        this.mileage = mileage;
    }
}

class Bus extends Vehicle {
    String fuelType;
    int mileage;

    public Bus(String vehicleName, String vehicleModel, String company, String fuelType, int mileage) {
        super(vehicleName, vehicleModel, company);
        this.fuelType = fuelType;
        this.mileage = mileage;
    }
}

public class VehicleDemo {
    public static void main(String[] args) {
        Car car = new Car("Swift", "VXI", "Maruti Suzuki", "Petrol", 20);
        System.out.println("Car: " + car.vehicleName + " " + car.vehicleModel + " " + car.company + " " + car.fuelType + " " + car.mileage);

        Bike bike = new Bike("Pulsar", "150", "Bajaj", "Petrol", 50);
        System.out.println("Bike: " + bike.vehicleName + " " + bike.vehicleModel + " " + bike.company + " " + bike.fuelType + " " + bike.mileage);

        Bus bus = new Bus("Volvo", "B9R", "Volvo", "Diesel", 10);
        System.out.println("Bus: " + bus.vehicleName + " " + bus.vehicleModel + " " + bus.company + " " + bus.fuelType + " " + bus.mileage);
    }
}
