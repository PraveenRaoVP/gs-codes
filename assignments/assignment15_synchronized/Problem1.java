package assignments.assignment15_synchronized;

/*
 * Write a program to learn to use synchronized blocks to control access to shared resources.

Instructions:
Create a class named Printer with a method printDocument that prints a document name.
Use a synchronized block within printDocument to synchronize on this.
Create a Runnable implementation that calls printDocument in a loop.
Start multiple threads to run the Runnable and observe the synchronized behavior.

 */

class Printer {
    public void printDocument(String documentName) {
        synchronized(this) {
            for(int i=1; i<=5; i++) {
                System.out.println("Printing " + documentName + " page " + i);
            }
        }
    }
}

class PrintJob implements Runnable {
    Printer printer;
    String documentName;

    public PrintJob(Printer printer, String documentName) {
        this.printer = printer;
        this.documentName = documentName;
    }

    @Override
    public void run() {
        printer.printDocument(documentName);
    }
}

public class Problem1 {
    public static void main(String[] args) {
        Printer printer = new Printer();
        PrintJob job1 = new PrintJob(printer, "Document1");
        PrintJob job2 = new PrintJob(printer, "Document2");
        PrintJob job3 = new PrintJob(printer, "Document3");

        Thread thread1 = new Thread(job1);
        Thread thread2 = new Thread(job2);
        Thread thread3 = new Thread(job3);

        thread1.start();
        thread2.start();
        thread3.start();

        try{
            thread1.join();
            thread2.join();
            thread3.join();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
