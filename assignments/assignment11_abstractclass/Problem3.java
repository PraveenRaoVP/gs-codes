package assignments.assignment11_abstractclass;

/* Create an abstract class named Product with methods like getPrice() and getDescription(). Extend the abstract class in classes representing different types of products such as ElectronicsProduct , ClothingProduct , and BookProduct . Display the details of products and calculate the total price in a shopping cart. */

abstract class Product {
    private String name;

    public Product(String name) {
        this.name = name;
    }

    public abstract double getPrice();

    public abstract String getDescription();
}

class ElectronicsProduct extends Product {
    public ElectronicsProduct(String name) {
        super(name);
    }

    @Override
    public double getPrice() {
        return 10000.0;
    }

    @Override
    public String getDescription() {
        return "Electronics Product";
    }
}

class ClothingProduct extends Product {
    public ClothingProduct(String name) {
        super(name);
    }

    @Override
    public double getPrice() {
        return 5000.0;
    }

    @Override
    public String getDescription() {
        return "Clothing Product";
    }
}

class BookProduct extends Product {
    public BookProduct(String name) {
        super(name);
    }

    @Override
    public double getPrice() {
        return 500.0;
    }

    @Override
    public String getDescription() {
        return "Book Product";
    }
}

public class Problem3 {
    public static void main(String[] args) {
        Product[] products = new Product[3];
        products[0] = new ElectronicsProduct("Laptop");
        products[1] = new ClothingProduct("Shirt");
        products[2] = new BookProduct("Java Guide");

        double totalPrice = 0.0;
        for (Product product : products) {
            System.out.println("Product: " + product.getDescription());
            System.out.println("Price: " + product.getPrice());
            totalPrice += product.getPrice();
        }

        System.out.println("Total Price: " + totalPrice);
    }
}
