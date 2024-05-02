package evals.LiftSystem.Lifts;

import java.util.ArrayList;
import java.util.List;

import evals.LiftSystem.database.LiftDatabase;
import evals.LiftSystem.utils.PrintersAndFormatters;

public class LiftModel {
    private LiftView liftView;

    public LiftModel(LiftView liftView) {
        this.liftView = liftView;
    }

    /**
     * Get all the lifts
     * @return int[] containing all the lifts
     */
    public int[] getLifts() {
        return LiftDatabase.getInstance().getLifts();
    }

    /**
     * Get the closest lift available to the user
     * @param src
     * @param dest
     * @return int representing lift number closest to the user
     */
    public int getClosestLift(int src, int dest) {
        int[] lifts = getLifts();
        int min = 11;
        int liftNumber = -1;
        for (int i : getRestrictedLifts(src, dest)) {
            if (lifts[i] == -1 || checkIsLiftFull(liftNumber)) {
                continue;
            }
            if (src - dest < 0) {
                // System.out.println("Inside smaller block");

                if (Math.abs(lifts[i] - src) < min) {
                    min = Math.abs(lifts[i] - src);
                    liftNumber = i;
                }
                // System.out.println("Lift Number inside small block: " + liftNumber);

            } else if (src - dest > 0) {
                // System.out.println("Inside greater block");
                if (Math.abs(lifts[i] - src) < min) {
                    min = Math.abs(lifts[i] - src);
                    liftNumber = i;
                }
                // System.out.println("Lift Number inside big block: " + liftNumber);
            }
        }
        if (liftNumber == -1) {
            PrintersAndFormatters.showMessage("All the lifts are full. Please wait for some time.");
        }
        LiftDatabase.getInstance().incrementCurrentCapacity(liftNumber, src, dest); // getting in the lift
        LiftDatabase.getInstance().moveLift(liftNumber, dest);
        LiftDatabase.getInstance().decrementCurrentCapacity(liftNumber, src, dest); // getting out the lift
        return liftNumber;
    }

    /**
     * Return the restricted lifts based on src and dest
     * @param src
     * @param dest
     * @return int[] containing the restricted lifts
     */
    public int[] getRestrictedLifts(int src, int dest) {
        if (src >= 0 && src <= 5 && dest >= 0 && dest <= 5) {
            return new int[] { 0, 1, 4 };
        } else if (src > 5 && src <= 10 && dest > 5 && dest <= 10) {
            return new int[] { 2, 3, 4 };
        }
        if (src == 0 || dest == 0) {
            if (src == 0) {
                if (dest > 5) {
                    return new int[] { 2, 3 };
                } else {
                    return new int[] { 0, 1 };
                }
            } else if (dest == 0) {
                if (src > 5) {
                    return new int[] { 2, 3 };
                } else {
                    return new int[] { 0, 1 };
                }
            }
        }
        return new int[] { 4 };
    }

    /**
     * Set the lift number to maintenance by making it -1
     * @param liftNumber
     */
    public void setLiftValueToMaintenance(int liftNumber) {
        if(LiftDatabase.getInstance().getLifts()[liftNumber - 1] == -1) {
            LiftDatabase.getInstance().setLiftValue(liftNumber - 1, 0);
        } else {
            LiftDatabase.getInstance().setLiftValue(liftNumber - 1, -1);
        }
        addCapacityToLift(liftNumber, 0);
        LiftDatabase.getInstance().setCapacity(liftNumber - 1, 0);
    }

    /**
     * Add capacity to the lift
     * @param liftNumber
     * @param capacity
     */
    public void addCapacityToLift(int liftNumber, int capacity) {
        LiftDatabase.getInstance().setCapacity(liftNumber, capacity);
    }

    /**
     * Validate the lift number
     * @param liftNumber
     * @return boolean
     */
    public boolean validateLiftNumber(int liftNumber) {
        return liftNumber > 0 && liftNumber <= 5;
    }

    /**
     * Validate the floor number
     * @param floorNumber
     * @return
     */
    public boolean validateFloorNumber(int floorNumber) {
        return floorNumber >= 0 && floorNumber <= 10;
    }

    /**
     * Check if the lift is full
     * @param liftNumber
     * @return
     */
    private boolean checkIsLiftFull(int liftNumber) {
        return LiftDatabase.getInstance()
                .getCurrentCapacity(liftNumber).size() >= LiftDatabase.getInstance().getCapacities()[liftNumber];
    }
}
