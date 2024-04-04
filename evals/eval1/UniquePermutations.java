package evals.eval1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniquePermutations {
    public static void main(String[] args) {
        String str = "abc";
        List<String> ans = permute(str);
        for(String s: ans) {
            System.out.print(s+" ");
        }
    }

    public static List<String> permute(String str) {
        List<String> result = new ArrayList<>();

        generatePermutations(str.toCharArray(), 0, result);

        return result;
    }

    public static void generatePermutations(char[] arr, int index, List<String> result) {
        if(index == arr.length) {
            result.add(new String(arr));
            return;
        }
        Set<Character> visited = new HashSet<>();
        for(int i=index;i<arr.length;i++) {
            if(!visited.contains(arr[i])) {
                visited.add(arr[i]);
                swap(arr, i, index);
                generatePermutations(arr, index+1, result);
                swap(arr, i, index);
            }
        }   
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
