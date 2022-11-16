package it.unipr.classes;

import java.io.Serializable;
import java.util.HashMap;
/**
 * The {@code Payment} class is used to store
 * and manage all the information related to payments.
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public class Payment implements Serializable {
    private String id;
    private String reason;
    private String type;
    private Double amount;
    private String date;
    private String user;

    /**
     * {@code Boat} class constructor
     *
     * @param reason  reason of the payment ( Annual fee/ Boat Storage fee)
     * @param type  type the payment ( Bank Transfer/ Credit Card)
     * @param amount  amount of the payment
     * @param date  date of the payment
     * @param user  member who made the payment
     */
    public Payment( String reason, String type, double amount, String date, String user) {
        this.reason = reason;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.user = user;
    }

    /**
     * {@code HasMap<String, String>} class constructor
     *
     * @param values {@code HashMap<String, String>} contains all attributes values to create the {@code Boat} object
     */
    public Payment(HashMap<String, String> values) {
        this.id = values.get("id");
        this.reason = values.get("reason");
        this.type = values.get("type");
        this.amount = Double.parseDouble(values.get("amount"));
        this.date = values.get("date");
        this.user = values.get("user");
    }

    /**
     * Id getter
     *
     * @return {@code String} id
     */
    public String getId() {
        return id;
    }

    /**
     * Id setter
     *
     * @param id {@code String} id value to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Reason getter
     *
     * @return {@code String} reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Reason setter
     *
     * @param reason {@code String} reason value to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Type getter
     *
     * @return {@code String} type
     */
    public String getType() {
        return type;
    }

    /**
     * Type setter
     *
     * @param type {@code String} type value to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Amount getter
     *
     * @return {@code Double} amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Amount setter
     *
     * @param amount {@code Double} amount value to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Date getter
     *
     * @return {@code String} date
     */
    public String getDate() {
        return date;
    }

    /**
     * Date setter
     *
     * @param date {@code String} date value to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * User getter
     *
     * @return {@code String} user
     */
    public String getUser() {
        return user;
    }

    /**
     * User setter
     *
     * @param user {@code String} user value to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Default toString method
     *
     * @return {@code String} printable object
     */
    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", reason='" + reason + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
