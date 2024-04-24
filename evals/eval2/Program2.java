package evals.eval2;

import java.util.HashMap;
import java.util.Map;

public class Program2 {
    public static void main(String[] args) {
        int[] arr = { 4, 1, 2, 3, 1, 2, 5, 2 };
        int n = arr.length;

        arrangeArrayElements(arr, n);
    }

    public static void arrangeArrayElements(int[] arr, int n) {
        Map<Integer, Integer> map = new HashMap<>();

        Map<Integer, Integer> firstIndex = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            if (!firstIndex.containsKey(arr[i])) {
                firstIndex.put(arr[i], i);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (map.get(arr[i]) < map.get(arr[j])) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                } else if (map.get(arr[i]) == map.get(arr[j]) && i != j &&
                        firstIndex.get(arr[i]) > firstIndex.get(arr[j])) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        // int left = 0;
        // int right = n - 1;

        // while (left <= right) {
        // if (map.get(arr[left]) < map.get(arr[right])) {
        // int temp = arr[left];
        // arr[left] = arr[right];
        // arr[right] = temp;
        // } else if (map.get(arr[left]) == map.get(arr[right]) && left != right
        // && firstIndex.get(arr[left]) > firstIndex.get(arr[right])) {
        // int temp = arr[left];
        // arr[left] = arr[right];
        // arr[right] = temp;
        // }
        // left++;
        // right--;
        // }

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

    }
}
