package model.customer;

import model.customer.taxregime.TaxRegime;
import model.types.Address;
import model.types.PhoneNumber;

/**
 * This class extends the {@link model.customer.Customer} class and represents an <b>Industry</b> customer.
 * <p>
 * It provides the specific implementation of the {@code getTotalCost} method, which calculates the cost
 * by applying the tax discount to the base stock cost including manufacturing overheads.
 *
 * @author zafir
 * @see model.customer.Customer
 * @see model.customer.BusinessCustomer
 */
public class IndustryCustomer extends Customer {

    public IndustryCustomer(String name, Address address,
                            PhoneNumber phoneNumber, TaxRegime taxRegime) {
        super(name, address, phoneNumber, taxRegime);
    }

    @Override
    public String toString() {
        return "Industry Customer" + super.toString();
    }

    @Override
    public double getTotalCost() {
        double baseCost = getStock().getTotalStockCost();
        double taxDiscount = getTaxRegime().calculateTaxDiscount(getAddress());
        double materialCost = baseCost * (1 - taxDiscount / 100);
        double MANUFACTURINGOVERHEAD = 1.15;

        return materialCost * MANUFACTURINGOVERHEAD;
    }

    @Override
    public double getProfit() {
        double totalPrice = getStock().getTotalStockPrice();
        double totalCost = getTotalCost();

        return totalPrice - totalCost;
    }
}
