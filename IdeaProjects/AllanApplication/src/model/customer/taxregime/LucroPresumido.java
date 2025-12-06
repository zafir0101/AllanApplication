package model.customer.taxregime;

import model.types.Address;

/**
 * Implementation of the {@link model.customer.taxregime.TaxRegime} strategy for "Lucro Presumido".
 * <p>
 * The tax discount is calculated based solely on the ICMS rate of the customer's state.
 * <p>
 * This module is part of <b>Bridge</b> pattern.
 *
 *
 * @author zafir
 */
public class LucroPresumido implements TaxRegime {
    @Override
    public String toString() {
        return "Lucro Presumido";
    }

    @Override
    public double calculateTaxDiscount(Address address) {
        return address.getIcmsRate();
    }
}

