/**
 * Module containing all the packages needed
 * to run and manage the {@code Server}
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
module Server {
    requires javafx.controls;
    requires javafx.fxml;
    requires YachtClub;
    requires java.sql;
    requires mysql.connector.j;

    opens it.unipr.server.main to javafx.fxml;
    exports it.unipr.server.main;
    opens it.unipr.server.controllers to javafx.fxml;
    exports it.unipr.server.controllers;
}