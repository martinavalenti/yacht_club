package it.unipr.client.controllers;

import it.unipr.client.Client;
import it.unipr.classes.*;
import it.unipr.client.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import static it.unipr.constants.Constants.*;

/**
 * The {@code StaffInterfaceController} class is the controller for the Staff Interface.
 * Manages and controls all staff operation.
 *
 * @author Martina Valenti 308044
 * @version 1.0
 * @since 1.0
 */
public class StaffInterfaceController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button members_btn, boats_btn, races_btn, payments_btn;
    @FXML
    private Label navBar_username;

    // New Race Pane
    @FXML
    private Pane addNewRace_pane;
    @FXML
    private TextField raceDate_textField, raceName_textField, racePrice_textField;
    @FXML
    private Label newRaceError_label;

    // Race Pane
    @FXML
    private Pane races_pane;
    @FXML
    private TableView<Race> raceTable;
    @FXML
    private TableColumn raceName_column, raceDate_column, entryFee_column;
    @FXML
    private Label race_label;
    @FXML
    private Button switchRaces_btn;

    // Members Pane
    @FXML
    private Pane members_pane;
    @FXML
    private TableView<Member> membersTable;
    @FXML
    private TableColumn username_column, fullname_column, annualFee_column;

    // Payments Pane
    @FXML
    private Pane payments_pane;
    @FXML
    private TableView<Payment> payments_table;
    @FXML
    private TableColumn reason_column, type_column, amount_column, paymentDate_column, member_column;

    // Boats Pane
    @FXML
    private Pane boats_pane;
    @FXML
    private TableView<Boat> boats_table;
    @FXML
    private TableColumn boat_column, owner_column, length_column, storageFee_column;

    private double x, y;
    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeDragable();
        client = new Client();
        setTables();
    }

    /**
     * Method used to set member data received from {@code SignController}
     *
     * @param s {@code Staff} object created during Sign In
     * @see Staff
     */
    public void setStaff(Staff s) {
        this.navBar_username.setText(s.getUsername());
        setTableMembers();
    }
    private void setTables() {
        // Races Table
        raceName_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        raceDate_column.setCellValueFactory(new PropertyValueFactory<>("date"));
        entryFee_column.setCellValueFactory(new PropertyValueFactory<>("entryFee"));
        // Members Table
        username_column.setCellValueFactory(new PropertyValueFactory<>("username"));
        fullname_column.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        annualFee_column.setCellValueFactory(new PropertyValueFactory<>("annual_fee"));
        // Payments Table
        reason_column.setCellValueFactory(new PropertyValueFactory<>("reason"));
        type_column.setCellValueFactory(new PropertyValueFactory<>("type"));
        amount_column.setCellValueFactory(new PropertyValueFactory<>("amount"));
        paymentDate_column.setCellValueFactory(new PropertyValueFactory<>("date"));
        member_column.setCellValueFactory(new PropertyValueFactory<>("user"));
        // Boats Table
        boat_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        owner_column.setCellValueFactory(new PropertyValueFactory<>("user"));
        length_column.setCellValueFactory(new PropertyValueFactory<>("length"));
        storageFee_column.setCellValueFactory(new PropertyValueFactory<>("feeExpiryFormatted"));
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

    private List<Boat> queryGetAllBoats() {
        return (List<Boat>) client.connect(GET_ALL_BOATS, PARAM);
    }

    private List<Payment> queryGetAllPayments() {
        return (List<Payment>) client.connect(GET_ALL_PAYMENTS, PARAM);
    }

    private List<Member> queryGetAllMembers() {
        return (List<Member>) client.connect(GET_ALL_MEMBERS, PARAM);
    }

    private List<Race> queryGetAllRaces() {
        return (List<Race>) client.connect(GET_ALL_RACES, PARAM);
    }

    private List<Race> queryGetPastRaces() {
        return (List<Race>) client.connect(GET_PAST_RACES, LocalDateTime.now().toString());
    }

    private void queryAddNotification (Notification n){
        client.connect(ADD_NOTIFICATION, n.getContent() + SEPARATOR + n.getUser());
    }

    private void queryAddNewRace(Race r){
        client.connect(ADD_RACE, r.getName() + SEPARATOR + r.getDate() + SEPARATOR + r.getEntryFee());
    }

    private void queryDeleteMember(String username){
        client.connect(DELETE_MEMBER, username);
    }

    private void queryDeleteRace(String id){
        client.connect(DELETE_RACE, id);
    }

    private void setTablePastRaces() {
        raceTable.getItems().clear();
        for (Race r : this.queryGetPastRaces())
            raceTable.getItems().add(r);
    }

    private void setTableAllRaces() {
        raceTable.getItems().clear();
        for (Race r : this.queryGetAllRaces()){
            raceTable.getItems().add(r);
        }
    }

    private void setTablePayments() {
        payments_table.getItems().clear();
        for (Payment p : this.queryGetAllPayments()) {
            payments_table.getItems().add(p);
        }
    }
    private void setTableBoats() {
        boats_table.getItems().clear();
        for (Boat b : this.queryGetAllBoats()) {
            boats_table.getItems().add(b);
        }
    }
    private void setTableMembers() {
        membersTable.getItems().clear();
        for (Member m: this.queryGetAllMembers()) {
            membersTable.getItems().add(m);
        }
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

    private void setMembers(){
        boats_pane.setVisible(false);
        payments_pane.setVisible(false);
        races_pane.setVisible(false);
        addNewRace_pane.setVisible(false);
        members_pane.setVisible(true);
        setTableMembers();
    }
    @FXML
    private void showMembers(ActionEvent actionEvent) {
        setMembers();
    }

    @FXML
    private void showBoats(ActionEvent actionEvent) {
        payments_pane.setVisible(false);
        races_pane.setVisible(false);
        addNewRace_pane.setVisible(false);
        members_pane.setVisible(false);
        boats_pane.setVisible(true);
        setTableBoats();
    }

    private void setRaces(){
        boats_pane.setVisible(false);
        payments_pane.setVisible(false);
        addNewRace_pane.setVisible(false);
        members_pane.setVisible(false);
        races_pane.setVisible(true);
        setTableAllRaces();
    }
    @FXML
    private void showRaces(ActionEvent actionEvent) {
        setRaces();
    }

    @FXML
    private void showPayments(ActionEvent actionEvent) {
        boats_pane.setVisible(false);
        races_pane.setVisible(false);
        addNewRace_pane.setVisible(false);
        members_pane.setVisible(false);
        payments_pane.setVisible(true);
        setTablePayments();

    }

    @FXML
    private void addNewRace(ActionEvent actionEvent) {
        if (raceName_textField.getText().isBlank() || racePrice_textField.getText().isEmpty() || raceDate_textField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("INPUT ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Please complete all field.");
            alert.showAndWait();
        }
        else if (!raceDate_textField.getText().matches("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$" )){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("INPUT ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Invalid race date format.");
            alert.showAndWait();
        }
        else if(!checkPriceTextField()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("INPUT ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Invalid price format.");
            alert.showAndWait();
        }
        else{
            SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                String reformattedStr = myFormat.format(fromUser.parse(raceDate_textField.getText()));
                String name = raceName_textField.getText();
                String price = racePrice_textField.getText();
                Race race = new Race(name, reformattedStr, price);

                queryAddNewRace(race);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
                alert.setTitle("SUCCESS");
                alert.initStyle(StageStyle.TRANSPARENT);
                alert.setContentText("New race " + raceName_textField.getText() + " added successfully.");
                alert.showAndWait();
                setRaces();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private Boolean checkPriceTextField(){
        try {
            double d = Double.parseDouble(racePrice_textField.getText());
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @FXML
    private void backToRaces(ActionEvent actionEvent) {
        addNewRace_pane.setVisible(false);
        races_pane.setVisible(true);
    }

    @FXML
    private void getSelectedRace(MouseEvent mouseEvent) {
    }

    @FXML
    private void switchRaces(ActionEvent actionEvent) {
        if(race_label.getText().equals(PAST_RACES)){
            setTableAllRaces();
            race_label.setText(ALL_RACES);
            switchRaces_btn.setText(PAST_RACES);

        } else if(race_label.getText().equals(ALL_RACES)) {
            setTablePastRaces();
            race_label.setText(PAST_RACES);
            switchRaces_btn.setText(ALL_RACES);
        }
    }

    @FXML
    private void deleteRace(ActionEvent actionEvent) {
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
        Race race = raceTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        alert.setTitle("ERROR");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.setContentText("Are you sure you want to delete " + race.getName() + " ?");
        alert.showAndWait();

        if(alert.getResult()== ButtonType.OK){
            queryDeleteRace(race.getId());
            setRaces();
        }
    }

    @FXML
    private void removeMember(ActionEvent actionEvent) {
        if(membersTable.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Please select a member.");
            alert.showAndWait();
            return;
        }
        Member member = membersTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        alert.setTitle("ERROR");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.setContentText("Are you sure you want to delete " + member.getUsername()+ " ?");
        alert.showAndWait();

        if(alert.getResult()== ButtonType.OK){
            queryDeleteMember(member.getUsername());
            setMembers();
        }
    }

    @FXML
    private void sendAnnualFeeNotification(ActionEvent actionEvent) {
        if(membersTable.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Please select a member.");
            alert.showAndWait();
            return;
        }
        Member m = membersTable.getSelectionModel().getSelectedItem();
        Notification notification = new Notification("Attention! Your annual fee is about to expire", m.getUsername());
        queryAddNotification(notification);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        alert.setTitle("SUCCESS");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.setContentText("Notification send to " + notification.getUser() + ".");
        alert.showAndWait();
    }

    @FXML
    private void sendStorageFeeNotification(ActionEvent actionEvent) {
        if(boats_table.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            alert.setTitle("ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.setContentText("Please select an item from the table.");
            alert.showAndWait();
            return;
        }

        Boat b = boats_table.getSelectionModel().getSelectedItem();
        Notification notification = new Notification("Attention! Your annual storage fee is about to expire", b.getUser());
        queryAddNotification(notification);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        alert.setTitle("SUCCESS");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.setContentText("Notification send to " + notification.getUser() + ".");
        alert.showAndWait();
    }

    public void showAddNewRacePane(ActionEvent actionEvent) {
        raceDate_textField.setText("");
        raceName_textField.setText("");
        racePrice_textField.setText("");
        races_pane.setVisible(false);
        addNewRace_pane.setVisible(true);
    }
}
