package evals.LiftSystem.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LiftDatabase {
    private LiftDatabase() {
        liftUsers = new HashMap<>();
        for(int i=0;i<5;i++) {
            liftUsers.put(i, new ArrayList<>());
        }
    }

    private static LiftDatabase instance = null;

    public static LiftDatabase getInstance() {
        if(instance == null) {
            instance = new LiftDatabase();
        }
        return instance;
    }

    int[] lifts = new int[5]; 
    int[] currentCapacity = new int[5];

    Map<Integer, List<int[]>> liftUsers;
    
    int[] max_capacities = new int[]{3,4,4,4,4};

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

    public int[] getMaxCapacities() {
        return max_capacities;
    }

    public void setCapacities(int[] capacity) {
        this.max_capacities = capacity;
    }

    public void setCapacity(int liftNumber, int cap) {
        max_capacities[liftNumber] = cap;
    }

    // public List<int[]> getCurrentCapacity(int liftNumber) {
    //     return liftUsers.get(liftNumber) == null ? new ArrayList<>() : liftUsers.get(liftNumber); 
    // }

    public int getCurrentCapacity(int liftNumber) {
        return currentCapacity[liftNumber];
    }

    // public void gettingIntoLift(int liftNumber, int src, int dest) {
    //     liftUsers.get(liftNumber).add(new int[]{src, dest});
    // }   

    public void incrementCapacity(int liftNumber) {
        currentCapacity[liftNumber]++;
    } 

    public void decrementCapacity(int liftNumber) {
        currentCapacity[liftNumber]--;
    }

    // public void gettingOutOfLift(int liftNumber, int src ,int dest) {
    //     for(int i=0;i<liftUsers.get(liftNumber).size();i++) {
    //         if(liftUsers.get(liftNumber).get(i)[0] == src && liftUsers.get(liftNumber).get(i)[1] == dest) {
    //             liftUsers.get(liftNumber).remove(i);
    //             break;
    //         }
    //     }
    // }
}
