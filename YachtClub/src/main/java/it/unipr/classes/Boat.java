package it.unipr.classes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
/**
 * The {@code Boat} class is used to store
 * and manage all the information related to a Boat.
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public class Boat implements Serializable {
    private String id;
    private String name;
    private double length;
    private String user;
    private Date feeExpiry;
    private String feeExpiryFormatted;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Class default constructor
     */
    public Boat() {
    }

    /**
     * {@code Boat} class constructor
     *
     * @param id  unique id of the boat
     * @param name  name of the boat
     * @param length  length of the boat
     * @param user  member owner of the boat
     */
    public Boat(String id, String name, double length, String user) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.user = user;
    }

    /**
     * {@code Boat} class constructor
     *
     * @param b boat used to create a new {@code Boat} object
     */
    public Boat(Boat b) {
        this.id = b.getId();
        this.name = b.getName();
        this.length = b.getLength();
        this.user = b.getUser();
    }

    /**
     * {@code HasMap<String, String>} class constructor
     *
     * @param values {@code HashMap<String, String>} contains all attributes values to create the {@code Boat} object
     */
    public Boat(HashMap<String, String> values) {
        this.id = values.get("id");
        this.name = values.get("name");
        this.length = Double.parseDouble(values.get("length"));
        this.user = values.get("user");
        try {
            this.feeExpiry = formatter.parse(values.get("annual_fee_expiry"));
        } catch (Exception e) {
            this.feeExpiry = null;
        }
        this.feeExpiryFormatted = formatter.format(feeExpiry);
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
     * @param id  {@code String} id value to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Name getter
     *
     * @return {@code String} name
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter
     *
     * @param name {@code String} name value to set
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Length getter
     *
     * @return {@code double} length
     */
    public double getLength() {
        return length;
    }

    /**
     * Length setter
     *
     * @param length {@code String} length value to set
     */
    public void setLength(double length) {
        this.length = length;
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
     * FeeExpiry {@code Date} getter
     *
     * @return {@code Date} feeExpiry
     */
    public Date getFeeExpiry() {
        return feeExpiry;
    }

    /**
     * FeeExpiry getter
     *
     * @return {@code String} feeExpiry
     */
    public String getExpiryString(){ return formatter.format(feeExpiry);}

    /**
     * Response setter
     *
     * @param feeExpiry {@code Date} feeExpiry value to set
     */
    public void setFeeExpiry(Date feeExpiry) {
        this.feeExpiry = feeExpiry;
    }

    /**
     * Formatted annual fee expiration date getter
     *
     * @return {@code String} feeExpiryFormatted
     */
    public String getFeeExpiryFormatted() {
        return feeExpiryFormatted;
    }

    /**
     * Formatted annual fee expiration date setter
     *
     * @param feeExpiryFormatted {@code String} feeExpiryFormatted value to set
     */
    public void setFeeExpiryFormatted(String feeExpiryFormatted) {
        this.feeExpiryFormatted = feeExpiryFormatted;
    }

    /**
     * Default toString method
     *
     * @return {@code String} printable object
     */
    @Override
    public String toString() {
        return "Boat{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", owner='" + user + '\'' +
                ", feeExpiry=" + formatter.format(feeExpiry) +
                '}';
    }
}
