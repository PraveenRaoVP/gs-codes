package evals.eval2;

public class Program4 {
    public static void main(String[] args) {
        int[] arr = {2,7,5,-1,-3,2,9,7};
        // int[] arr = {3,-1,6,1,-5,1,3,-8};
        int n = arr.length;

        largestSumContiguousSubarray(arr, n); 
    }

    public static void largestSumContiguousSubarray(int[] arr, int n) {
        int local = 0;
        int global = Integer.MIN_VALUE;

        int startIndex = 0;
        int endIndex = 0;

        int ultStartIndex = 0;
        int ultEndIndex = 0;

        for(int i=0;i<n;i++) {
            if(arr[i] > 0) {
                local+=arr[i];
                if(local > global) {
                    global = local;
                    endIndex = i;
                    ultStartIndex = startIndex;
                    ultEndIndex = endIndex;
                }
            } else {
                local = 0;
                startIndex = i+1;
            }
        }
        System.out.println("Sum: " + global);
        System.out.print("Elements: ");
        for(int i=ultStartIndex;i<=ultEndIndex;i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
