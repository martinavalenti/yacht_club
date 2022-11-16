package it.unipr.client.controllers;

import it.unipr.client.Client;
import it.unipr.classes.*;
import it.unipr.client.main.Main;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static it.unipr.constants.Constants.*;

/**
 * The {@code MemberInterfaceController} class is the controller for the Member Interface.
 * Allows members to interact with the system.
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public class MemberInterfaceController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button account_btn, boats_btn, races_btn, creditCards_btn;
    @FXML
    private Label navBar_username;
    // Race Payment Pane
    @FXML
    private Pane racePayment_pane;
    @FXML
    private Label raceName_label, raceDate_label, racePrice_label, raceIban_label;

    @FXML
    private ChoiceBox<String> boat_choiceBox, payment_choiceBox;

    // Race Pane
    @FXML
    private Pane race_pane;
    @FXML
    private TableView raceTable;
    @FXML
    private TableColumn name_column, date_column, entryFee_column;
    @FXML
    private Label race_label;
    @FXML
    private Button switchTable_button, enroll_button;

    // Account Pane
    @FXML
    private Pane account_pane;
    @FXML
    private Label fullName_label, taxCode_label, username_label;
    @FXML
    private Label city_label, address_label, postalCode_label, country_label;

    // Boat Payment Pane
    @FXML
    private Pane boatPayment_pane;
    @FXML
    private Label boatName_label, boatPrice_label, boatLength_label, boatIban_label;

    @FXML
    private ChoiceBox<String> boatPayment_cb;
    @FXML
    private HBox raceIban_box, boatIban_box;

    // Add Boat pane
    @FXML
    private Pane addBoat_pane;
    @FXML
    private Button addBoat_btn;
    @FXML
    private TextField boatName_textField, boatLength_textField;
    @FXML
    private Label newBoatPrice_label;

    // Boats Pane
    @FXML
    private Pane boats_pane;
    @FXML
    private ScrollPane boats_scrollPane;
    @FXML
    private GridPane boats_gridPane;

    // Add Credit Card pane
    @FXML
    private Pane addCreditCard_pane;
    @FXML
    private Button addCreditCard_btn;
    @FXML
    private TextField cardNum_textField, cvv_textField, owner_textField, expirDate_textField;

    // Credit Cards Pane
    @FXML
    private Pane creditCards_pane;
    @FXML
    private GridPane creditCards_gridPane;

    // Notifications Pane
    @FXML
    private Pane notification_pane;
    @FXML
    private GridPane notif_gridPane;

    // Annual Fee Payment Pane
    @FXML
    private Pane annualFeePayment_pane;
    @FXML
    private ChoiceBox<String> annualFeePay_cb;
    @FXML
    private HBox annualFeeIban_box;
    @FXML
    private Label annualFeeIban_label, annualFeePrice_label;

    // Storage Fee Payment Pane
    @FXML
    private Pane storageFeePayment_pane;
    @FXML
    private ChoiceBox<String> storageFee_cb;
    @FXML
    private HBox storageFeeIban_box;
    @FXML
    private Label storageFeeIban_label, storageFeeBoat_label , storageFeePrice_label;

    private double x, y;
    private Member member;
    private Client client;
    private Race userRegRace;
    private Boat boatToUpdate;
    private CardListener listener;
    private Boolean showNotif;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        makeDragable();
        client = new Client();
        this.listener = this.setListener();
        this.showNotif = true;
        setRaceTable();
    }

    private void setRaceTable() {
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        date_column.setCellValueFactory(new PropertyValueFactory<>("date"));
        entryFee_column.setCellValueFactory(new PropertyValueFactory<>("entryFee"));
    }

    /**
     * Method used to set member data received from {@code SignController}
     *
     * @param member {@code Member} object created during Sign In
     * @see Member
     */
    public void setMember(Member member) {
        this.member = new Member(member);
        this.member.setAddress(queryGetAddress());
        this.member.setBoats(queryGetBoat());
        this.member.setCreditCards(queryGetCreditCard());
        this.member.setRaces(queryGetRaceReg());

        this.navBar_username.setText(this.member.getUsername());
        this.username_label.setText(this.member.getUsername());
        this.fullName_label.setText(this.member.getFullName());
        this.taxCode_label.setText(this.member.getTaxCode());


        this.address_label.setText(this.member.getAddress().getAddress());
        this.city_label.setText(this.member.getAddress().getCity());
        this.country_label.setText(this.member.getAddress().getCountry());
        this.postalCode_label.setText(this.member.getAddress().getPostalCode());

        validateAnnualFee();
    }

    private CardListener setListener() {
        return new CardListener() {
            @Override
            public void deleteBoat(Boat b) {
                showDeleteBoat(b);
            }

            @Override
            public void deleteCreditCard(CreditCard c) { showDeleteCard(c); }

            @Override
            public void closeNotification(Notification n) {
                deleteNotification(n);
            }

            @Override
            public void payStorageFee(Boat b) { showPayStorageFee_pane(b);}

        };
    }

    private void deleteNotification(Notification n) {
        queryDeleteNotification(n);
        this.member.deleteNotification(n);
        if(this.member.getNotifications().isEmpty()){
            showNotif = !showNotif;
            notification_pane.setVisible(false);
        } else {
            showNotificationCards();
        }
    }

    private void showDeleteCard(CreditCard c) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        alert.setTitle("ERROR");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.setContentText("Are you sure you want to delete credit card: " + c.getNumber()+ " ?");
        alert.showAndWait();

        if(alert.getResult()== ButtonType.OK){
            queryDeleteCrediCard(c.getNumber());
            this.member.deleteCreditCard(c);
            setCreditCardPane();
        }
    }

    private void showDeleteBoat(Boat b) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        alert.setTitle("ERROR");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.setContentText("Are you sure you want to delete " + b.getName()+ " ?");
        alert.showAndWait();

        if(alert.getResult()== ButtonType.OK){
            queryDeleteBoat(b.getId());
            this.member.deleteBoat(b);
            setBoatsPane();
        }
    }

    private Address queryGetAddress() {
        List<Address> response = (List<Address>) client.connect(GET_ADDRESS, this.member.getAddressId());
        if (!response.isEmpty()) {
            return response.get(0);
        }
        return null;
    }
    private List<Race> queryGetRaceReg() {
        System.out.println("race");
        return (List<Race>) client.connect(GET_REG_RACES, member.getUsername() + SEPARATOR + LocalDateTime.now().toString());
    }

    private List<CreditCard> queryGetCreditCard() {
        System.out.println("cred card");
        return (List<CreditCard>) client.connect(GET_CARDS, member.getUsername());
    }

    private List<Boat> queryGetBoat() {
        return (List<Boat>) client.connect(GET_BOATS, member.getUsername());
    }

    private List<Race> queryGetNotRegRace() {
        return (List<Race>) client.connect(GET_NOT_REG_RACES, member.getUsername() + SEPARATOR + LocalDateTime.now().toString());
    }
    private String queryRegisterToRace(Boat boat) {
        return ((List<String>) client.connect(REGISTER_TO_RACE, userRegRace.getId() + SEPARATOR + member.getUsername() + SEPARATOR + boat.getId())).get(0);
    }
    private void queryInsertCreditcard(CreditCard creditCard) {
        client.connect(INSERT_CARD, creditCard.getNumber() + SEPARATOR + creditCard.getOwner() + SEPARATOR + creditCard.getCvc() + SEPARATOR + creditCard.getExpirationDate() + SEPARATOR + member.getUsername());
    }
    private void queryInsertPayment(Payment payment) {
        client.connect(INSERT_PAYMENT, payment.getReason() + SEPARATOR + payment.getType() + SEPARATOR + payment.getAmount() + SEPARATOR + payment.getDate() + SEPARATOR + member.getUsername());
    }

    private void queryDeleteNotification(Notification n) {
        client.connect(DELETE_NOTIFICATION, n.getId());
    }

    private void queryUpdateBoatStorage(Boat b) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date now = formatter.parse(String.valueOf(LocalDateTime.now()));
            Calendar c = Calendar.getInstance();
            c.setTime(now);
            c.add(Calendar.YEAR, 1);
            Date nextYear = c.getTime();
            String storageFeeExpiryDate = formatter.format(nextYear);
            client.connect(UPDATE_BOAT, storageFeeExpiryDate + SEPARATOR + b.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Notification> queryGetNotifications() {
        return (List<Notification>) client.connect(GET_NOTIFICATIONS, member.getUsername());
    }
    private void queryInsertNewBoat(Boat b) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date now = formatter.parse(String.valueOf(LocalDateTime.now()));
            Calendar c = Calendar.getInstance();
            c.setTime(now);
            c.add(Calendar.YEAR, 1);
            Date nextYear = c.getTime();
            String feeExpiryDate = formatter.format(nextYear);
            client.connect(INSERT_BOAT, b.getName() + SEPARATOR + b.getLength() + SEPARATOR  + member.getUsername() + SEPARATOR + feeExpiryDate);
        } catch (Exception ignored) {
        }
    }

    private void queryDeleteBoat(String id) {
        client.connect(DELETE_BOAT, id);
    }
    private void queryDeleteCrediCard(String number) {
        client.connect(DELETE_CARD, number);
    }

    private void queryUpdateMemberAnnualFee() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date now = sdf.parse(String.valueOf(LocalDateTime.now()));
            Calendar c = Calendar.getInstance();
            c.setTime(now);
            c.add(Calendar.YEAR, 1);
            Date nextYear = c.getTime();
            String nextYearDate = sdf.format(nextYear);
            client.connect(UPDATE_MEMBER_FEE, nextYearDate + SEPARATOR + member.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void validateAnnualFee() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date now = sdf.parse(LocalDate.now().toString());
            Date memberAnnualFee = sdf.parse(this.member.getAnnual_fee());
            if (memberAnnualFee.before(now)) {
                annualFeePrice_label.setText(ANNUAL_FEE + " €");
                account_btn.setDisable(true);
                races_btn.setDisable(true);
                boats_btn.setDisable(true);
                creditCards_btn.setDisable(true);
                account_pane.setVisible(false);
                annualFeePayment_pane.setVisible(true);

                annualFeePay_cb.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    try{
                        if(newValue.equals("Bank Transfer")){
                                    annualFeeIban_box.setVisible(true);
                        }
                        else{
                            annualFeeIban_box.setVisible(false);
                        }
                    } catch (Exception ignore){

                    }

                });
                annualFeePay_cb.getItems().clear();
                annualFeePay_cb.getItems().add("Bank Transfer");
                for (CreditCard c : this.member.getCreditCards()) {
                    annualFeePay_cb.getItems().add(c.getNumber());
                }
                annualFeeIban_label.setText(UUID.randomUUID().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    private void showBoatCards() {
        int column = 0, row = 0, minHeight = 459;
        this.boats_gridPane.getChildren().clear();
        this.boats_gridPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        for (Boat b : this.member.getBoats()) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("BoatCard.fxml"));
                AnchorPane pane = fxmlLoader.load();
                BoatCardController itemController = fxmlLoader.getController();
                itemController.setBoat(b, this.listener);
                if (column == 2) {
                    column = 0;
                    row++;
                }
                boats_gridPane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(20));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        boats_gridPane.setMinWidth(562);
        boats_gridPane.setMinHeight(minHeight);
    }

    private void showCreditcardCards() {
        int column = 0, row = 0, minHeight = 459;
        this.creditCards_gridPane.getChildren().clear();
        this.creditCards_gridPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        for (CreditCard c : this.member.getCreditCards()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CreditCard.fxml"));
                AnchorPane pane = fxmlLoader.load();
                CreditCardController controller = fxmlLoader.getController();
                controller.setData(c, this.listener);
                if (column == 2) {
                    column = 0;
                    row++;
                }
                creditCards_gridPane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(20));
            } catch (IOException ignored) {
            }
        }
        creditCards_gridPane.setMinWidth(562);
        creditCards_gridPane.setMinHeight(minHeight);
    }


    @FXML
    private void showAccount(ActionEvent actionEvent) {
        addBoat_pane.setVisible(false);
        addCreditCard_pane.setVisible(false);
        creditCards_pane.setVisible(false);
        boats_pane.setVisible(false);
        notification_pane.setVisible(false);
        boatPayment_pane.setVisible(false);
        racePayment_pane.setVisible(false);
        race_pane.setVisible(false);
        addCreditCard_btn.setVisible(false);
        addBoat_btn.setVisible(false);
        account_pane.setVisible(true);
    }

    private void setBoatsPane() {
        addBoat_pane.setVisible(false);
        addCreditCard_pane.setVisible(false);
        storageFeePayment_pane.setVisible(false);
        creditCards_pane.setVisible(false);
        boatPayment_pane.setVisible(false);
        notification_pane.setVisible(false);
        racePayment_pane.setVisible(false);
        race_pane.setVisible(false);
        account_pane.setVisible(false);
        addCreditCard_btn.setVisible(false);
        addBoat_btn.setVisible(true);
        boats_pane.setVisible(true);
        this.member.setBoats(queryGetBoat());
        showBoatCards();
    }
    @FXML
    private void showBoats(ActionEvent actionEvent) {
        this.member.setBoats(queryGetBoat());
        setBoatsPane();
    }

    private void setRacesPane(){
        addBoat_pane.setVisible(false);
        addCreditCard_pane.setVisible(false);
        creditCards_pane.setVisible(false);
        notification_pane.setVisible(false);
        boatPayment_pane.setVisible(false);
        account_pane.setVisible(false);
        boats_pane.setVisible(false);
        racePayment_pane.setVisible(false);
        addCreditCard_btn.setVisible(false);
        addBoat_btn.setVisible(false);
        setTableAllRaces();
        race_pane.setVisible(true);
    }
    @FXML
    private void showRaces(ActionEvent actionEvent) {
        setRacesPane();
    }

    private void setTableAllRaces() {
        raceTable.getItems().clear();
        for (Race r : this.queryGetNotRegRace())
            raceTable.getItems().add(r);
    }

    private void setTableRegRaces() {
        raceTable.getItems().clear();
        for (Race r : this.queryGetRaceReg())
            raceTable.getItems().add(r);
    }

    private void setCreditCardPane(){
        addBoat_pane.setVisible(false);
        addCreditCard_pane.setVisible(false);
        boatPayment_pane.setVisible(false);
        account_pane.setVisible(false);
        notification_pane.setVisible(false);
        boats_pane.setVisible(false);
        racePayment_pane.setVisible(false);
        addBoat_btn.setVisible(false);
        addCreditCard_btn.setVisible(true);
        race_pane.setVisible(false);
        creditCards_pane.setVisible(true);
        this.member.setCreditCards(queryGetCreditCard());
        showCreditcardCards();
    }

    private void showPayStorageFee_pane(Boat b) {
        this.boatToUpdate = new Boat(b);
        storageFeeBoat_label.setText(b.getName());
        Double price = STORAGE_FEE * b.getLength();
        storageFeePrice_label.setText(Double.toString(price)+" €");
        storageFee_cb.getItems().clear();
        storageFee_cb.getItems().add("Bank Transfer");

        for (CreditCard c : this.member.getCreditCards()){
            storageFee_cb.getItems().add(c.getNumber());
        }
        storageFeeIban_label.setText(UUID.randomUUID().toString());

        storageFee_cb.getSelectionModel().selectedItemProperty().addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            try{
                if(newValue.equals("Bank Transfer")){
                    storageFeeIban_box.setVisible(true);
                }
                else{
                    storageFeeIban_box.setVisible(false);
                }
            } catch (Exception ignore){
            }
        });

        queryUpdateBoatStorage(boatToUpdate);
        boats_pane.setVisible(false);
        storageFeePayment_pane.setVisible(true);
    }

    private void showBoatPayment(){
        addBoat_pane.setVisible(false);
        addCreditCard_pane.setVisible(false);
        account_pane.setVisible(false);
        boats_pane.setVisible(false);
        notification_pane.setVisible(false);
        racePayment_pane.setVisible(false);
        addCreditCard_btn.setVisible(false);
        addBoat_btn.setVisible(false);
        race_pane.setVisible(false);
        creditCards_pane.setVisible(false);

        boatName_label.setText(boatName_textField.getText());
        boatLength_label.setText(boatLength_textField.getText());
        boatPrice_label.setText(newBoatPrice_label.getText());
        boatPayment_cb.getItems().clear();
        boatPayment_cb.getItems().add("Bank Transfer");

        for (CreditCard c : this.member.getCreditCards()){
            boatPayment_cb.getItems().add(c.getNumber());
        }
        boatIban_label.setText(UUID.randomUUID().toString());

        boatPayment_cb.getSelectionModel()
                .selectedItemProperty()
                .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    try{
                        if(newValue.equals("Bank Transfer")){
                            boatIban_box.setVisible(true);
                        }
                        else{
                            boatIban_box.setVisible(false);
                        }
                    } catch (Exception ignore){

                    }

                } );
        boatPayment_pane.setVisible(true);
    }
    @FXML
    private void showCreditCards(ActionEvent actionEvent) {
        setCreditCardPane();
    }


    @FXML
    private void showNotification(ActionEvent actionEvent) {
        if(showNotif){
            showNotif = !showNotif;
            this.member.setNotifications(queryGetNotifications());
            notification_pane.setVisible(true);
            showNotificationCards();
        }
        else{
            showNotif = !showNotif;
            this.member.setNotifications(queryGetNotifications());
            notification_pane.setVisible(false);
        }
    }

    private void showNotificationCards() {
        if(this.member.getNotifications().isEmpty()){
            this.notif_gridPane.getChildren().clear();
            showNotif = !showNotif;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("INFORMATION");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("there are no new notifications.");
            alert.showAndWait();
            notification_pane.setVisible(false);
            return;
        }
        int column = 0, row = 0, minHeight = 430;
        this.notif_gridPane.getChildren().clear();
        this.notif_gridPane.setHgap(10);
        this.notif_gridPane.setVgap(10);
        this.notif_gridPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        for (Notification n : this.member.getNotifications()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NotificationCard.fxml"));
                AnchorPane pane = fxmlLoader.load();
                NotificationController controller = fxmlLoader.getController();
                controller.setNotification(n, this.listener);
                if (column == 2) {
                    column = 0;
                    row++;
                }
                notif_gridPane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(20));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        notif_gridPane.setMinWidth(530);
        notif_gridPane.setMinHeight(minHeight);
    }

    @FXML
    private void logout(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("SignForm.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);

            Stage stage = (Stage) navBar_username.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sign Form");
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void enrollToRace(ActionEvent actionEvent) {
        if(payment_choiceBox.getValue()==null||boat_choiceBox.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Please select a boat and a type of payment.");
            alert.showAndWait();
            return;
        }
        Boat boat = new Boat();
        for (Boat b : this.member.getBoats())
            if (b.getName().equals(boat_choiceBox.getSelectionModel().getSelectedItem()))
                boat = new Boat(b);
        if (queryRegisterToRace(boat).equals(SUCCESS)) {
            String type;
            if (payment_choiceBox.getSelectionModel().getSelectedItem().equals("Bank Transfer")){
                type = "Bank transfer";
            } else {
                type = "Credit Card";
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            queryInsertPayment(new Payment(MOTIVE_RACE, type, Double.parseDouble(userRegRace.getEntryFee()) , dtf.format(now) , member.getUsername()));
            this.member.addRegRace(userRegRace);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("SUCCESS");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Successfully registered for race " + userRegRace.getName() + ".");
            alert.showAndWait();
            setRacesPane();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Something went wrong during your registration for the race.");
            alert.showAndWait();
        }

    }

    @FXML
    private void getSelectedRace(MouseEvent mouseEvent) {
    }

    @FXML
    private void switchTable(ActionEvent actionEvent) {
        if(race_label.getText().equals(YOUR_RACES)){
            setTableAllRaces();
            race_label.setText(AVAIL_RACES);
            enroll_button.setVisible(true);
            switchTable_button.setText(MY_RACES);

        } else if(race_label.getText().equals(AVAIL_RACES)) {
            setTableRegRaces();
            race_label.setText(YOUR_RACES);
            enroll_button.setVisible(false);
            switchTable_button.setText(ALL_RACES);
        }
    }

    @FXML
    private void enroll(ActionEvent actionEvent) {

        if (this.member.getBoats().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("No boats to enroll for the race.");
            alert.showAndWait();
            return;
        }

        payment_choiceBox.getSelectionModel().selectedItemProperty().addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            try{
                if(newValue.equals("Bank Transfer")){
                    raceIban_box.setVisible(true);
                }
                else{
                    raceIban_box.setVisible(false);
                }
            } catch (Exception ignore){

            }
        });

        if(raceTable.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Please select a race.");
            alert.showAndWait();
            return;
        }

        addBoat_pane.setVisible(false);
        addCreditCard_pane.setVisible(false);
        boatPayment_pane.setVisible(false);
        account_pane.setVisible(false);
        boats_pane.setVisible(false);
        race_pane.setVisible(false);
        creditCards_pane.setVisible(false);
        racePayment_pane.setVisible(true);

        userRegRace = (Race) raceTable.getSelectionModel().getSelectedItem();
        raceName_label.setText(userRegRace.getName());
        raceDate_label.setText(userRegRace.getDate());
        racePrice_label.setText(userRegRace.getEntryFee());
        boat_choiceBox.getItems().clear();
        for (Boat b : this.member.getBoats()) {
            boat_choiceBox.getItems().add(b.getName());
        }
        boat_choiceBox.setValue(this.member.getBoats().get(0).getName());
        payment_choiceBox.getItems().clear();
        payment_choiceBox.getItems().add("Bank Transfer");
        for (CreditCard c : this.member.getCreditCards()) {
            payment_choiceBox.getItems().add(c.getNumber());
        }
        raceIban_label.setText(UUID.randomUUID().toString());

    }

    @FXML
    private void payBoat(ActionEvent actionEvent) {
        if(boatPayment_cb.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Please select a type of payment.");
            alert.showAndWait();
            return;
        }
        String name = boatName_label.getText();
        double length = Double.parseDouble(boatLength_label.getText());

        try {
            length = Double.parseDouble(boatLength_textField.getText());

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("INPUT ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Please select a valid length value.");
            alert.showAndWait();
            return;
        }

        Boat b = new Boat("", name, Double.parseDouble(boatLength_label.getText()), this.member.getUsername());
        this.member.addBoat(b);
        queryInsertNewBoat(b);
        String type;
        if (boatPayment_cb.getSelectionModel().getSelectedItem().equals("Bank Transfer")){
            type = "Bank transfer";
        } else {
            type = "Credit Card";
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        queryInsertPayment(new Payment(MOTIVE_BOAT, type, Double.parseDouble(boatPrice_label.getText()) , dtf.format(now), member.getUsername()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        alert.setTitle("SUCCESS");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.setContentText("New boat successfully registered.");
        alert.showAndWait();
        setBoatsPane();
    }


    @FXML
    private void backToBoatPane(ActionEvent actionEvent) {
        boatPayment_pane.setVisible(false);
        addBoat_btn.setVisible(true);
        boats_pane.setVisible(true);
    }


    @FXML
    private void backToCreditCard(ActionEvent actionEvent) {
        setCreditCardPane();

    }

    @FXML
    private void backToBoatRaces(ActionEvent actionEvent) {
        setRacesPane();
    }

    @FXML
    private void confirmNewCreditCard(ActionEvent actionEvent) {
        if (cardNum_textField.getText().isBlank() || cvv_textField.getText().isEmpty() || owner_textField.getText().isEmpty()|| expirDate_textField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("INPUT ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Please complete all field.");
            alert.showAndWait();
        }
        else if (!expirDate_textField.getText().matches("^(0[1-9]|1[0-2])\\/?([0-9]{2})$" )){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("INPUT ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Invalid expiration date format.");
            alert.showAndWait();
        }
        else if (!cvv_textField.getText().matches("[0-9]{3,4}$" )){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("INPUT ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Invalid CVV format.");
            alert.showAndWait();
        }
        else {
            String number = cardNum_textField.getText();
            String owner = owner_textField.getText();
            String date = expirDate_textField.getText();
            String cvc = cvv_textField.getText();

            CreditCard creditCard = new CreditCard(number, owner, date, cvc);
            this.member.addCreditCard(creditCard);
            queryInsertCreditcard(creditCard);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("SUCCESS");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("New Credit Card successfully registered.");
            alert.showAndWait();
            setCreditCardPane();
        }

    }

    @FXML
    private void confirmNewBoat(ActionEvent actionEvent) {
        if (boatName_textField.getText().isBlank() || boatName_textField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("INPUT ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Please complete the boat name field.");
            alert.showAndWait();
            return;
        }
        double length = 0;
        double price = 0;
        try {
            length = Double.parseDouble(boatLength_textField.getText());
            price = Double.parseDouble(newBoatPrice_label.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("INPUT ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Please select a valid length value.");
            alert.showAndWait();
            return;
        }
        if (length > 0) {
            showBoatPayment();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("INPUT ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Please select a valid length value.");
            alert.showAndWait();
        }
    }

    @FXML
    private void showAddBoatPane(ActionEvent actionEvent) {
        addCreditCard_pane.setVisible(false);
        creditCards_pane.setVisible(false);
        boatPayment_pane.setVisible(false);
        account_pane.setVisible(false);
        boats_pane.setVisible(false);
        racePayment_pane.setVisible(false);
        addCreditCard_btn.setVisible(false);
        addBoat_btn.setVisible(false);
        race_pane.setVisible(false);
        //this.newBoatPaid = false;
        boatLength_textField.setText("");
        boatName_textField.setText("");
        newBoatPrice_label.setText("");
        addBoat_pane.setVisible(true);
        boatLength_textField.textProperty().addListener((obs, oldText, newText) -> {
            if(!newText.isBlank()) {
                try {
                    Double price = 40 * Double.parseDouble(newText);
                    newBoatPrice_label.setText(Double.toString(price));
                } catch (Exception e){

                }
            }
        });
    }

    @FXML
    private void showAddCreditCardPane(ActionEvent actionEvent) {
        creditCards_pane.setVisible(false);
        boatPayment_pane.setVisible(false);
        account_pane.setVisible(false);
        boats_pane.setVisible(false);
        racePayment_pane.setVisible(false);
        addCreditCard_btn.setVisible(false);
        addBoat_btn.setVisible(false);
        race_pane.setVisible(false);
        addBoat_pane.setVisible(false);
        cardNum_textField.setText("");
        cvv_textField.setText("");
        owner_textField.setText("");
        expirDate_textField.setText("");
        addCreditCard_pane.setVisible(true);
    }

    @FXML
    private void payAnnualFee(ActionEvent actionEvent) {
        if(annualFeePay_cb.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Please select a type of payment.");
            alert.showAndWait();
            return;
        }
        String type;
        if (annualFeePay_cb.getSelectionModel().getSelectedItem().equals("Bank Transfer")){
            type = "Bank transfer";
        } else {
            type = "Credit Card";
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        queryInsertPayment(new Payment(MOTIVE_FEE, type, ANNUAL_FEE , dtf.format(now) , member.getUsername()));
        queryUpdateMemberAnnualFee();
        account_btn.setDisable(false);
        races_btn.setDisable(false);
        boats_btn.setDisable(false);
        creditCards_btn.setDisable(false);
        annualFeePayment_pane.setVisible(false);
        annualFeePayment_pane.setVisible(false);
        account_pane.setVisible(true);
    }

    @FXML
    private void backToBoats(ActionEvent actionEvent) {
        setBoatsPane();
    }

    @FXML
    private void payStorageFee(ActionEvent actionEvent) {
        if(storageFee_cb.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Please select a type of payment.");
            alert.showAndWait();
            return;
        }
        String type;
        if (storageFee_cb.getSelectionModel().getSelectedItem().equals("Bank Transfer")){
            type = "Bank transfer";
        } else {
            type = "Credit Card";
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        Double price = Double.parseDouble(storageFeePrice_label.getText());
        queryInsertPayment(new Payment(MOTIVE_BOAT, type, price, dtf.format(now) , member.getUsername()));

        queryUpdateBoatStorage(this.boatToUpdate);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        alert.setTitle("SUCCESS");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.setContentText("Successful payment of the storage fee for boat" + boatToUpdate.getName() + ".");
        alert.showAndWait();
        setBoatsPane();
    }
}
