package it.unipr.classes;

import java.io.Serializable;
import java.util.HashMap;

/**
 * The {@code Notification} class is used to store
 * and manage all the information related to a notification.
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public class Notification implements Serializable {
    private String id;
    private String content;
    private String user;

    /**
     * {@code Notification} class constructor
     *
     * @param content notification text message
     * @param user user to whom to send the notification
     */
    public Notification(String content, String user) {
        this.content = content;
        this.user = user;
    }

    /**
     * {@code HasMap<String, String>} class constructor
     *
     * @param values {@code HashMap<String, String>} contains all attributes values to create the {@code Notification} object
     */
    public Notification(HashMap<String, String> values) {
        this.id = values.get("id");
        this.content = values.get("content");
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
     * Content getter
     *
     * @return {@code String} content
     */
    public String getContent() {
        return content;
    }

    /**
     * Content setter
     *
     * @param content {@code String} content message to set
     */
    public void setContent(String content) {
        this.content = content;
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
        return "Notification{" +
                "content='" + content + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
