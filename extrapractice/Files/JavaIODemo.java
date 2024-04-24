package extrapractice.Files;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class JavaIODemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(".\\extrapractice\\Files\\sample.txt");
        FileOutputStream fos = new FileOutputStream(".\\extrapractice\\Files\\copy.txt");
        int value = fis.read();
        while(value != -1) {
            fos.write(value);
            value = fis.read();
        }
        fis.close();
        fos.close();
    }
}
