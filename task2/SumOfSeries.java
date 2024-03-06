package task2;

public class SumOfSeries {
    public static int sumOfSeries(int n) {
        int sum = 1;
        int rem = 10;
        int sums = 0;
        while(n>0) {
            sum += rem;
            sums+=sum;
            rem*=10;
            n-=1;
        }
        return sums/10;
    }
    public static void main(String[] args) {
        System.out.println(sumOfSeries(3));
    }
}
