package it.univr.ipertensione_hope.Controller.patient;

import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.Medico;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class MyDoctor implements Initializable {
    @FXML
    private Label emailField;
    @FXML
    private Label nameField;
    @FXML
    private Label surnameField;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Medico medico = Medico.getDoctor(PatientAppData.getInstance().getLoggedPatient().getDoctorId());
        if (medico == null)
            Functions.alert("Medico non trovato", Alert.AlertType.ERROR, null);
        else{
            nameField.setText(medico.getNome());
            surnameField.setText(medico.getCognome());
            emailField.setText(medico.getEmail());

        }
    }
}
