package assignments.assignment11_abstractclass;

/* Change Employee class as abstract and add abstract methods like calculatePay() with concrete methods like getEmployeeDetails(), etc. Extend this abstract class in subclasses - HourlyEmployee and SalariedEmployee. Allow the user to input details of employees and calculate their respective pay using the abstract class reference. */

abstract class Employee {
    private String name;
    private int id;
    private String address;
    private String phone;
    private String email;
    private double noOfHoursWorked;

    public Employee(String name, int id, String address, String phone, String email, int noOfHoursWorked) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.noOfHoursWorked = noOfHoursWorked;
    }

    public abstract double calculatePay();

    public void getEmployeeDetails() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
    }

    public double getNoOfHoursWorked() {
        return noOfHoursWorked;
    }
}

class HourlyEmployee extends Employee {
    private double hourlyRate;

    public HourlyEmployee(String name, int id, String address, String phone, String email, int noOfHoursWorked, double hourlyRate) {
        super(name, id, address, phone, email, noOfHoursWorked);
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculatePay() {
        return hourlyRate * super.getNoOfHoursWorked();
    }
}

class SalariedEmployee extends Employee {
    private double salary;

    public SalariedEmployee(String name, int id, String address, String phone, String email, int noOfHoursWorked, double salary) {
        super(name, id, address, phone, email, noOfHoursWorked);
        this.salary = salary;
    }

    @Override
    public double calculatePay() {
        return salary + salary * (40 - super.getNoOfHoursWorked());
    }
}

public class Problem2 {
    public static void main(String[] args) {
        Employee emp1 = new HourlyEmployee("Employee 1", 1, "Address 1", "1234567890", "employee1@gmail.com", 50, 10.0);
        Employee emp2 = new SalariedEmployee("Employee 2",2,"Address 2", "2345678901", "employee2@gmail.com", 30, 1000.0);
        emp1.getEmployeeDetails();
        System.out.println("Pay: " + emp1.calculatePay());
        emp2.getEmployeeDetails();
        System.out.println("Pay: " + emp2.calculatePay());
    }
}
