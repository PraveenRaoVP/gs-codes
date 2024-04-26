package extrapractice.Files;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.Scanner;

public class FileStreamExample {
    public static void main(String[] args) throws IOException {
        // Scanner sc = new Scanner(System.in);
        // FileOutputStream fw = new FileOutputStream(".//extrapractice//Files//output.txt");
        // System.out.println("Enter the text: ");
        
        // String text = sc.nextLine();
        // byte[] bytes = text.getBytes();
        // fw.write(bytes);
        // System.out.println("Data written to file successfully");
        // fw.close();

        // read from a byte array and write to a file
        // Scanner sc = new Scanner(System.in);
        // System.out.println("Enter the text: ");
        // String input = sc.nextLine();
        // ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
        // FileOutputStream fw = new FileOutputStream(".//extrapractice//Files//output.txt");
        // while(bais.available() > 0) {
        //     int data = bais.read();
        //     fw.write(data);
        // }
        // System.out.println("Data written to file successfully");

        Scanner sc = new Scanner(System.in);
        SequenceInputStream s = new SequenceInputStream(new ByteArrayInputStream("Hello ".getBytes()), new ByteArrayInputStream("World".getBytes()));
        FileOutputStream fw = new FileOutputStream(".//extrapractice//Files//output.txt", true);
        int data;
        while((data = s.read()) != -1) {
            fw.write(data);
        }

        System.out.println("Data written to file successfully");
        sc.close();
        fw.close();
    }
}
