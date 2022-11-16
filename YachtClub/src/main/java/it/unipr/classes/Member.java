package it.unipr.classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * The {@code Member} class is used to store and manage
 * all the information related to a member of the Yacht Club.
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public class Member implements Serializable {
    private String username;
    private String password;
    private String fullName;
    private String taxCode;
    private String addressId;
    private Address address;
    private List<Boat> boats;
    private String annual_fee;
    private List<CreditCard> creditCards;
    private List<Race> races;
    private List<Notification> notifications;

    /**
     * Class default constructor
     */
    public Member() {
    }

    /**
     * {@code Member} class constructor
     *
     * @param username  unique username of the member
     * @param password  password of the member
     * @param fullName  full name of the member
     * @param taxCode  tax code of the member
     * @param addressId  address unique id associated to the member
     */
    public Member(String username, String password, String fullName, String taxCode, String addressId) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.taxCode = taxCode;
        this.addressId = addressId;
    }

    /**
     * {@code HasMap<String, String>} class constructor
     *
     * @param values {@code HashMap<String, String>} contains all attributes values to create the {@code Member} object
     */
    public Member(HashMap<String, String> values) {
        this.username = values.get("username");
        this.password = values.get("password");
        this.fullName = values.get("full_name");
        this.taxCode = values.get("tax_code");
        this.addressId = values.get("address_id");
        try {
            this.annual_fee = values.get("annual_fee");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * {@code Member} class constructor
     *
     * @param m Member used to create a new {@code Member} object
     */
    public Member(Member m) {
        this.username = m.getUsername();
        this.password = m.getPassword();
        this.fullName = m.getFullName();
        this.taxCode = m.getTaxCode();
        this.addressId = m.getAddressId();
        this.annual_fee = m.getAnnual_fee();
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
     * FullName getter
     *
     * @return {@code String} fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * FullName setter
     *
     * @param fullName full name to be set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * TaxCode getter
     *
     * @return {@code String} taxCode
     */
    public String getTaxCode() {
        return taxCode;
    }

    /**
     * TaxCode setter
     *
     * @param taxCode tax code to be set
     */
    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    /**
     * AddressId getter
     *
     * @return {@code String} addressId
     */
    public String getAddressId() {
        return addressId;
    }

    /**
     * AddressId setter
     *
     * @param addressId address id to be set
     */
    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    /**
     * Address getter
     *
     * @return {@code Address} address object
     */
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Notifications getter
     *
     * @return {@code List<Notification>} notifications
     */
    public List<Notification> getNotifications() {
        return notifications;
    }

    /**
     * Notifications setter
     *
     * @param notifications notifications to set
     */
    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    /**
     * Boats getter
     *
     * @return {@code List<Boat>} boats
     */
    public List<Boat> getBoats() {
        return boats;
    }

    /**
     * Boats setter
     *
     * @param boats boats to be set
     */
    public void setBoats(List<Boat> boats) {
        this.boats = boats;
    }

    /**
     * CreditCards getter
     *
     * @return {@code List<CreditCard>} creditCards
     */
    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    /**
     * CreditCards setter
     *
     * @param creditCards credit cards to be set
     */
    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    /**
     * Races getter
     *
     * @return {@code List<Race>} races
     */
    public List<Race> getRaces() {
        return races;
    }

    /**
     * Races setter
     *
     * @param races races to set
     */
    public void setRaces(List<Race> races) {
        this.races = races;
    }

    /**
     * Method that adds a race to the races list
     *
     * @param race {@code Race} to add
     */
    public void addRegRace(Race race) {
        this.races.add(race);
    }

    /**
     * Method that adds a boat to the boats list
     *
     * @param b {@code Boat} to add
     */
    public void addBoat(Boat b) {
        this.boats.add(b);
    }

    /**
     * Method that deletes a boat from the boats list
     *
     * @param boat {@code Boat} to delete
     */
    public void deleteBoat(Boat boat) {
        for (Boat b : this.boats)
            if (b.getId().equals(boat.getId())) {
                this.boats.remove(b);
                return;
            }
    }

    /**
     * Method that adds a credit card to the creditCards list
     *
     * @param c {@code CreditCard} to add
     */
    public void addCreditCard(CreditCard c) {
        this.creditCards.add(c);
    }

    /**
     * Method that deletes a credit card from the creditCards list
     *
     * @param c {@code CreditCard} to delete
     */
    public void deleteCreditCard(CreditCard c) {
        this.creditCards.remove(c);
    }

    /**
     * Method that deletes a notification from the notifications list
     *
     * @param n {@code Notification} to delete
     */
    public void deleteNotification(Notification n) {
        this.notifications.remove(n);
    }

    /**
     * Annual_fee getter
     *
     * @return {@code String} annual_fee
     */
    public String getAnnual_fee() {
        return annual_fee;
    }

    /**
     * Annual_fee setter
     *
     * @param annual_fee {@code String} annual fee expiration date to set
     */
    public void setAnnual_fee(String annual_fee) {
        this.annual_fee = annual_fee;
    }

    /**
     * Default toString method
     *
     * @return {@code String} printable object
     */
    @Override
    public String toString() {
        return "Member{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", taxCode='" + taxCode + '\'' +
                ", addressId='" + addressId + '\'' +
                ", address=" + address +
                ", boats=" + boats +
                ", annual_fee='" + annual_fee + '\'' +
                ", creditCards=" + creditCards +
                ", races=" + races +
                ", notifications=" + notifications +
                '}';
    }
}
