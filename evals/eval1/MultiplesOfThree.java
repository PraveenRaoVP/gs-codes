package evals.eval1;

public class MultiplesOfThree {
    public static void main(String[] args) {
        String s = "12471293748";
        int sum = 0;
        for(char i: s.toCharArray()) {
            sum+=Integer.parseInt(Character.toString(i));
        }
        if(sum%3 == 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
