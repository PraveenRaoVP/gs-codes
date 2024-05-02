package evals.LiftSystem.UI;

import java.util.Scanner;

import evals.LiftSystem.Lifts.LiftView;

public class MainMenu {
    private LiftView liftView;

    public MainMenu() {
        this.liftView = new LiftView();
    }   

    public void init() {
        System.out.println("Welcome to the Lift System:-");
        
        do {
            displayMainMenu();
        } while(true);
    }

    public void displayMainMenu() {
        System.out.println("1. Display Lifts");
        System.out.println("2. Assign Lift To User");
        System.out.println("3. Set Lift To Maintenance");
        System.out.println("4. Set Capacity to Lift");
        System.out.println("5. Exit");
        System.out.println("Enter your choice:-");  
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch(choice) {
            case 1:
                liftView.displayLifts();
                break;
            case 2:
                liftView.assignLiftToUser();
                break;
            case 3:
                liftView.setLiftToMaintenance();
                break;
            case 4:
                liftView.setCapacityToLift();
                break;
            case 5: 
                System.out.println("Exiting the system");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}
