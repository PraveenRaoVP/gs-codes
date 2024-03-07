package assignments.assignment2;

public class TemperatureConverter {
    public double convertCelsiusToFahrenheit(double celsius) {
        return (celsius*9/5)+32;
    } 

    public static void main(String[] args) {
        TemperatureConverter obj = new TemperatureConverter();
        System.out.println("The temperature in fahrenheit is: " + obj.convertCelsiusToFahrenheit(37));
    }
}
