package evals.LiftSystem.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LiftDatabase {
    private LiftDatabase() {}

    private static LiftDatabase instance = null;

    public static LiftDatabase getInstance() {
        if(instance == null) {
            instance = new LiftDatabase();
        }
        return instance;
    }

    int[] lifts = new int[5]; 
    List<List<int[]>> liftUsers = new ArrayList<>();
    int[] max_capacities = new int[]{5,5,5,5,5};

    public int[] getLifts() {
        return lifts;
    }

    public void setLifts(int[] lifts) {
        this.lifts = lifts;
    }

    public void moveLift(int liftNumber, int floorNumber) {
        lifts[liftNumber] = floorNumber;
    }

    public int getLiftFloor(int liftNumber) {
        return lifts[liftNumber];
    }

    public void setLiftValue(int i, int value) {
        lifts[i] = value;
    }

    public int[] getCapacities() {
        return max_capacities;
    }

    public void setCapacities(int[] capacity) {
        this.max_capacities = capacity;
    }

    public void setCapacity(int liftNumber, int cap) {
        max_capacities[liftNumber] = cap;
    }

    public List<int[]> getCurrentCapacity(int liftNumber) {
        return liftUsers.get(liftNumber); 
    }

    public void incrementCurrentCapacity(int liftNumber, int src, int dest) {
        liftUsers.get(liftNumber).add(new int[]{src, dest});
    }   

    public void decrementCurrentCapacity(int liftNumber, int src ,int dest) {
        for(int[] dests: liftUsers.get(liftNumber)) {
            if(dests[0] == src && dests[1] == dest) {
                liftUsers.get(liftNumber).remove(dests);
                break;
            }
        }
    }
}
