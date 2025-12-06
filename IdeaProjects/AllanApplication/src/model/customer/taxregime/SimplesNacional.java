package model.customer.taxregime;

import model.types.Address;

/**
 * Implementation of the {@link model.customer.taxregime.TaxRegime} strategy for "Simples Nacional".
 * <p>
 * This module is part of <b>Bridge</b> pattern.
 * <p>
 * <i>This regime currently applies no tax discount (returns 0).</i>
 *
 * @author zafir
 */
public class SimplesNacional implements TaxRegime {
    @Override
    public String toString() {
        return "Simples Nacional";
    }

    @Override
    public double calculateTaxDiscount(Address address) {
        return 0;
    }
}