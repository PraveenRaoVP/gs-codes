package extrapractice;

/**
 * LambdaTrial
 */


interface Printer {
    void showDocuments();
    void printDocument(String documentName);
}

public class LambdaTrial{
    public static void main(String[] args) {
        Printer p = new Printer() {
            @Override
            public void showDocuments() {
                System.out.println("Showing documents");
            }

            @Override
            public void printDocument(String documentName) {
                System.out.println("Printing " + documentName);
            }
        };
        
        p.showDocuments();
        p.printDocument("Sample");
    }
}