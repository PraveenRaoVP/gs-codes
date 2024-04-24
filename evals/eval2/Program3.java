package evals.eval2;

public class Program3 {
    public static void main(String[] args) {
        int[] num1 = {9,2,8,1,3,5,6,7,3,1,1,6};
        int[] num2 = {7,8,4,6,2,1,9,9,7};

        int n1 = num1.length;
        int n2 = num2.length;

        int finalArrSize = Math.max(n1,n2) + 1;

        int[] sum = new int[finalArrSize];
        int carry = 0;
        int i,j,k;
        i=n1-1;
        j=n2-1;
        k = finalArrSize-1;

        while(i>=0 && j>=0) {
            int s = num1[i] + num2[j] + carry;
            sum[k] = s%10;
            carry = s/10;
            i--; j--; k--;
        } 

        while(i>=0) {
            int s = num1[i] + carry;
            sum[k] = s%10;
            carry = s/10;
            i--; k--;
        }

        while(j>=0) {
            int s = num2[j] + carry;
            sum[k] = s%10;
            carry = s/10;
            j--; k--;
        }

        sum[k] = carry;

        for(int x=0;x<finalArrSize;x++) {
            if(x==0 && sum[x] == 0) continue;
            System.out.print(sum[x]+" ");
        }
    }
}
