package it.univr.ipertensione_hope.Controller.patient;

import it.univr.ipertensione_hope.Controller.DatabaseController;
import it.univr.ipertensione_hope.Model.Paziente;
import it.univr.ipertensione_hope.Model.Prescrizione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Medications implements Initializable {

    @FXML
    private TableView<Prescrizione> tableView;
    @FXML
    private TableColumn<Prescrizione,String> medicationColumn;
    @FXML
    private TableColumn<Prescrizione, String> indicationsColumn;
    @FXML
    private TableColumn<Prescrizione, Integer> daysLeftColumn;
    @FXML
    private TableColumn<Prescrizione, Date> untilDateColumn;
    @FXML
    private Label todayLabel;
    @FXML
    private Label statusLabel;
    private Paziente activeUser;
    private HomePage homePageController;

    public void setHomePageController(HomePage homePageController){
        this.homePageController = homePageController;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //set cell value -> FACTORY PATTERN
        /*
        activeUser = Paziente.getInstance();
        medicationColumn.setCellValueFactory(new PropertyValueFactory<>("medication"));
        indicationsColumn.setCellValueFactory(new PropertyValueFactory<>("indications"));
        daysLeftColumn.setCellValueFactory(new PropertyValueFactory<>("days"));
        untilDateColumn.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        todayLabel.setText("Medications I have to take today: " + LocalDate.now());

        loadTable();

         */
    }


    private void loadTable(){

    }

    @FXML
    private void reportMedicine(ActionEvent event){
        Prescrizione selectedItem;
        if(tableView.getSelectionModel().getSelectedItem() != null) {
            selectedItem = tableView.getSelectionModel().getSelectedItem();

            String query = "INSERT INTO takenmedication (user_id, medication, indications, days, daythatwastaken)" +
                    "VALUES ('" + activeUser.getPatientId() + "', '" +
                    selectedItem.getMedication() + "', '" +
                    selectedItem.getIndications() + "', '" +
                    selectedItem.getDays() + "', '" +
                    new Date(System.currentTimeMillis()) + "')";
            DatabaseController.updateItem(query);
            loadTable();
            if(tableView.getItems().size() == 0)
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        } else {
            statusLabel.setVisible(true);
            statusLabel.setText("You need to select an item first!");
        }
    }

    private static boolean isValidYet(Date fromDate, int days){
        Date today = new Date(System.currentTimeMillis());
        long daysPassed = ChronoUnit.DAYS.between(fromDate.toLocalDate(), today.toLocalDate());
        return daysPassed <= days;
    }

    private static void deleteExpiredPrescription(Prescrizione prescrizione){
        /*
        DatabaseController.updateItem("DELETE FROM prescriptions WHERE user_id = '" +
                Paziente.getInstance().getPatientId() + "' AND medication LIKE '" +
                prescrizione.getMedication() + "' AND indications LIKE '" +
                prescrizione.getIndications() +"' AND days LIKE '" +
                prescrizione.getDays() + "' AND fromDate LIKE '" +
                prescrizione.getFromDate() + "'");

         */
    }



}
