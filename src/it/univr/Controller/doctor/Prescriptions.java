package it.univr.Controller.doctor;

import it.univr.Controller.DatabaseController;
import it.univr.Model.Patient;
import it.univr.Model.Prescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class Prescriptions implements Initializable {
    @FXML
    private TableView<Prescription> tableView;
    @FXML
    private TableColumn<Prescription,String> medicationColumn;
    @FXML
    private TableColumn<Prescription, String> indicationsColumn;
    @FXML
    private TableColumn<Prescription, Integer> daysColumn;
    @FXML
    private TableColumn<Prescription, Date> fromDateColumn;
    @FXML
    private TextField medicationField;
    @FXML
    private TextArea indicationsField;
    @FXML
    private TextField daysField;
    @FXML
    private Label statusLabel;
    private Patient selectedPatient;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //set cell value -> FACTORY PATTERN
        selectedPatient = Patient.getInstance();
        medicationColumn.setCellValueFactory(new PropertyValueFactory<>("medication"));
        indicationsColumn.setCellValueFactory(new PropertyValueFactory<>("indications"));
        daysColumn.setCellValueFactory(new PropertyValueFactory<>("days"));
        fromDateColumn.setCellValueFactory(new PropertyValueFactory<>("fromDate"));

        loadTable();
    }

    @FXML
    private void removeSelected(ActionEvent event){

        try {
            Prescription selectedPrescription = tableView.getSelectionModel().getSelectedItem();

            DatabaseController.updateItem("DELETE FROM prescriptions WHERE user_id = '" +
                    selectedPatient.getPatientId() + "' AND medication LIKE '" +
                    selectedPrescription.getMedication() + "' AND indications LIKE '" +
                    selectedPrescription.getIndications() +"' AND days LIKE '" +
                    selectedPrescription.getDays() + "' AND fromDate LIKE '" +
                    selectedPrescription.getFromDate() + "'");


            int selectedMedication = tableView.getSelectionModel().getSelectedIndex();
            tableView.getItems().remove(selectedMedication);

            statusLabel.setText("Therapy Removed!");
            statusLabel.setVisible(true);
            statusLabel.setTextFill(Color.rgb(60,176,80));
        } catch (Exception e){
            statusLabel.setText("Couldn't remove therapy");
            statusLabel.setVisible(true);
            statusLabel.setTextFill(Color.rgb(211,81,81));
        }

    }

    @FXML
    private void addTherapy(ActionEvent event){


        if(medicationField.getText().equals("") || indicationsField.getText().equals("") || daysField.getText().equals("")) {
            statusLabel.setText("Couldn't add therapy");
            statusLabel.setVisible(true);
            statusLabel.setTextFill(Color.rgb(211,81,81));
        } else {
            String query = "INSERT INTO prescriptions (user_id, medication, indications, days, fromDate)" +
                            "VALUES ('"+ selectedPatient.getPatientId() +"', '" +
                            medicationField.getText() + "', '" +
                            indicationsField.getText() + "', '" +
                            daysField.getText() + "', '" +
                            new Date(System.currentTimeMillis()) + "')";

            DatabaseController.updateItem(query);
            statusLabel.setText("Therapy Added!");
            statusLabel.setVisible(true);
            statusLabel.setTextFill(Color.rgb(60,176,80));
            medicationField.clear();
            indicationsField.clear();
            daysField.clear();
        }
        loadTable();
    }

    private void loadTable(){
        ObservableList<Prescription> data = FXCollections.observableArrayList();
        try {
            ResultSet rs = DatabaseController.getResultSet("SELECT * FROM prescriptions WHERE user_id = '" +
                    selectedPatient.getPatientId() + "'");
            while (rs.next()){
                Prescription prescription = new Prescription(rs.getString("medication"),
                        rs.getString("indications"),
                        rs.getInt("days"),
                        rs.getDate("fromDate"));
                // If prescription is not valid anymore remove it from DB
                if(isValidYet(prescription.getFromDate(), prescription.getDays()))
                    data.add(prescription);
                else
                    deleteExpiredPrescription(prescription);
            }
            tableView.setItems(data);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    // Check if prescription still valid today
    private boolean isValidYet(Date fromDate, int days){
        Date today = new Date(System.currentTimeMillis());
        long daysPassed = ChronoUnit.DAYS.between(fromDate.toLocalDate(), today.toLocalDate());
        return daysPassed <= days;
    }

    private void deleteExpiredPrescription(Prescription prescription){
        DatabaseController.updateItem("DELETE FROM prescriptions WHERE user_id = '" +
                selectedPatient.getPatientId() + "' AND medication LIKE '" +
                prescription.getMedication() + "' AND indications LIKE '" +
                prescription.getIndications() +"' AND days LIKE '" +
                prescription.getDays() + "' AND fromDate LIKE '" +
                prescription.getFromDate() + "'");
    }

}
