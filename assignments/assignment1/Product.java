package assignments.assignment1;

import java.util.UUID;

public class Product {
    private UUID productID;
    private String productName;
    private double productPrice;
    private int productQuantity;

    public void display() {
        System.out.println("Product ID: " + productID);
        System.out.println("Product Name: " + productName);
        System.out.println("Product Price: " + productPrice);
        System.out.println("Product Quantity: " + productQuantity);
    }

    public static void main(String[] args) {
        Product obj = new Product();
        obj.productID = UUID.randomUUID();
        obj.productName = "Laptop";
        obj.productPrice = 50000;
        obj.productQuantity = 10;
        obj.display();
    }
}
