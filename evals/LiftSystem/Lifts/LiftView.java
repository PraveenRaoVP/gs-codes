package evals.LiftSystem.Lifts;

import java.util.Scanner;

public class LiftView {
    private LiftModel liftModel;

    public LiftView() {
        this.liftModel = new LiftModel(this);
    }

    public void displayLifts() {
        int[] lifts = liftModel.getLifts();
        System.out.println("L1\tL2\tL3\tL4\tL5");  
        for(int i=0;i<5;i++) {
            System.out.print(lifts[i] + "\t");
        }
        System.out.println();
    }

    public void assignLiftToUser() {
        displayLifts();
        Scanner sc = new Scanner(System.in);
        System.out.print("Current Floor: ");
        int src = sc.nextInt();
        while(!liftModel.validateFloorNumber(src)) {
            System.out.println("Invalid floor number. Enter a proper floor number.");
            System.out.print("Current Floor: ");
            src = sc.nextInt();
        }
        System.out.print("Destination Floor: "); 
        int dest = sc.nextInt();
        while(!liftModel.validateFloorNumber(dest)) {
            System.out.println("Invalid floor number. Enter a proper floor number.");
            System.out.print("Destination Floor: "); 
            dest = sc.nextInt();
        }
        int liftNumber = liftModel.getClosestLift(src, dest);
        System.out.println("L"+(liftNumber+1)+" is assigned");
        displayLifts();
    }

    public void setLiftToMaintenance() {
        displayLifts();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the lift number you want to set to maintenance or set to working: ");
        int liftNumber = sc.nextInt();
        while(!liftModel.validateLiftNumber(liftNumber)) {
            System.out.println("Invalid lift number. Enter a proper lift number.");
            System.out.print("Enter the lift number you want to set to maintenance: ");
            liftNumber = sc.nextInt();
        }
        liftModel.setLiftValueToMaintenance(liftNumber);
        System.out.println("L"+(liftNumber)+" is set to maintenance");
        displayLifts();
    }

    public void setCapacityToLift() {
        Scanner sc = new Scanner(System.in);
        displayLifts();
        System.out.print("Enter the lift number: ");
        int liftNumber = sc.nextInt();
        while(!liftModel.validateLiftNumber(liftNumber)) {
            System.out.println("Invalid lift number. Enter a proper lift number."); 
            System.out.print("Enter the lift number: ");
            liftNumber = sc.nextInt();
        }

        System.out.print("Enter the capacity of the lift: ");
        int capacity = sc.nextInt();
        liftModel.addCapacityToLift(liftNumber, capacity);
    }

    public void ifAllLiftsAreFull() {
        System.out.println("All lifts are full. Please wait for some time.");
    }
}
