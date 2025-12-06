package model.stock;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Manages the inventory of a specific customer.
 * <p>
 * This class maintains a list of {@link model.stock.ProductWithQuantity} and provides methods
 * to add, remove, and calculate the total financial value of the stocked items.
 *
 * @author zafir
 * @see model.stock.ProductWithQuantity
 */
public class Stock implements Serializable {
    private List<ProductWithQuantity> productWithQuantityList;
    private double totalPrice;
    private double totalCost;

    public Stock() {
        this.productWithQuantityList = new java.util.ArrayList<>();
        this.totalPrice = 0;
        this.totalCost = 0;
    }

    public List<ProductWithQuantity> getProductWithQuantityList() {
        return Collections.unmodifiableList(productWithQuantityList);
    };

    public void addProductWithQuantity(ProductWithQuantity productWithQuantity) {
        if (productWithQuantity == null)
            throw new IllegalArgumentException("Product with quantity cannot be null.");
        this.productWithQuantityList.add(productWithQuantity);
        this.updateTotals();
    }

    public void removeProductWithQuantity(ProductWithQuantity productWithQuantity) {
        if (!this.productWithQuantityList.remove(productWithQuantity))
            throw new IllegalArgumentException("Product with quantity not found.");
        this.updateTotals();
    }

    public double getTotalStockPrice() {
        return totalPrice;
    }

    public double getTotalStockCost() {
        return totalCost;
    }

    private void updateTotals() {
        totalPrice = 0;
        totalCost = 0;
        for (ProductWithQuantity pwq : productWithQuantityList) {
            totalPrice += pwq.getTotalValue();
            totalCost += pwq.getTotalCost();
        }
    }
}

