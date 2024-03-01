package task2;

/*
 Alternate sorting: Given an array of integers, rearrange the array in such a way that
the first element is first maximum and second element is first minimum.
 Example:  Input : {1, 2, 3, 4, 5, 6, 7}
Output: {7, 1, 6, 2, 5, 3, 4}

 */

public class AlternateSorting {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int n = arr.length;

        for(int i: arr) {
            System.out.print(i+" ");
        }
        System.out.println();
        

        for(int i=0;i<n;i++) {
            if(i%2==0) {
                for(int j=i;j<n;j++) {
                    if(arr[i] < arr[j]) {
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
            } else {
                for(int j=i;j<n;j++) {
                    if(arr[i] > arr[j]) {
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
