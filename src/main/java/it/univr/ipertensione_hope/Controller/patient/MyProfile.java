package it.univr.ipertensione_hope.Controller.patient;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MyProfile implements Initializable {
    @FXML
    private Label codiceFLabel;
    @FXML
    private Label cognomeLabel;
    @FXML
    private Label dataNascitaLabel;
    @FXML
    private Label fattoriRiscLabel;
    @FXML
    private Label nameLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.setText(PatientAppData.getInstance().getLoggedPatient().getCognome());
        cognomeLabel.setText(PatientAppData.getInstance().getLoggedPatient().getCognome());
        codiceFLabel.setText(PatientAppData.getInstance().getLoggedPatient().getCodiceF());
        dataNascitaLabel.setText(PatientAppData.getInstance().getLoggedPatient().getbDay().toString());
        fattoriRiscLabel.setText(PatientAppData.getInstance().getLoggedPatient().getFattoriDiRischio());
    }
}
