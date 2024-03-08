package task2;

import java.util.Arrays;

public class MergeSortedArrays {
    public static void main(String[] args) {
        int[] arr1 = {2,4,5,6,7,9,10,13};
        int[] arr2 = {2,3,4,4,5,6,7,8,9,11,15,16};
        int[] arr3 = new int[arr1.length+arr2.length];
        int i = 0, j = 0, k=0;
        int newLength = 0;
        while(i<arr1.length && j<arr2.length) {
            if(arr1[i] < arr2[j]) {
                arr3[k++] = arr1[i++];
                newLength++;
            } else if(arr1[i] > arr2[j]) {
                arr3[k++] = arr2[j++];
                newLength++;
            } else {
                if(k > 0 && arr3[k-1] != arr1[i])
                arr3[k++] = arr1[i++];
                j++;
                newLength++;
            }
        }
        while(i<arr1.length) {
            arr3[k++] = arr1[i++];
            newLength++;
        }
        while(j<arr2.length) {
            arr3[k++] = arr2[j++];
            newLength++;
        }
        int[] finalArray = Arrays.copyOfRange(arr3, 0, newLength-1);
        for(int l: finalArray) {
            System.out.print(l+" ");
        }
    }
}
