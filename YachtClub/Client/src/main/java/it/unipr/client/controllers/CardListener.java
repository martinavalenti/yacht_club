package it.unipr.client.controllers;

import it.unipr.classes.Boat;
import it.unipr.classes.CreditCard;
import it.unipr.classes.Notification;

/**
 * The {@code CardListener} interface defines abstract methods
 * to control boat-card, creditCard-card and notification-card elements.
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public interface CardListener {
    /**
     * Method which deletes selected boat
     *
     * @param b instance of {@code Boat} class to delete
     */
    void deleteBoat(Boat b);

    /**
     * Method which deletes selected credit card
     *
     * @param c {@code CreditCard} to delete
     */
    void deleteCreditCard(CreditCard c);

    /**
     * Method which allows to pay annual storage fee for the selected boat
     *
     * @param b {@code Boat} for which to pay the storage fee
     */
    void payStorageFee(Boat b);

    /**
     * Method which allows to close and delete notification
     *
     * @param n {@code Notification} to close and delete
     */
    void closeNotification(Notification n);
}
