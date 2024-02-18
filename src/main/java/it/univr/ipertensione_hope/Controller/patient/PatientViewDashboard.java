package it.univr.ipertensione_hope.Controller.patient;

import it.univr.ipertensione_hope.Model.BloodPressureData;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class PatientViewDashboard implements Initializable {
    @FXML
    public Label welcomeLabel;
    @FXML
    private Label pressureDataLabel;
    @FXML
    private Button loadPressureDataButton;

    private String directory = "patient/";

    @FXML
    private void openPressureDataPage() {
        String path = directory + "ReportBloodPressure.fxml";
        WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcomeLabel.setText(PatientAppData.getInstance().getLoggedPatient().getNome());

        // Controlla se sono presenti dati di pressione per la data odierna
        if (BloodPressureData.hasReportedToday(PatientAppData.getInstance().getLoggedPatient())) {
            pressureDataLabel.setVisible(false);
            loadPressureDataButton.setVisible(false);
        } else {
            pressureDataLabel.setVisible(true);
            loadPressureDataButton.setVisible(true);
        }
    }
}
