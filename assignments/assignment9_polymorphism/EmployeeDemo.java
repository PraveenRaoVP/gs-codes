package assignments.assignment9_polymorphism;
/*Add getEmployeeDetails method to the class 'Employee' and extend it in subclasses representing different types of employees such as HourlyEmployee and SalariedEmployee and overload getEmployeeDetails. Demonstrate polymorphism by printing various types of Employees by using Parent Class reference. */
abstract class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public void getEmployeeDetails() {
        System.out.println("Employee name: " + name);
    }
}

class HourlyEmployee extends Employee {
    private int hoursWorked;
    private int hourlyRate;

    public HourlyEmployee(String name, int hoursWorked, int hourlyRate) {
        super(name);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public void getEmployeeDetails() {
        super.getEmployeeDetails();
        System.out.println("Employee type: Hourly Employee");
        System.out.println("Hours worked: " + hoursWorked);
        System.out.println("Hourly rate: " + hourlyRate);
    }
}

class SalariedEmployee extends Employee {
    private int salary;

    public SalariedEmployee(String name, int salary) {
        super(name);
        this.salary = salary;
    }

    public void getEmployeeDetails() {
        super.getEmployeeDetails();
        System.out.println("Employee type: Salaried Employee");
        System.out.println("Salary: " + salary);
    }
}

public class EmployeeDemo {
    public static void main(String[] args) {
        Employee employee = new HourlyEmployee("John", 10, 20);
        employee.getEmployeeDetails();
        employee = new SalariedEmployee("Jane", 1000);
        employee.getEmployeeDetails();
    }
}
