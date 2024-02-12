package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Controller.DatabaseController;
import it.univr.ipertensione_hope.Model.Paziente;
import it.univr.ipertensione_hope.Model.Prescrizione;
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
    private TableView<Prescrizione> tableView;
    @FXML
    private TableColumn<Prescrizione,String> medicationColumn;
    @FXML
    private TableColumn<Prescrizione, String> indicationsColumn;
    @FXML
    private TableColumn<Prescrizione, Integer> daysColumn;
    @FXML
    private TableColumn<Prescrizione, Date> fromDateColumn;
    @FXML
    private TextField medicationField;
    @FXML
    private TextArea indicationsField;
    @FXML
    private TextField daysField;
    @FXML
    private Label statusLabel;
    private Paziente selectedPaziente;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //set cell value -> FACTORY PATTERN
        /*
        selectedPaziente = Paziente.getInstance();
        medicationColumn.setCellValueFactory(new PropertyValueFactory<>("medication"));
        indicationsColumn.setCellValueFactory(new PropertyValueFactory<>("indications"));
        daysColumn.setCellValueFactory(new PropertyValueFactory<>("days"));
        fromDateColumn.setCellValueFactory(new PropertyValueFactory<>("fromDate"));

        loadTable();

         */
    }

    @FXML
    private void removeSelected(ActionEvent event){

        try {
            Prescrizione selectedPrescrizione = tableView.getSelectionModel().getSelectedItem();

            DatabaseController.updateItem("DELETE FROM prescriptions WHERE user_id = '" +
                    selectedPaziente.getPatientId() + "' AND medication LIKE '" +
                    selectedPrescrizione.getMedication() + "' AND indications LIKE '" +
                    selectedPrescrizione.getIndications() +"' AND days LIKE '" +
                    selectedPrescrizione.getDays() + "' AND fromDate LIKE '" +
                    selectedPrescrizione.getFromDate() + "'");


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
                            "VALUES ('"+ selectedPaziente.getPatientId() +"', '" +
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
        ObservableList<Prescrizione> data = FXCollections.observableArrayList();
        try {
            ResultSet rs = DatabaseController.getResultSet("SELECT * FROM prescriptions WHERE user_id = '" +
                    selectedPaziente.getPatientId() + "'");
            while (rs.next()){
                Prescrizione prescrizione = new Prescrizione(rs.getString("medication"),
                        rs.getString("indications"),
                        rs.getInt("days"),
                        rs.getDate("fromDate"));
                // If prescription is not valid anymore remove it from DB
                if(isValidYet(prescrizione.getFromDate(), prescrizione.getDays()))
                    data.add(prescrizione);
                else
                    deleteExpiredPrescription(prescrizione);
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

    private void deleteExpiredPrescription(Prescrizione prescrizione){
        DatabaseController.updateItem("DELETE FROM prescriptions WHERE user_id = '" +
                selectedPaziente.getPatientId() + "' AND medication LIKE '" +
                prescrizione.getMedication() + "' AND indications LIKE '" +
                prescrizione.getIndications() +"' AND days LIKE '" +
                prescrizione.getDays() + "' AND fromDate LIKE '" +
                prescrizione.getFromDate() + "'");
    }

}
