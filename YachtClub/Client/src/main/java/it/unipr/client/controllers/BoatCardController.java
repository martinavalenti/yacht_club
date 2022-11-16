package it.unipr.client.controllers;

import it.unipr.classes.Boat;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * The {@code BoatCardController} class is the controller
 * for the boat card element.
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public class BoatCardController {
    @FXML
    private Label boatName, boatLength, expirDate;
    private Boat boat;
    private CardListener listener;

    /**
     * Method that sets the data to be shown in the boat card element
     *
     * @param b {@code Boat} object
     * @param listener {@code CardListener} object
     */
    public void setBoat(Boat b, CardListener listener) {
        this.listener = listener;
        this.boat = b;
        this.boatName.setText(b.getName());
        this.boatLength.setText(String.valueOf(b.getLength()));
        this.expirDate.setText(b.getExpiryString());
    }

    @FXML
    private void deleteBoat(MouseEvent mouseEvent) {
        this.listener.deleteBoat(this.boat);
    }

    @FXML
    private void payStorageFee(MouseEvent mouseEvent) { this.listener.payStorageFee(this.boat); }
}
