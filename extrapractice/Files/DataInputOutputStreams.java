package extrapractice.Files;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

// read line by line and write line by line from a file. using data input and output streams

public class DataInputOutputStreams {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(".//extrapractice//Files//sample.txt");
        DataInputStream dis = new DataInputStream(fis);
        FileOutputStream fos = new FileOutputStream(".//extrapractice//Files//sample.txt");
        DataOutputStream dos = new DataOutputStream(fos);
        
        PrintStream ps = new PrintStream(fos);

        String line = dis.readLine();
        while (line != null) {
            ps.println(line);
            line = dis.readLine();
        }
        System.out.println("File copied successfully");

        dis.close();
        dos.close();    
        ps.close();
        fis.close();
        fos.close();
    }
}
