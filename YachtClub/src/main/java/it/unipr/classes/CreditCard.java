package it.unipr.classes;

import java.io.Serializable;
import java.util.HashMap;

/**
 * The {@code CreditCard} class is used to store
 * and manage all the information related to a {@code User} credit card.
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public class CreditCard implements Serializable {
    private String number;
    private String owner;
    private String expirationDate;
    private String cvc;

    /**
     * {@code CreditCard} class constructor
     *
     * @param number  card number
     * @param owner  card owner full name
     * @param expirationDate  card expiration date
     * @param cvc  card cvc code
     */
    public CreditCard(String number, String owner, String expirationDate, String cvc) {
        this.number = number;
        this.owner = owner;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
    }

    /**
     * {@code HasMap<String, String>} class constructor
     *
     * @param values {@code HashMap<String, String>} contains all attributes values to create the {@code CreditCard} object
     */
    public CreditCard(HashMap<String, String> values) {
        this.number = values.get("number");
        this.cvc = values.get("cvc");
        this.owner = values.get("owner");
        this.expirationDate = values.get("expiration_date");
    }

    /**
     * Number getter
     *
     * @return {@code String} number
     */
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Owner getter
     *
     * @return {@code String} owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Owner setter
     *
     * @param owner {@code String} owner value to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * ExpirationDate getter
     *
     * @return {@code String} expirationDate
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * ExpirationDate setter
     *
     * @param expirationDate {@code String} expirationDate value to set
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * CVC getter
     *
     * @return {@code String} cvc
     */
    public String getCvc() {
        return cvc;
    }

    /**
     * CVC setter
     *
     * @param cvc {@code String} cvc value to set
     */
    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    /**
     * Default toString method
     *
     * @return {@code String} printable object
     */
    @Override
    public String toString() {
        return "CreditCard{" +
                "number='" + number + '\'' +
                ", owner='" + owner + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvc='" + cvc + '\'' +
                '}';
    }
}
