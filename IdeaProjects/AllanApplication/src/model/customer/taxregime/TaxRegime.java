package model.customer.taxregime;

import model.types.Address;
import java.io.Serializable;

/**
 * Defines the contract for the Tax Regime Strategy pattern.
 * <p>
 * Implementations of this interface define how tax discounts or calculations are applied
 * based on the customer's location ({@link model.types.Address}).
 * <p>
 * This module is part of <b>Bridge</b> pattern.
 *
 * @author zafir
 * @see model.types.Address
 */
public interface TaxRegime extends Serializable {
    double calculateTaxDiscount(Address address);
}


