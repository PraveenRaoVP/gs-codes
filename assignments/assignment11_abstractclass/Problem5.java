package assignments.assignment11_abstractclass;

/* Create 'Document' abstract class. Create subclasses like PDFDocument, TextDocument, ImageDocument, etc. Create abstract methods and concrete methods and demonstrate how they work. */

abstract class Document {
    private String name;
    private String content;

    public Document(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public abstract void displayDocumentType();

    public void displayDocumentDetails() {
        System.out.println("Document name: " + name);
        System.out.println("Content: " + content);
    }
}

class PDFDocument extends Document {
    public PDFDocument(String name, String content) {
        super(name, content);
    }

    @Override
    public void displayDocumentType() {
        System.out.println("Document type: PDF");
    }
}

class TextDocument extends Document {
    public TextDocument(String name, String content) {
        super(name, content);
    }

    @Override
    public void displayDocumentType() {
        System.out.println("Document type: Text");
    }
}

class ImageDocument extends Document {
    public ImageDocument(String name, String content) {
        super(name, content);
    }

    @Override
    public void displayDocumentType() {
        System.out.println("Document type: Image");
    }
}


public class Problem5 {
    public static void main(String[] args) {
        Document pdfDocument = new PDFDocument("PDF Document", "This is a PDF document");
        pdfDocument.displayDocumentType();
        pdfDocument.displayDocumentDetails();

        Document textDocument = new TextDocument("Text Document", "This is a text document");
        textDocument.displayDocumentType();
        textDocument.displayDocumentDetails();

        Document imageDocument = new ImageDocument("Image Document", "This is an image document");
        imageDocument.displayDocumentType();
        imageDocument.displayDocumentDetails();
    }
}
