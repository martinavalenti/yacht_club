package it.unipr.client.controllers;

import it.unipr.classes.Notification;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * The {@code NotificationController} class is the controller
 * for the notification card element.
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public class NotificationController {
    @FXML
    private Label notification_label;
    private CardListener listener;
    private Notification notification;

    /**
     * Method that sets the data to be shown in the notification card element
     *
     * @param n {@code Notification} object
     * @param listener {@code CardListener} object
     * @since 1.0
     */
    public void setNotification(Notification n, CardListener listener) {
        this.listener = listener;
        this.notification = n;
        this.notification_label.setText(n.getContent());
    }

    @FXML
    private void closeNotification(MouseEvent mouseEvent) { this.listener.closeNotification(this.notification); }
}
