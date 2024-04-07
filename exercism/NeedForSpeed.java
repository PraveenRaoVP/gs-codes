package exercism;

public class NeedForSpeed {
    int speed;
    int batteryDrain;
    int distanceDriven;
    int remainingBatteryPercentage;

    NeedForSpeed(int speed, int batteryDrain) {
        this.speed = speed;
        this.batteryDrain = batteryDrain;
        this.distanceDriven = 0;
        this.remainingBatteryPercentage = 100;
    }

    public void drive() {
        if (!batteryDrained()) {
            distanceDriven += speed;
            remainingBatteryPercentage -= batteryDrain;
        }
    }

    public int distanceDriven() {
        return distanceDriven;
    }

    public boolean batteryDrained() {
        return remainingBatteryPercentage < batteryDrain;
    }

    public static NeedForSpeed nitro() {
        return new NeedForSpeed(50, 4);
    }
}

class RaceTrack {
    int distance;

    RaceTrack(int distance) {
        this.distance = distance;
    }

    public boolean tryFinishTrack(NeedForSpeed car) {
        while (car.distanceDriven() < distance && !car.batteryDrained()) {
            car.drive();
        }
        return car.distanceDriven() >= distance;
    }
}
