package model.stock;

import java.io.Serializable;

/**
 * Represents an item in the stock, associating a {@link model.stock.Product} with a specific quantity.
 * <p>
 * This class acts as a wrapper to manage inventory levels and calculate the total value
 * (quantity * price) and total cost (quantity * cost) for a specific product line.
 *
 * @author zafir
 * @see model.stock.Product
 */
public class ProductWithQuantity implements Serializable {
    private final Product product;
    private final int quantity;

    public ProductWithQuantity(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }

        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalValue() {
        return product.getPrice() * quantity;
    }

    public double getTotalCost() {
        return product.getCost() * quantity;
    }

    public String toString() {
        return String.format("%s - Quantity: %d, Total Value: %.2f, Total Cost: %.2f, Price/Unit: %.2f," +
                        " Cost/Unit: %.2f\nDescription: %s\n",
                             product.getName(), getQuantity(), getTotalValue(), getTotalCost(),
                            product.getPrice(), product.getCost(), product.getDescription());

    }
}
