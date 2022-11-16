package it.unipr.classes;

import java.io.Serializable;
import java.util.HashMap;

/**
 * The {@code Race} class is used to store
 * and manage all the information related to a race.
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public class Race implements Serializable {
    private String id;
    private String name;
    private String date;
    private String entryFee;

    /**
     * {@code race} class constructor
     *
     * @param name  name of the race
     * @param date  date of the race
     * @param entryFee  entry fee price of the race
     */
    public Race( String name, String date, String entryFee) {
        this.name = name;
        this.date = date;
        this.entryFee = entryFee;
    }

    /**
     * {@code HasMap<String, String>} class constructor
     *
     * @param values {@code HashMap<String, String>} contains all attributes values to create the {@code Boat} object
     */
    public Race(HashMap<String, String> values) {
        this.id = values.get("id");
        this.name = values.get("name");
        this.date = values.get("date");
        this.entryFee = values.get("entry_fee");
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
     * EntryFee getter
     *
     * @return {@code String} entryFee
     */
    public String getEntryFee() {
        return entryFee;
    }

    /**
     * EntryFee setter
     *
     * @param entryFee {@code String} entryFee value to set
     */
    public void setEntryFee(String entryFee) {
        this.entryFee = entryFee;
    }

    /**
     * Default toString method
     *
     * @return {@code String} printable object
     */
    @Override
    public String toString() {
        return "Race{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", entryFee='" + entryFee + '\'' +
                '}';
    }
}
