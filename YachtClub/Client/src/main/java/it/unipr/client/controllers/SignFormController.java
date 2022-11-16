package it.unipr.client.controllers;

import it.unipr.client.Client;
import it.unipr.classes.Member;
import it.unipr.classes.Staff;
import it.unipr.client.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import static it.unipr.constants.Constants.*;

/**
 * The {@code SignFormController} class is the controller
 * for both the SignUp/SignIn scenes.
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public class SignFormController implements Initializable {
    @FXML
    private ChoiceBox<String> signInChoice_box;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private PasswordField pswSignIn_field, pswSignUp_field;
    @FXML
    private Label signInError_label, signUpError_label;
    @FXML
    private StackPane signUpPane;
    @FXML
    private TextField fullName_field, taxCode_field, usernameSignUp_field, usernameSignIn_field;
    @FXML
    private TextField city_field, country_field, postalCode_field, address_field;
    private String[] loginChoice = {"Member", "Staff"};
    private double x, y;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeDragable();
        this.signInChoice_box.getItems().addAll(this.loginChoice);
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

    @FXML
    private void setSignUpPane(ActionEvent actionEvent) {
        fullName_field.setText("");
        taxCode_field.setText("");
        usernameSignUp_field.setText("");
        pswSignUp_field.setText("");
        address_field.setText("");
        postalCode_field.setText("");
        city_field.setText("");
        country_field.setText("");
        signUpError_label.setText("");
        signUpPane.setVisible(true);
    }

    @FXML
    private void setLoginPane(ActionEvent actionEvent) {
        usernameSignIn_field.setText("");
        pswSignIn_field.setText("");
        signInError_label.setText("");
        signInChoice_box.setValue(null);
        signUpPane.setVisible(false);
    }

    @FXML
    private void clearError(MouseEvent mouseEvent) {
        signUpError_label.setText("");
        signInError_label.setText("");
    }

    @FXML
    private void close(MouseEvent mouseEvent) {
        System.exit(0);
    }

    @FXML
    private void signIn(ActionEvent actionEvent) {
        System.out.println(signInChoice_box.getValue());
        if(signInChoice_box.getValue()==null){
            signInError_label.setText(MISSING_ACCOUNT_TYPE);
        }
        //Check login choice value
        else if (signInChoice_box.getValue().equals("Member")) {
            //Check if fields are blank
            if (!usernameSignIn_field.getText().isBlank() && !pswSignIn_field.getText().isBlank()) {
                Client client = new Client();
                //Check if username and password are correct
                List<?> response = (List<?>) client.connect(MEMBER_LOGIN,
                                        usernameSignIn_field.getText() + SEPARATOR +
                                               pswSignIn_field.getText());

                if (!response.isEmpty() && !response.get(0).equals(FAIL) && !response.get(0).equals(DEFAULT) && !response.get(0).equals(NULL) && !response.get(0).equals(CONN_ERROR)) {
                    try{
                        FXMLLoader loader = new FXMLLoader(Main.class.getResource("MemberInterface.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        scene.setFill(Color.TRANSPARENT);

                        Stage stage = (Stage) usernameSignIn_field.getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("Member");
                        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("icons/logo.png"))));
                        MemberInterfaceController controller = loader.getController();
                        Member member = (Member) response.get(0);
                        controller.setMember(member);
                        stage.show();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                else if (!response.isEmpty() && response.get(0).equals(CONN_ERROR)) {
                    signInError_label.setText(CONNECTION_FAIL);
                }
                else {
                    signInError_label.setText(BAD_CREDENTIALS);
                }
            }
            else {
                signInError_label.setText(MISSING_FIELDS);
            }

        }
        else if(signInChoice_box.getValue().equals("Staff")){
            if (!usernameSignIn_field.getText().isBlank() && !pswSignIn_field.getText().isBlank()) {
                Client client = new Client();
                List<?> response = (List<?>) client.connect(STAFF_LOGIN,
                                   usernameSignIn_field.getText() + SEPARATOR +
                                          pswSignIn_field.getText());
                if (!response.isEmpty() && !response.get(0).equals(FAIL) && !response.get(0).equals(DEFAULT) && !response.get(0).equals(NULL) && !response.get(0).equals(CONN_ERROR)) {
                    try{
                        FXMLLoader loader = new FXMLLoader(Main.class.getResource("StaffInterface.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        scene.setFill(Color.TRANSPARENT);

                        Stage stage = (Stage) usernameSignIn_field.getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("Staff");
                        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("icons/logo.png"))));
                        StaffInterfaceController controller = loader.getController();
                        Staff staff = (Staff) response.get(0);
                        controller.setStaff(staff);
                        stage.show();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                } else if (!response.isEmpty() && response.get(0).equals(CONN_ERROR)) {
                    signInError_label.setText(CONNECTION_FAIL);
                }
                else {
                    signInError_label.setText(BAD_CREDENTIALS);
                }
            }
            else{
                signInError_label.setText(MISSING_FIELDS);
            }
        }
    }

    @FXML
    private void signUp(ActionEvent actionEvent) {
        if (!fullName_field.getText().isBlank() && !taxCode_field.getText().isBlank() &&
            !usernameSignUp_field.getText().isBlank() &&  !pswSignUp_field.getText().isBlank() &&
            !address_field.getText().isBlank() &&  !postalCode_field.getText().isBlank() &&
            !city_field.getText().isBlank() &&  !country_field.getText().isBlank() ) {
            Client client = new Client();
            //Checks if fiscalCode or username already exist
            List<?> response = (List<?>) client.connect(CHECK_SIGNUP, usernameSignUp_field.getText());
            if (Objects.equals(response.get(0), "0")) {
                //Tries to add the user to the database
                try{
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date now = formatter.parse(String.valueOf(LocalDateTime.now()));
                    Calendar c = Calendar.getInstance();
                    c.setTime(now);
                    c.add(Calendar.YEAR, 1);
                    Date nextYear = c.getTime();
                    String feeExpiryDate = formatter.format(nextYear);
                    response = (List<?>) client.connect(REGISTRATION, usernameSignUp_field.getText() + SEPARATOR +
                            pswSignUp_field.getText()+ SEPARATOR + fullName_field.getText() + SEPARATOR +
                            taxCode_field.getText() + SEPARATOR + feeExpiryDate + SEPARATOR + address_field.getText()
                            +SEPARATOR + city_field.getText()+ SEPARATOR + postalCode_field.getText() +SEPARATOR +
                            country_field.getText());
                } catch (Exception e){
                    e.printStackTrace();
                }
                if (Objects.equals(response.get(0), SUCCESS)) {
                    try{
                        Member member = ((List<Member>) client.connect(MEMBER_LOGIN, usernameSignUp_field.getText() + SEPARATOR + pswSignUp_field.getText())).get(0);
                        FXMLLoader loader = new FXMLLoader(Main.class.getResource("MemberInterface.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        scene.setFill(Color.TRANSPARENT);

                        Stage stage = (Stage) usernameSignIn_field.getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("Member");
                        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("icons/logo.png"))));
                        MemberInterfaceController controller = loader.getController();
                        controller.setMember(member);
                        stage.show();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(Main.class.getResource("styles.css").toExternalForm());
                    alert.initStyle(StageStyle.TRANSPARENT);
                    alert.setTitle("SIGNUP ERROR");
                    alert.setContentText("Error during signup.");
                    alert.showAndWait();
                }
            }
            else{
                signUpError_label.setText(TAKEN_USERNAME);
            }
        }
        else{
            signUpError_label.setText(MISSING_FIELDS);
        }
    }

}