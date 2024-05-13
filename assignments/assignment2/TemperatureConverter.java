package assignments.assignment2;

// Write a program to convert Celsius to Fahrenheit using appropriate datatypes and literal conventions
public class TemperatureConverter {
    public double convertCelsiusToFahrenheit(double celsius) {
        return (celsius*9/5)+32;
    } 

    public static void main(String[] args) {
        TemperatureConverter obj = new TemperatureConverter();
        System.out.println("The temperature in fahrenheit is: " + obj.convertCelsiusToFahrenheit(37));
    }
}
