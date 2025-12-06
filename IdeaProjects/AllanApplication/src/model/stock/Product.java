package model.stock;

import java.io.Serializable;

/**
 * Represents a specific product available for sale or purchase.
 * <p>
 * This class holds the basic details of an item, including its name, description,
 * selling price, and acquisition cost.
 *
 * @author zafir
 */
public class Product implements Serializable {
    private final String name;
    private final String description;
    private final double price;
    private final double cost;

    public Product(String name, String description, double price, double cost) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }

        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Product description cannot be null.");
        }

        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative.");
        }

        if (cost < 0) {
            throw new IllegalArgumentException("Product cost cannot be negative.");
        }

        this.name = Character.toUpperCase(name.charAt(0)) + name.substring(1);;
        this.description = Character.toUpperCase(description.charAt(0)) + description.substring(1);
        this.price = price;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("Product: %s, description: %s, price: %.2f, cost: %.2f}",
                             getName(), getDescription(), getPrice(), getCost());
    }
}