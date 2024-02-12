package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Controller.DatabaseController;
import it.univr.ipertensione_hope.Model.Paziente;
import it.univr.ipertensione_hope.Model.Prescrizione;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class AlertPatient {
    @FXML
    private Label statusLabel;
    @FXML
    private TableColumn<?, String> alert_Category;
    @FXML
    private TableView<Prescrizione> tableView;
    @FXML
    private TableColumn<?, String> alert_emailPatient;

    @FXML
    private TableColumn<?, String> alert_namePatient;

    @FXML
    private TableColumn<?, String> alert_specific;
    private Paziente selectedPaziente;
    @FXML
    void removeAlert(ActionEvent event) {

        try {
            Prescrizione selectedPrescrizione = tableView.getSelectionModel().getSelectedItem();
            //type presciption_id
            DatabaseController.updateItem("DELETE FROM alert WHERE user_id = '" +
                    selectedPaziente.getPatientId() + "' AND Category LIKE '" +
                    selectedPrescrizione.getMedication() + "' AND Specify LIKE '" +
                    //selectedPrescription.get +"' AND idPatient LIKE '" +
                    selectedPrescrizione.getDays() + "' AND EmailPatient LIKE '" +
                    selectedPrescrizione.getFromDate() + "'");

            int selectedMedication = tableView.getSelectionModel().getSelectedIndex();
            tableView.getItems().remove(selectedMedication);

            statusLabel.setText("Alert Removed!");
            statusLabel.setVisible(true);
            statusLabel.setTextFill(Color.rgb(60,176,80));
        } catch (Exception e){
            statusLabel.setText("Couldn't remove Alert");
            statusLabel.setVisible(true);
            statusLabel.setTextFill(Color.rgb(211,81,81));
        }
    }

    @FXML
    private void myPatient(ActionEvent event) throws IOException {
        Parent fxml =  FXMLLoader.load(WindowsManager.mainClass.getResource("../../View/doctor/MyPatient.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxml);
        stage.setScene(scene);
        stage.show();
    }

}
