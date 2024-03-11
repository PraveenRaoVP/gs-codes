package extrapractice;

// given an integer input n, find the output of (sum of n natural nos)^2 - sum of squares  

public class SumOfNNaturalNos {
    public static int returnSumOfNNaturalNos(int n) {
        return n*(n+1)/2;
    }

    public static int returnSumOfSquares(int n) {
        return (n*(n+1)*(2*n+1))/6;
    }
    public static void main(String[] args) {
        System.out.println(returnSumOfNNaturalNos(10)*returnSumOfNNaturalNos(10) - returnSumOfSquares(10));
    }
}
