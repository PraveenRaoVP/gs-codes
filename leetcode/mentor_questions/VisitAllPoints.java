package leetcode.mentor_questions;

public class VisitAllPoints {
    public int minTimeToVisitAllPoints(int[][] points) {
        int count = 0;

        for(int i=0;i<points.length-1;i++) {
            int x = points[i][0];
            int y = points[i][1];
            int a = points[i+1][0];
            int b = points[i+1][1];

            if(Math.abs(x-a) > Math.abs(y-b)) {
                count+=Math.abs(x-a);
            } else {
                count+=Math.abs(y-b);
            }
        }

        return count;
    }
}
