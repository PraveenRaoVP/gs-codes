package evals.eval1;

/*  Given a rectangular sheet of length l and width w. we need to divide this sheet into
square sheets such that the number of square sheets should be as minimum as
possible.
Examples:
Input :l= 4 w=6
Output :6
We can form squares with side of 1 unit, But the number of squares will be 24, this is
not minimum. If we make square with side of 2, then we have 6 squares. and this is our
required answer.
And also we can’t make square with side 3, if we select 3 as square side, then whole
sheet can’t be converted into squares of equal length */

public class RectangularSheet {
    public static void main(String[] args) {
        int l = 3, w = 5;
        System.out.println(minSquares(l, w));
    }

    public static int gcd(int a, int b) {
        if(b==0) return a;
        return gcd(b,a%b);
    }

    public static int minSquares(int l, int w) {
        int side = gcd(l,w);
        return (l*w)/(side*side);
    }
}
