/**
 * Module containing all the packages needed
 * to run and manage the {@code Client}
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
module Client {
    requires javafx.controls;
    requires javafx.fxml;
    requires YachtClub;

    exports it.unipr.client.main;
    opens it.unipr.client.main to javafx.fxml;
    exports it.unipr.client.controllers;
    opens it.unipr.client.controllers to javafx.fxml;
}