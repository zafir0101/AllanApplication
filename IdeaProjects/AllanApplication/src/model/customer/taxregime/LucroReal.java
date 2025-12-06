package model.customer.taxregime;

import model.types.Address;

/**
 * Implementation of the {@link model.customer.taxregime.TaxRegime} strategy for "Lucro Real".
 * <p>
 * The tax discount is calculated as a fixed rate (9.25%) plus the state's ICMS rate.
 * <p>
 * This module is part of <b>Bridge</b> pattern.
 *
 * @author zafir
 */
public class LucroReal implements TaxRegime {
    @Override
    public String toString() {
        return "Lucro Real";
    }

    @Override
    public double calculateTaxDiscount(Address address) {
        return 9.25 + address.getIcmsRate();
    }
}
