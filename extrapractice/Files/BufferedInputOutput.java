package extrapractice.Files;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedInputOutput {
    public static void main(String[] args) throws IOException {
        // FileInputStream fis = null;
        // BufferedInputStream bis = null;
        // FileOutputStream fos = null;
        // BufferedOutputStream bos = null;
        long start = System.currentTimeMillis();
        try (   FileInputStream fis = new FileInputStream(".//extrapractice//Files//sample.txt");
                FileOutputStream fos = new FileOutputStream(".//extrapractice//Files//copy.txt");
                BufferedInputStream bis = new BufferedInputStream(fis);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
        ) {
            int value = bis.read();
            while (value != -1) {
                bos.write(value);
                value = bis.read();
            }

            // launch the file copy.txt
            Runtime.getRuntime().exec("notepad .//extrapractice//Files//copy.txt");

            // launch a video file using code
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            long end = System.currentTimeMillis();
            System.out.println("Time taken: " + (end - start));
        }
    }
}
