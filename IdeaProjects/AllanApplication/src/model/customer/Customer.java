package model.customer;

import model.customer.taxregime.TaxRegime;
import model.stock.ProductWithQuantity;
import model.stock.Stock;
import model.types.Address;
import model.types.PhoneNumber;

import java.io.Serializable;

/**
 * Represents the abstract base for all customer types in the system.
 * <p>
 * Every customer possesses a name, contact information ({@link model.types.Address}, {@link model.types.PhoneNumber}),
 * a specific {@link model.customer.taxregime.TaxRegime}, and an individual {@link model.stock.Stock}.
 * <p>
 * This class provides common functionality to manage the stock, update the profile, and
 * view the financial summary. Specific cost calculations are delegated to subclasses.
 *
 * @author zafir
 * @see model.customer.BusinessCustomer
 * @see model.customer.IndustryCustomer
 */
public abstract class Customer implements Serializable {
    private String name;
    private Address address;
    private PhoneNumber phoneNumber;
    private TaxRegime taxRegime;
    private final Stock stock = new Stock();

    public Customer(String name, Address address, PhoneNumber phoneNumber, TaxRegime taxRegime) {
        this.name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.taxRegime = taxRegime;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public TaxRegime getTaxRegime() {
        return taxRegime;
    }

    public Stock getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void addProductToStock(ProductWithQuantity itemProduct) {
        stock.addProductWithQuantity(itemProduct);
    }

    public void removeProductFromStock(ProductWithQuantity itemProduct) {
        stock.removeProductWithQuantity(itemProduct);
    }

    @Override
    public String toString() {
        return ": Name: " + name + ", Address: " + address + ", Phone Number: " + phoneNumber + "";
    }

    public abstract double getTotalCost();

    public abstract double getProfit();
}

