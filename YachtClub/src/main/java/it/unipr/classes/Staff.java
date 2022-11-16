package it.unipr.classes;

import java.io.Serializable;
import java.util.HashMap;

/**
 * The {@code Staff} class is used to store
 * and manage all the information related to a staff member of the Yacht Club.
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public class Staff implements Serializable {
    private String username;
    private String password;

    /**
     * Class default constructor
     */
    public Staff() {
    }

    /**
     * {@code Boat} class constructor
     *
     * @param username  staff member username
     * @param password  staff member password
     */
    public Staff(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * {@code Staff} class constructor
     *
     * @param s staff member used to create a new {@code Staff} object
     */
    public Staff(Staff s) {
        this.username = s.getUsername();
        this.password = s.getPassword();
    }

    /**
     * {@code HasMap<String, String>} class constructor
     *
     * @param values {@code HashMap<String, String>} contains all attributes values to create the {@code Boat} object
     */
    public Staff(HashMap<String, String> values) {
        this.username = values.get("username");
        this.password = values.get("password");
    }

    /**
     * Username getter
     *
     * @return {@code String} username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Username setter
     *
     * @param username username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Password getter
     *
     * @return {@code String} password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Password setter
     *
     * @param password password to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Default toString method
     *
     * @return {@code String} printable object
     */
    @Override
    public String toString() {
        return "Staff{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
