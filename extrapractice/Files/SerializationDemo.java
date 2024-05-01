package extrapractice.Files;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationDemo {
    public static void main(String[] args) throws Exception {
        Lion lion = new Lion("Singam",50.3f,7);
        FileOutputStream fos = new FileOutputStream(".//extrapractice//Files//lion.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        System.out.println("Before serialization: " + lion.name + " " + lion.weight + " " + Lion.age);

        oos.writeObject(lion);
        System.out.println("Serialization done.");
        oos.close();
        fos.close();
    }
}

class Lion implements Serializable { // NotSerializableException if not implemented
    String name;
    float weight;
    static int age; // transient keyword to avoid serialization

    public Lion(String name, float weight, int age) {
        this.name = name;
        this.weight = weight;
        Lion.age = age;
    }
}
