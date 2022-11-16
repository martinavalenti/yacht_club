package it.unipr.connection;

import java.io.Serializable;
import java.util.List;

import static it.unipr.constants.Constants.*;

/**
 * Response used for communication between client and server
 *
 * @param <T> Generic type of the data returned in the response
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public class Response<T> implements Serializable {
    private List<T> response;
    private String message = SUCCESS;
    private boolean empty = true;

    /**
     * Class default constructor
     */
    public Response() {
    }
    /**
     * Class List constructor
     *
     * @param response List of {@code T} class objects
     */
    public Response(List<T> response) {
        this.response = response;
    }

    /**
     * Class message constructor
     *
     * @param message message to be set
     */
    public Response(String message) {
        this.message = message;
    }


    /**
     * Response getter
     *
     * @return {@code List<T>} class response
     */
    public List<T> getResponse() {
        return response;
    }

    /**
     * Response setter
     *
     * @param response response to be set
     */
    public void setResponse(List<T> response) {
        this.response = response;
    }

    /**
     * Empty attribute getter
     *
     * @return true if the message received is null, false otherwise
     */
    public boolean isEmpty() {
        return empty;
    }

    /**
     * Empty attribute setter
     *
     * @param value to set empty attribute
     */
    public void setEmpty(boolean value) {
        empty = value;
    }

    /**
     * Message attribute getter
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Message attribute setter
     *
     * @param message message to be set
     */
    public void setMessage(String message) {
        this.message = message;
        if (empty) {
            empty = false;
        }
    }
    /**
     * Default ToString method
     *
     * @return printable {@code String} Object
     */
    @Override
    public String toString() {
        return "Response{" + "response=" + response + '}';
    }
}
