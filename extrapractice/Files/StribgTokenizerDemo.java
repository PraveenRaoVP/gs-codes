package extrapractice.Files;

import java.util.StringTokenizer;

public class StribgTokenizerDemo {
    public static void main(String[] args) {
        StringTokenizer st = new StringTokenizer("Praveen is a good boy", " ", true); // token - a meaningful unit of  text
        while(st.hasMoreTokens()) {
            System.out.print(st.nextToken());
        }
    }
}
