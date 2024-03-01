package task2;

public class OddEvenSort {
    public static void main(String[] args) {
        int[] arr = {13,2,4,15,12,10,5};
        int n = arr.length;

        for(int i: arr) {
            System.out.print(i+" ");
        }
        System.out.println();
        
        for(int i=0;i<n;i++) {
            if(i%2==0) {
                for(int j=0;j<n-1;j+=2) {
                    if(arr[i] > arr[j]) {
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                } 
            } else {
                for(int j=1;j<n-1;j+=2) {
                    if(arr[i] < arr[j]) {
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
        
        for(int i: arr) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
