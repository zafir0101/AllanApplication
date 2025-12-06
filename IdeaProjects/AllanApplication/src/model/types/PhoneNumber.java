package model.types;

import java.io.Serializable;

/**
 * Represents a validated Brazilian phone number.
 * <p>
 * This class ensures the phone number follows the standard format, including
 * a valid 2-digit area code (DDD) and a number with 8 or 9 digits.
 *
 * @author zafir
 */
public class PhoneNumber implements Serializable {
    private final DDD areaCode;
    private final String number;
    private static final int MIN_PHONE_NUMBER_LENGTH = 8;
    private static final int MAX_PHONE_NUMBER_LENGTH = 9;

    public PhoneNumber(String areaCode, String number) throws IllegalArgumentException {
        if (!isValid(areaCode, number)) {
            throw new IllegalArgumentException("Phone number invalid");
        }

        this.areaCode = DDD.fromCode(areaCode);
        this.number = number;
    }

    public String getAreaCode() {
        return String.valueOf(areaCode.getCode());
    }

    public String getNumber() {
        return number;
    }

    public static final boolean isValid(String code, String number) {
        return DDD.isValidDDD(code) && isValidNumber(number);
    }

    private static boolean isValidNumber(String number) {
        String phoneRegex = String.format("\\d{%d,%d}", MIN_PHONE_NUMBER_LENGTH, MAX_PHONE_NUMBER_LENGTH);
        return number != null && number.matches(phoneRegex);
        }

    @Override
    public String toString() {
        return String.format("(%s) %s", getAreaCode(), getNumber());
    }

    private enum DDD {
        AC("68"), AL("82"), AP("96"), AM("92"), BA("71"), CE("85"), DF("61"),
        ES("27"), GO("62"), MA("98"), MT("65"), MS("67"), MG("31"), PA("91"),
        PB("83"), PR("41"), PE("81"), PI("86"), RJ("21"), RN("84"), RS("51"),
        RO("69"), RR("95"), SC("48"), SP("11"), SE("79"), TO("63"), SC_2("47"),
        RS_2("53"), RS_3("54"), RS_4("55"), PR_2("42"), PR_3("43"), PR_4("44"),
        MG_2("32"), MG_3("33"), MG_4("34"), MG_5("35"), MG_6("37"), MG_7("38"),
        SP_2("12"), SP_3("13"), SP_4("14"), SP_5("15"), SP_6("16"), SP_7("17"),
        SP_8("18"), SP_9("19"), RJ_2("22"), RJ_3("24"), ES_2("28"), BA_2("73"),
        BA_3("74"), BA_4("75"), BA_5("77"), BA_6("79"), CE_2("88"), CE_3("89"),
        PE_2("87"), PB_2("84"), PB_3("87"), RN_2("83"), RN_3("84"), AL_2("82"),
        SE_2("75"), SE_3("79"), TO_2("63"), TO_3("64");


        private final String code;

        DDD(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public static boolean isValidDDD(String code) {
            for (DDD ddd : DDD.values()) {
                if (ddd.getCode().equals(code)) {
                    return true;
                }
            }
            return false;
        }

        public static DDD fromCode(String code) {
            for (DDD ddd : DDD.values()) {
                if (ddd.getCode().equals(code)) {
                    return ddd;
                }
            }
            throw new IllegalArgumentException("Invalid DDD code: " + code);
        }
    }
}
