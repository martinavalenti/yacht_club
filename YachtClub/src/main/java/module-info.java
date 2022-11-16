/**
 * Module containing classes and constants
 * used throwout the system,
 * both in {@code Server} and {@code Client} modules.
 */
module YachtClub {
    requires javafx.controls;
    requires javafx.fxml;

    exports it.unipr.constants;
    exports it.unipr.connection;
    exports it.unipr.classes;
}