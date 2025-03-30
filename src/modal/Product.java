package modal;

public class Product {
    private String name;
    private String imagePath;
    private String category;
    private String price; // Add this line for price

    public Product(String name, String imagePath, String category, String price) {
        this.name = name;
        this.imagePath = imagePath;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getCategory() {
        return category;
    }
    
    public String getPrice() {
        return price;
    }
}