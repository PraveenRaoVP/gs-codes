package week0;

public class FindIntersection {
    
    public static boolean isIntersect(int c1[][], int c2[][]) {
        // Point A : c1[0][0], c1[0][1]
        // Point B : c1[1][0], c1[1][1]
        // Point C : c2[0][0], c2[0][1]
        // Point D : c2[1][0], c2[1][1]
        int x1 = c1[0][0], y1 = c1[0][1];
        int x2 = c1[1][0], y2 = c1[1][1];
        int x3 = c2[0][0], y3 = c2[0][1];
        int x4 = c2[1][0], y4= c2[1][1];
        int a1 = x2 - x1; // vector AB
        int b1 = y2 - y1; // vector AB
        int a2 = x4 - x3; // vector CD
        int b2 = y4 - y3; // vector CD
        System.out.println(a1+" "+b1+" "+a2+" "+b2);

        int cp = a1*b2 - a2*b1; // cross product, determinant 
        if(cp==0) return false; // if cp is 0 then the lines are parallel

        double ua = (a2*(y1-y3) - b2*(x1-x3))/cp; // if less than 1 and greater than 0 then it is intersecting
        double ub = (a1*(y1-y3) - b1*(x1-x3))/cp; // if less than 1 and greater than 0 then it is intersecting

        return ua>=0 && ua<=1 && ub >= 0 && ub <=1; // if both are true then the lines are intersecting. why? because the lines are not parallel and the intersection point is within the line segments.
    }
    
    public static void main(String[] args) {
        int[][] c1 = {{1,1},{2,4}}; 
        int[][] c2 = {{1,4},{4,1}};
        System.out.println(isIntersect(c1, c2));
    }
}
