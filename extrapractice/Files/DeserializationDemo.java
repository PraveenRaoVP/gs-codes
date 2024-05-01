package extrapractice.Files;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializationDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(".//extrapractice//Files//lion.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            Lion lion = (Lion) ois.readObject();
            System.out.println("Name: " + lion.name + " Weight: " + lion.weight + " Age: " + lion.age);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
