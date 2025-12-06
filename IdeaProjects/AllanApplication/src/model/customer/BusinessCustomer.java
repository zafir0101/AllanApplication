package model.customer;

import model.customer.taxregime.TaxRegime;
import model.types.Address;
import model.types.PhoneNumber;

/**
 * This class extends the {@link model.customer.Customer} class and represents a <b>Business</b> customer.
 * <p>
 * It provides the specific implementation of the {@code getTotalCost} method, which calculates the cost
 * by applying the tax discount to the base stock cost, without manufacturing overheads.
 *
 * @author zafir
 * @see model.customer.Customer
 * @see model.customer.IndustryCustomer
 */
public class BusinessCustomer extends Customer {

    public BusinessCustomer(String name, Address address,
                            PhoneNumber phoneNumber, TaxRegime taxRegime) {
        super(name, address, phoneNumber, taxRegime);
    }

    @Override
    public String toString() {
        return "Business Customer" + super.toString();
    }

    @Override
    public double getTotalCost() {
        double baseCost = getStock().getTotalStockCost();
        double taxDiscount = getTaxRegime().calculateTaxDiscount(getAddress());
        double productCost = baseCost * (1 - taxDiscount / 100);

        return productCost;
    }

    @Override
    public double getProfit() {
        double totalPrice = getStock().getTotalStockPrice();
        double totalCost = getTotalCost();

        return totalPrice - totalCost;
    }
}
