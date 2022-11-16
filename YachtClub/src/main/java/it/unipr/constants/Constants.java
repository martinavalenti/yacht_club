package it.unipr.constants;
/**
 * The {@code Constants} class defines constants values used throughout the project.
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public final class Constants {
    //
    // SERVER
    //
    /**
     * Port used for server connection
     */
    public static final int SERVER_PORT = 4444;
    /**
     * Server Host value for Client-Server communication
     */
    public static final String SERVER_HOST = "localhost";
    /**
     * Maximum number of concurrent connections accepted by the server
     */
    public static final int MAX_CONNECTIONS = 100;
    /**
     * Number of requests to the server
     */
    public static int NUM_REQUESTS = 0;
    /**
     * Current number of client connections
     */
    public static int CURRENT_CONNECTIONS = 0;
    //
    // CLIENT
    //
    /**
     * Request parameters separator
     */
    public static final String REQUEST_SEPARATOR = ":";
    /**
     * Parameter separator
     */
    public static final String SEPARATOR = ";";
    //
    // CONTROLLERS
    //
    /**
     * Annual fee price
     */
    public static final double ANNUAL_FEE = 1000;
    /**
     * Storage fee price
     */
    public static final double STORAGE_FEE = 30;
    /**
     * Your Races label value
     */
    public static final String YOUR_RACES = "Your Races";
    /**
     * All Races label value
     */
    public static final String ALL_RACES = "All Races";
    /**
     * Past Races label value
     */
    public static final String PAST_RACES = "Past Races";
    /**
     * My Races button value
     */
    public static final String MY_RACES = "See my Races";
    /**
     * Available Races label value
     */
    public static final String AVAIL_RACES = "Available Races";
    //
    // PAYMENT REASON
    //
    /**
     * Payment of race entry fee
     */
    public static final String MOTIVE_RACE = "Race Entry Fee";
    /**
     * Payment of annual storage fee for a boat
     */
    public static final String MOTIVE_BOAT = "Boat Storage";
    /**
     * Payment of annual subscription fee
     */
    public static final String MOTIVE_FEE = "Annual Fee";
    //
    // DATABASE
    //
    /**
     * MySQL Database url
     */
    public static final String DB_URL = "jdbc:mysql://localhost:3306/yacht_club";
    /**
     * MySQL Database username
     */
    public static final String DB_USERNAME = "root";
    /**
     * MySQL Database password
     */
    public static final String DB_PASSWORD = "";
    //
    // RESPONSE RETURNED TO THE CLIENT
    //
    /**
     * Null message
     */
    public static final String NULL = "NULL";
    /**
     * Failed operation message
     */
    public static final String FAIL = "FAIL";
    /**
     * Default message
     */
    public static final String DEFAULT = "DEFAULT";
    /**
     * Successful operation message
     */
    public static final String SUCCESS = "OK";
    /**
     * Connection error message
     */
    public static final String CONN_ERROR = "CONNECTION_ERROR";
    //
    // REQUEST TYPES
    //
    /**
     * Member login
     */
    public static final String MEMBER_LOGIN = "MEMBER_LOGIN";
    /**
     * Staff login
     */
    public static final String STAFF_LOGIN = "STAFF_LOGIN";
    /**
     * Check if username is valid
     */
    public static final String CHECK_SIGNUP = "CHECK_SIGNUP";
    /**
     * Register new user
     */
    public static final String REGISTRATION = "REGISTRATION";
    /**
     * Add a new boat
     */
    public static final String INSERT_BOAT = "INSERT_NEW_BOAT";
    /**
     * Get user address
     */
    public static final String GET_ADDRESS = "GET_ADDRESS";
    /**
     * Get all user's boats
     */
    public static final String GET_BOATS = "GET_BOATS";
    /**
     * Get a credit card
     */
    public static final String GET_CARDS = "GET_CARDS";
    /**
     * Get all races in which a member is enrolled
     */
    public static final String GET_REG_RACES = "REG_RACES";
    /**
     * Add a new credit card
     */
    public static final String INSERT_CARD = "INSERT_NEW_CREDIT_CARD";
    /**
     * Get all races in which a member is not enrolled
     */
    public static final String GET_NOT_REG_RACES = "NOT_REG_RACES";
    /**
     * Add a new payment
     */
    public static final String INSERT_PAYMENT = "ADD_NEW_PAYMENT";
    /**
     * Update a member's annual entry fee expiration date
     */
    public static final String UPDATE_MEMBER_FEE = "UPDATE_MEMBER_ANNUAL_FEE";
    /**
     * Delete a credit card
     */
    public static final String DELETE_CARD = "DELETE_CREDIT_CARD";
    /**
     * Delete a boat
     */
    public static final String DELETE_BOAT = "DELETE_BOAT";
    /**
     * Get a specific notification
     */
    public static final String GET_NOTIFICATIONS = "GET_USER_NOTIFICATIONS";
    /**
     * Enroll member to a race
     */
    public static final String REGISTER_TO_RACE = "REGISTER";
    /**
     * Get all members
     */
    public static final String GET_ALL_MEMBERS = "GET_ALL_MEMBER";
    /**
     * Get all races
     */
    public static final String GET_ALL_RACES = "GET_ALL_RACES";
    /**
     * Get all past races
     */
    public static final String GET_PAST_RACES = "GET_PAST_RACES";
    /**
     * Add a new race
     */
    public static final String ADD_RACE = "ADD_RACE";
    /**
     * Get all payments
     */
    public static final String GET_ALL_PAYMENTS = "GET_ALL_PAYMENTS";
    /**
     * Get all boats
     */
    public static final String GET_ALL_BOATS = "GET_ALL_BOATS";
    /**
     * Add new notification
     */
    public static final String ADD_NOTIFICATION = "ADD_NOTIFICATION";
    /**
     * Delete a race
     */
    public static final String DELETE_RACE = "DELETE_RACE";
    /**
     * Delete a member
     */
    public static final String DELETE_MEMBER = "DELETE_MEMBER";
    /**
     * Delete a notification
     */
    public static final String DELETE_NOTIFICATION = "DELETE_NOTIFICATION";
    /**
     * Update boat storage fee expiration date
     */
    public static final String UPDATE_BOAT = "UPDATE_BOAT_STORAGE_FEE";
    //
    // ERRORS
    //
    /**
     * Missing fields
     */
    public static final String MISSING_FIELDS = "Please complete all fields.";
    /**
     * Missing account type
     */
    public static final String MISSING_ACCOUNT_TYPE = "Please select an account type.";
    /**
     * Invalid login credentials
     */
    public static final String BAD_CREDENTIALS = "Invalid username or password.";
    /**
     * Failure during server connection
     */
    public static final String CONNECTION_FAIL = "Server connection failed";
    /**
     * Username already taken
     */
    public static final String TAKEN_USERNAME = "Sorry, this username is already taken.";
    //
    // DATABASE QUERY PARAMETERS
    //
    /**
     * Empty parameter
     */
    public static final String PARAM = " ";
    /**
     * Array of member parameters
     */
    public static final String[] MEMBER_PARAMS = {"Member", "username", "password", "full_name", "tax_code", "address_id", "annual_fee"};
    /**
     * Array of staff parameters
     */
    public static final String[] STAFF_PARAMS = {"Staff", "username", "password"};
    /**
     * Array of address parameters
     */
    public static final String[] ADDRESS_PARAMS = {"Address", "id", "address", "city", "postal_code", "country"};
    /**
     * Array of race parameters
     */
    public static final String[] RACE_PARAMS = {"Race", "id", "name", "date", "entry_fee"};
    /**
     * Array of credit card parameters
     */
    public static final String[] CREDITCARD_PARAMS = {"CreditCard", "number", "owner", "cvc", "expiration_date"};
    /**
     * Array of boat parameters
     */
    public static final String[] BOAT_PARAMS = {"Boat", "id", "name", "length", "user", "annual_fee_expiry"};
    /**
     * Array of payment parameters
     */
    public static final String[] PAYMENT_PARAMS = {"Payment", "id", "reason", "type", "amount", "date", "user"};
    /**
     * Array of notification parameters
     */
    public static final String[] NOTIFICATION_PARAMS = {"Notification", "id", "content", "user"};
}
