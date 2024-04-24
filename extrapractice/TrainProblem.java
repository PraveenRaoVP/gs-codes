package extrapractice;

import java.util.Scanner;

/*
 *  A train line has two stations on it, A and B. Trains can take trips from A to B or from
B to A
multiple times during a day. When a train arrives at B from A (or arrives at A from B), it
needs a
certain amount of time before it is ready to take the return journey - this is the
turnaround time.
For example, if a train arrives at 12:00 and the turnaround time is 0 minutes, it can leave
immediately, at 12:00.
A train timetable specifies departure and arrival time of all trips between A and B. The
train
company needs to know how many trains have to start the day at A and B in order to
make the
timetable work: whenever a train is supposed to leave A or B, there must actually be one
there
ready to go. There are passing sections on the track, so trains don't necessarily arrive in
the
same order that they leave. Trains may not travel on trips that do not appear on the
schedule.
Input Explanation
The first line is the turnaround time, T, in minutes. The next line has two numbers on it,
NA and
NB. NA is the number of trips from A to B, and NB is the number of trips from B to A.
Then there
are NA lines giving the details of the trips from A
to B.
Each line contains two fields, giving the HH:MM departure and arrival time for that trip.
The
departure time for each trip will be earlier than the arrival time. All arrivals and
departures occur
on the same day. The trips may appear in any order - they are not necessarily sorted by
time.
The hour and minute values are both two digits, zero-padded, and are on a 24-hour clock
(00:00
through 23:59).
After these NA lines, there are NB lines giving the departure and arrival times for the
trips from
B to A.
Example 1:
Input
5
3 2
09:00 12:00
10:00 13:00
11:00 12:30
12:02 15:00
09:00 10:30
Output :
Total Trains needed station A:2
Total Trains needed station B:2
Example 2
Input
2
2 0
09:00 09:01
12:00 12:02
output
Total Trains needed station A:2
Total Trains needed station B:0
 */

public class TrainProblem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int turnAroundTime = sc.nextInt();

        int tripsFromAtoB = sc.nextInt();
        int tripsFromBtoA = sc.nextInt();

        int[] arrivalTimeAtA = new int[tripsFromAtoB];
        int[] departureTimeAtA = new int[tripsFromAtoB];
        int[] arrivalTimeAtB = new int[tripsFromBtoA];
        int[] departureTimeAtB = new int[tripsFromBtoA];    

        for (int i = 0; i < tripsFromAtoB; i++) {
            String[] time = sc.next().split(":");
            arrivalTimeAtA[i] = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            time = sc.next().split(":");
            departureTimeAtA[i] = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
        }
    
        for (int i = 0; i < tripsFromBtoA; i++) {
            String[] time = sc.next().split(":");
            arrivalTimeAtB[i] = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            time = sc.next().split(":");
            departureTimeAtB[i] = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
        }

        int trainsAtA = 0;
        int trainsAtB = 0;

        for(int i=0;i<tripsFromAtoB;i++) {
            for(int j=0; j<tripsFromBtoA;j++) {
                if(i!=j) {
                    if(arrivalTimeAtA[i]-turnAroundTime <= departureTimeAtB[j]) {
                        trainsAtA++;
                    }
                }
            }
        }

        for(int i=0;i<tripsFromBtoA;i++) {
            for(int j=0;j<tripsFromAtoB;j++) {
                if(i!=j) {
                    if(arrivalTimeAtB[i]-turnAroundTime <= departureTimeAtA[j]) {
                        trainsAtB++;
                    }
                }
            }
        }

        System.out.println("Total Trains needed station A:"+trainsAtA);
        System.out.println("Total Trains needed station B:"+trainsAtB);
    }
}
