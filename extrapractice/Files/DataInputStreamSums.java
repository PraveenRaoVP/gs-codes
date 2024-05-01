package extrapractice.Files;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Scanner;

// get 5 numbers and sum them using datainputstream

public class DataInputStreamSums {
    public static void main(String[] args) throws IOException {
        DataInputStream dis = new DataInputStream(System.in);

        int sum = 0;

        for (int i = 0; i < 5; i++) {
            System.out.println("Enter a number: ");
            sum += Integer.parseInt(dis.readLine());
        }

        System.out.println("Sum: " + sum);
    }
}
