package it.unipr.classes;

import java.io.Serializable;
import java.util.HashMap;
/**
 *
 * The {@code Address} class is used to store and manage
 * members address, postal code, city and country information.
 *
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public class Address implements Serializable {
    private String address;
    private String city;
    private String postalCode;
    private String country;

    /**
     * Class default constructor
     */
    public Address() {
    }

    /**
     * {@code Address} class constructor
     *
     * @param a address used to create a new {@code Address} object
     */
    public Address(Address a){
        this.address = a.getAddress();
        this.city = a.getCity();
        this.postalCode = a.getPostalCode();
        this.country = a.getCountry();
    }

    /**
     * {@code Address} class constructor
     *
     * @param address {@code User} address
     * @param city {@code User} city
     * @param postalCode {@code User} postal code
     * @param country {@code User} country
     */
    public Address(String address, String city, String postalCode, String country) {
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    /**
     * {@code HasMap<String, String>} class constructor
     *
     * @param values {@code HashMap<String, String>} contains all attributes values to create the {@code Address} object
     */
    public Address(HashMap<String, String> values) {
        this.address = values.get("address");
        this.city = values.get("city");
        this.postalCode = values.get("postal_code");
        this.country = values.get("country");
    }

    /**
     * Address getter
     *
     * @return {@code String} address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Response setter
     *
     * @param address {@code String} address value to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * City getter
     *
     * @return {@code String} city
     */
    public String getCity() {
        return city;
    }

    /**
     * City setter
     *
     * @param city {@code String} city value to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Postal code getter
     *
     * @return {@code String} postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Response setter
     *
     * @param postalCode {@code String} postal code to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Country getter
     *
     * @return {@code String} country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Country setter
     *
     * @param country {@code String} country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Default toString method
     *
     * @return {@code String} printable object
     */
    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
