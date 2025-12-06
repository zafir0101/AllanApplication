package model.types;

import java.io.Serializable;
import java.text.Collator;
import java.util.Locale;

/**
 * Represents a validated physical address.
 * <p>
 * This class encapsulates street, number, city, and state information, ensuring
 * that the state is a valid Brazilian UF. It also provides the ICMS rate associated with that state.
 *
 * @author zafir
 */
public class Address implements Serializable {
    private final String street;
    private final int number;
    private final String city;
    private final States state;

    public Address(String street, int number, String city, String state) throws IllegalArgumentException {
        if (!isValid(street , number, city, state)) {
            throw new IllegalArgumentException("Address invalid");
        }

        this.state = States.fromString(state);
        this.street = Character.toUpperCase(street.charAt(0)) + street.substring(1);
        this.number = number;
        this.city = Character.toUpperCase(city.charAt(0)) + city.substring(1);
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getStateName() {
        return state.getName();
    }

    public States getState() {
        return state;
    }

    public String getAcronym() {
        return state.getAcronym();
    }

    public int getNumber() {
        return number;
    }

    public double getIcmsRate() {
        return state.getIcmsRate();
    }


    private static final boolean isValid(String street, int number, String city, String state) {
        return States.isValidState(state) &&
                street != null && !street.isEmpty() &&
                city != null && !city.isEmpty() &&
                number >= 0;
    }

    @Override
    public String toString() {
        return String.format("%s, %d, %s, %s", getStreet(), getNumber(), getCity(), getStateName());
    }

    private enum States {
        ACRE("Acre", "AC", 17.0),
        ALAGOAS("Alagoas", "AL", 18.0),
        AMAPA("Amapá", "AP", 18.0),
        AMAZONAS("Amazonas", "AM", 18.0),
        BAHIA("Bahia", "BA", 18.0),
        CEARA("Ceará", "CE", 18.0),
        DISTRITO_FEDERAL("Distrito Federal", "DF", 18.0),
        ESPIRITO_SANTO("Espírito Santo", "ES", 17.0),
        GOIAS("Goiás", "GO", 17.0),
        MARANHAO("Maranhão", "MA", 18.0),
        MATO_GROSSO("Mato Grosso", "MT", 17.0),
        MATO_GROSSO_DO_SUL("Mato Grosso do Sul", "MS", 17.0),
        MINAS_GERAIS("Minas Gerais", "MG", 18.0),
        PARA("Pará", "PA", 17.0),
        PARAIBA("Paraíba", "PB", 18.0),
        PARANA("Paraná", "PR", 18.0),
        PERNAMBUCO("Pernambuco", "PE", 18.0),
        PIAUI("Piauí", "PI", 18.0),
        RIO_DE_JANEIRO("Rio de Janeiro", "RJ", 20.0),
        RIO_GRANDE_DO_NORTE("Rio Grande do Norte", "RN", 18.0),
        RIO_GRANDE_DO_SUL("Rio Grande do Sul", "RS", 18.0),
        RONDONIA("Rondônia", "RO", 17.0),
        RORAIMA("Roraima", "RR", 17.0),
        SANTA_CATARINA("Santa Catarina", "SC", 17.0),
        SAO_PAULO("São Paulo", "SP", 18.0),
        SERGIPE("Sergipe", "SE", 18.0),
        TOCANTINS("Tocantins", "TO", 18.0);

        private final String name;
        private final String Acronym;
        private final double icmsRate;
        private final static Collator myCollator = Collator.getInstance(new Locale("pt", "BR"));

        States(String name, String Acronym, double icmsRate) {
            this.icmsRate = icmsRate;
            this.name = name;
            this.Acronym = Acronym;
        }

        public String getName() {
            return name;
        }

        public String getAcronym() {
            return Acronym;
        }

        public double getIcmsRate() {
            return icmsRate;
        }

        public static boolean isValidState(String state) {
            myCollator.setStrength(Collator.PRIMARY);
            for (States s : States.values()) {
                if (myCollator.compare(s.getAcronym(), state) == 0 || myCollator.compare(s.getName(), state) == 0)
                    return true;
            }
            return false;
        }

        public static States fromString(String state) {
            myCollator.setStrength(Collator.PRIMARY);
            for (States s : States.values()) {
                if (myCollator.compare(s.getAcronym(), state) == 0 || myCollator.compare(s.getName(), state) == 0)
                    return s;
            }
            throw new IllegalArgumentException("State not found: " + state);
        }
    }


}



