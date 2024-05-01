package extrapractice.Files;

import java.io.File;

public class FileDemo {
    public static void main(String[] args) throws Exception {
        File f = new File(".//extrapractice//Files//lion"); // f is an instance of a file that represents file path
        System.out.println(f.getAbsolutePath()); // prints the absolute path of the file (lion.ser
        // System.out.println(f.exists()); // prints true if the file exists, false otherwise
        // System.out.println(f.getName()); // prints the name of the file (lion.ser)
        // System.out.println(f.getParent()); // prints the parent directory of the file (extrapractice/Files)
        // System.out.println(f.getPath()); // prints the path of the file (extrapractice/Files/lion.ser)
        // System.out.println(f.isFile()); // prints true if the file is a file, false otherwise
        // System.out.println(f.isDirectory()); // prints true if the file is a directory, false otherwise
        // System.out.println(f.canRead()); // prints true if the file can be read, false otherwise
        // System.out.println(f.canWrite()); // prints true if the file can be written, false otherwise
        System.out.println(f.getCanonicalPath()); // prints the canonical path of the file (extrapractice/Files/lion.ser)
        // f.createNewFile();
        f.mkdir();
    }
}
