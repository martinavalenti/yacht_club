package it.unipr.server.controllers;

import it.unipr.server.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

import static it.unipr.constants.Constants.*;

/**
 * The {@code ServerLoginController} class is the controller for the admin login scene.
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public class ServerLoginController implements Initializable {
    @FXML
    private Label IPaddress, port, error_label;
    @FXML
    private Pane serverPane, signInPane;
    @FXML
    private TextField username_field;
    @FXML
    private PasswordField psw_field;
    @FXML
    private AnchorPane anchorPane;
    private Server server;
    private double x, y;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeDragable();
    }
    @FXML
    private void start(ActionEvent actionEvent) {

        if(!username_field.getText().isBlank() && !psw_field.getText().isBlank()){
            String username = this.username_field.getText();
            String psw = this.psw_field.getText();
            Integer check = checkAdmin(username, psw);
            if (check == 1) {
                try {
                    this.server = new Server();
                    this.IPaddress.setText(this.server.getIPadress());
                    this.port.setText(String.valueOf(SERVER_PORT));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                serverPane.setVisible(true);
            } else if (check != -1) {
                this.error_label.setText("Wrong admin credentials.");
                this.psw_field.setText("");
            }
        }
        else{
            this.error_label.setText("Please complete all fields.");
        }


    }
    @FXML
    private void close(MouseEvent mouseEvent) {
        try {
            this.server.close();
        } catch (Exception ignored) {
        }
        System.exit(0);
    }
    @FXML
    private void stopServer(ActionEvent actionEvent) {
        serverPane.setVisible(false);
        this.username_field.setText("");
        this.psw_field.setText("");
        try {
            this.server.close();
        } catch (Exception ignored) {
        }
    }

    private void makeDragable() {
        anchorPane.setOnMousePressed(((event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        }));

        anchorPane.setOnMouseDragged(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.8f);
        }));

        anchorPane.setOnDragDone(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));

        anchorPane.setOnMouseReleased(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));
    }
    private int checkAdmin(String user, String psw) {
        Connection conn = null;
        Statement stmt = null;
        int result = 0;
        String query = ("SELECT COUNT(*) FROM admin WHERE username='%s' and password='%s' ").formatted(user, psw);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL + "?user=" + DB_USERNAME + "&password=" + DB_PASSWORD);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
                result = rs.getInt(1);
        } catch (ClassNotFoundException | SQLException e) {
            this.error_label.setText("CONNECTION ERROR: Could not connect to server.");
            this.psw_field.setText("");
            return -1;
        }
        finally {
            try {
                Objects.requireNonNull(stmt).close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                this.error_label.setText("CONNECTION ERROR: Could not connect to server.!");
                this.psw_field.setText("");
                return -1;
            }
            try {
                Objects.requireNonNull(conn).close();
            } catch (SQLException ignored) {
            } catch (NullPointerException e) {
                this.error_label.setText("CONNECTION ERROR: Could not connect to server.");
                this.psw_field.setText("");
                return -1;
            }
            return result;
        }
    }

    @FXML
    private void clearError(MouseEvent mouseEvent) {
        error_label.setText("");
    }
}