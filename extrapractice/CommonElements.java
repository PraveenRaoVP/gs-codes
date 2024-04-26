package extrapractice;

public class CommonElements {
    public static void main(String[] args) {
        int[] arr1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int[] arr2 = { 5, 2, 3, 9, 5 };

        int minSize = Math.min(arr1.length, arr2.length);
        for (int i = 0; i < minSize; i++) {
            if (arr1[i] == arr2[i]) {
                System.out.print(arr1[i] + " ");
            }
        }
    }
}
