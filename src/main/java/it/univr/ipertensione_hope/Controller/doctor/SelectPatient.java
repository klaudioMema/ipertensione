package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.Paziente;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectPatient implements Initializable {
    private final String directory = "doctor/";

    @FXML
    private Button selezionaButton;
    @FXML
    private TableView<Paziente> listaPazienti;
    @FXML
    private Label PatientLabel;
    @FXML
    private Label SelectPatient;

    private Paziente selectedPaziente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Paziente[] pazienti = Paziente.getAllByDoctor(DoctorAppData.getInstance().getMedicoLoggato());
        selectedPaziente = DoctorAppData.getInstance().getSelectedPatient();

        if(selectedPaziente != null)
            SelectPatient.setText(selectedPaziente.getNome() + " " + selectedPaziente.getCognome());

        listaPazienti.setItems(FXCollections.observableArrayList(pazienti));

        TableColumn<Paziente, String> nomeCol = new TableColumn<>("nome");
        nomeCol.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());

        TableColumn<Paziente, String> cognomeCol = new TableColumn<>("cognome");
        cognomeCol.setCellValueFactory(cellData -> cellData.getValue().cognomeProperty());

        TableColumn<Paziente, String> mailCol = new TableColumn<>("mail");
        mailCol.setCellValueFactory(cellData -> cellData.getValue().mailProperty());
    }

    @FXML
    private void seleziona() {
        Paziente selectedPatient = listaPazienti.getSelectionModel().getSelectedItem();

        if(selectedPatient == null) {
            Functions.alert("Seleziona prima un paziente dalla tabella", Alert.AlertType.ERROR, null);
        } else {
            DoctorAppData.getInstance().setSelectedPatient(selectedPatient);
            Functions.alert("Paziente selezionato correttamente", Alert.AlertType.INFORMATION, (ButtonType button) -> {
                WindowsManager.loadPage(WindowsManager.mainClass.getResource(directory + "DoctorDashboard.fxml"), directory + "DoctorDashboard.fxml");
            });
        }
    }
}
