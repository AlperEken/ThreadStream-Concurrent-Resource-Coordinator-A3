package model;

public class Product {
    public enum CategoryType {
        Food, Electrics, Tools
    }

    private final String name;
    private final double price;
    private final CategoryType category;

    public Product(String name, double price, CategoryType category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public CategoryType getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name + " - " + price;
    }
}
