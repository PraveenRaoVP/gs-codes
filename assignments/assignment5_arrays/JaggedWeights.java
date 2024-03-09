package assignments.assignment5_arrays;

import java.util.Scanner;

public class JaggedWeights {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of people: ");
        int n = sc.nextInt();

        int[][] weights = new int[n][];
        
        for(int i=0;i<n;i++) {
            
            System.out.println("Enter the number of weights for user "+i+": ");
            int m = sc.nextInt();
            weights[i] = new int[m];
            System.out.println("Enter the weights for user "+i+": ");
            for(int j=0;j<m;j++) {
                weights[i][j] = sc.nextInt();
            }
            System.out.println("The weights for user "+i+" are: ");
            for(int j=0;j<m;j++) {
                System.out.print(weights[i][j]+" ");
            }
            System.out.println();
        }
        while(true) {
            System.out.println("Menu: ");
            System.out.println("1. Add weight to user.");
            System.out.println("2. Calculate minimum weight of user.");
            System.out.println("3. Show weights of user.");
            System.out.println("4. Exit.");
            System.out.println("Enter your choice: ");  
            int choice = sc.nextInt();

            switch(choice) {
                case 1:
                    System.out.println("Enter the user number: ");
                    int user = sc.nextInt();
                    System.out.println("Enter the new weight to be added to the user: ");
                    int newWeight = sc.nextInt();
                    addWeightToUser(user,weights, newWeight);
                    break;
                case 2:
                    System.out.println("Enter the user number: ");
                    int inpUser = sc.nextInt();
                    calculateMinWeightOfUser(inpUser, weights);
                    break;
                case 3: 
                    System.out.println("Enter the user number: ");
                    user = sc.nextInt();
                    showWeightsOfUser(user, weights);
                    break;
                case 4:
                System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Enter the correct choice.");
            }
        }
    }

    public static void showWeightsOfUser(int user, int[][] weights) {
        if(user < 0 || user >= weights.length) {
            System.out.println("Invalid user number.");
            return;
        }
        System.out.println();
        System.out.println("The weights for user "+user+" are: ");
        for(int j=0;j<weights[user].length;j++) {
            System.out.print(weights[user][j]+" ");
        }
        System.out.println();
    }

    public static void addWeightToUser(int user, int[][] weights, int newWeight) {
        if(user < 0 || user >= weights.length) {
            System.out.println("Invalid user number.");
            return;
        }
        System.out.println();

        int[] newWeights = new int[weights[user].length+1];
        for(int i=0;i<weights[user].length;i++) {
            newWeights[i] = weights[user][i];
        }
        newWeights[weights[user].length] = newWeight;
        weights[user] = newWeights;
        System.out.println("Weight added successfully.");
        System.out.println();
    }

    public static void calculateMinWeightOfUser(int user, int[][] weights) {
        if(user < 0 || user >= weights.length) {
            System.out.println("Invalid user number.");
            return;
        }
        int minWeight = Integer.MAX_VALUE;
        System.out.println();
        for(int i=0;i<weights[user].length;i++) {
            if(weights[user][i] < minWeight) {
                minWeight = weights[user][i];
            }
        }
        System.out.println("The minimum weight of user "+user+" is: "+minWeight);
        System.out.println(); 
    }
}
