package exercism;

public class ElonsToyCar {
    public int meters = 0;
    public int capacity = 100;

    public static ElonsToyCar buy() {
        return new ElonsToyCar();
    }

    public String distanceDisplay() {
        return "Driven " + this.meters + " meters";
    }

    public String batteryDisplay() {
        if (this.capacity == 0)
            return "Battery empty";
        return "Battery at " + this.capacity + "%";
    }

    public void drive() {
        if (capacity == 0)
            return;
        capacity--;
        meters += 20;
    }
}
