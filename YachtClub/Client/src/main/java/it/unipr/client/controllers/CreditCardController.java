package it.unipr.client.controllers;

import it.unipr.classes.CreditCard;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * The {@code CreditCardController} class is the controller
 * for the notification card element.
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public class CreditCardController {
    @FXML
    private Label cardNumber, cvc, expirationDate, owner;
    private CreditCard creditcard;
    private CardListener listener;

    /**
     * Method that sets the data to be shown in the CreditCard card element
     *
     * @param c {@code CreditCard} object
     * @param listener {@code CardListener} object
     * @since 1.0
     */
    public void setData(CreditCard c, CardListener listener) {
        this.listener = listener;
        this.creditcard = c;
        this.cardNumber.setText(c.getNumber());
        this.owner.setText(c.getOwner());
        this.cvc.setText(c.getCvc());
        this.expirationDate.setText(c.getExpirationDate());
    }


    public void deleteCard(MouseEvent mouseEvent) {
        this.listener.deleteCreditCard(this.creditcard);
    }
}
