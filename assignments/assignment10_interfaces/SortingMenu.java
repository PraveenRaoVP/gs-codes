package assignments.assignment10_interfaces;

import java.util.Scanner;

/*
 *  Define an interface named Sortable with a method sort() . Implement this interface in classes representing different sorting algorithms such as bubble sort, quicksort, and merge sort. Allow the user to input an array, select a sorting algorithm, and display the sorted array.

 */

interface Sortable {
    void sort(int[] arr);
}

class BubbleSort implements Sortable {
    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < n - i - 1; j++) {
                if(arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }    
    }
}

class QuickSort implements Sortable {

    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int i, int j) {
        if(i < j) {
            int p = partition(arr, i, j);
            quickSort(arr, i, p - 1);
            quickSort(arr, p + 1, j);
        }
    }

    private int partition(int[] arr, int i, int j) {
        int pivot = arr[j];
        int p = i-1;

        for(int k = i; k < j; k++) {
            if(arr[k] < pivot) {
                p++;
                int temp = arr[p];
                arr[p] = arr[k];
                arr[k] = temp;
            }
        }

        int temp = arr[p + 1];
        arr[p + 1] = arr[j];
        arr[j] = temp;
        
        return p + 1;
    }
}

class MergeSort implements Sortable {

    private void mergeSort(int[] arr, int low, int high) {
        if(low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    private void merge(int[] arr, int low, int mid, int high) {
        int n1 = mid-low+1;
        int n2 = high-mid;
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for(int i = 0; i < n1; i++) {
            leftArray[i] = arr[low+i];
        }

        for(int i= 0; i < n2; i++) {
            rightArray[i] = arr[mid+1+i];
        }

        int i = 0, j = 0, k = low;

        while(i < n1 && j < n2) {
            if(leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while(i<n1) {
            arr[k++] = leftArray[i++];
        }
        while(j<n2) {
            arr[k++] = rightArray[j++];
        }
    }

    @Override
    public void sort(int[] arr) {
        mergeSort(arr, 0,arr.length-1);
    }
    
}

public class SortingMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the elements of the array: ");
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("Select the sorting algorithm: ");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Quick Sort");
        System.out.println("3. Merge Sort");
        int choice = sc.nextInt();

        System.out.println("Array before sorting: ");
        for(int i=0;i<n;i++) {
            System.out.print(arr[i] + " ");
        }

        Sortable sortable = null;
        switch(choice) {
            case 1:
                sortable = new BubbleSort();
                break;
            case 2:
                sortable = new QuickSort();
                break;
            case 3:
                sortable = new MergeSort();
                break;
            default:
                System.out.println("Invalid choice");
                System.exit(0);
        }
        sortable.sort(arr);
        System.out.println("Sorted array: ");
        for(int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        sc.close();
    }
}
