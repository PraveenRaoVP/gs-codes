package extrapractice.Files;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class JavaIODemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(".\\extrapractice\\Files\\copy.txt");
        FileOutputStream fos = new FileOutputStream(".\\extrapractice\\Files\\copy.txt");
        long start = System.currentTimeMillis();
        int value = fis.read();
        while(value != -1) {
            fos.write(value);
            value = fis.read();
        }
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));
        fis.close();
        fos.close();
    }
}
