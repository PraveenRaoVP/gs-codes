package leetcode;

public class FindPivotInteger {
    public int pivotInteger(int n) {
        int l=1, r=n;
        int sum1=l;
        int sum2=r;
        if(n==1) return n;
        while(l<r) {
            if(sum1 >= sum2) {
                sum2 += --r;
            } else{
                sum1 += ++l;
            }
            if(sum1==sum2 && l+1 == r-1) {
                return l+1;
            }
        } 
        return -1;
    }

    public int pivotInteger2(int n) {
        int y = n*(n+1)/2;
        int x = (int)Math.sqrt(n);
        if(x*x==y) return x;
        return -1;
    }
}
