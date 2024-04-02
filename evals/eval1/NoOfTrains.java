package evals.eval1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class NoOfTrains {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int turnAroundTime = sc.nextInt();
        int noOfTripsAToB = sc.nextInt();
        int noOfTripsBToA = sc.nextInt();

        List<int[]> tripsAToB = new ArrayList<>();
        for(int i=0;i<noOfTripsAToB;i++) {
            String[] times = sc.next().split(":");
            int depTime = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
            times = sc.next().split(":");
            int arrTime = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
            tripsAToB.add(new int[] {depTime, arrTime});
        }
        
        List<int[]> tripsBToA = new ArrayList<>();
        for(int i=0;i<noOfTripsBToA;i++) {
            String[] times = sc.next().split(":");
            int depTime = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
            times = sc.next().split(":");
            int arrTime = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
            tripsBToA.add(new int[] {depTime, arrTime});
        }

        Collections.sort(tripsAToB, Comparator.comparingInt(a->a[0]));
        Collections.sort(tripsBToA, Comparator.comparingInt(a->a[0]));

        int trainsAtA = calculateTrains(tripsAToB, tripsBToA, turnAroundTime);
        int trainsAtB = calculateTrains(tripsBToA, tripsAToB, turnAroundTime);

        System.out.println("Trains at A: "+trainsAtA);
        System.out.println("Trains at B: "+trainsAtB);

        sc.close();
    }

    public static int calculateTrains(List<int[]> depatures, List<int[]> arrivals, int turnAroundTime) {
        int trainsNeeded = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int[] dep: depatures) {
            while(!pq.isEmpty() && pq.peek() + turnAroundTime < dep[0]) {
                pq.poll();
            }
            if(pq.isEmpty()) {
                trainsNeeded++;
            } else {
                pq.poll();
            }
            pq.offer(dep[1]);
        }

        for(int[] arr: arrivals) {
            while(!pq.isEmpty() && pq.peek() + turnAroundTime < arr[0]) {
                pq.poll();
            }
            pq.offer(arr[1]);
        }
        return trainsNeeded;
    }
}
