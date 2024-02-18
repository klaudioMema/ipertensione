package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.Paziente;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MyPatient implements Initializable {
    @FXML
    private Label PatientLabel;
    @FXML
    private Label SelectPatient;
    @FXML
    private Label nameField;
    @FXML
    private Label surnameField;
    @FXML
    private Label codiceFField;
    @FXML
    private Label bDayField;
    @FXML
    private TextArea fattoriDiRischio;

    private Paziente selectedPaziente;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedPaziente=DoctorAppData.getInstance().getSelectedPatient();
        if ( selectedPaziente == null)
            Functions.alert("Paziente non selezionato", Alert.AlertType.ERROR, null);
        else{
            SelectPatient.setText(selectedPaziente.getNome() +" "+ selectedPaziente.getCognome());
            nameField.setText(selectedPaziente.getNome());
            surnameField.setText(selectedPaziente.getCognome());
            codiceFField.setText(selectedPaziente.getCodiceF());
            bDayField.setText((selectedPaziente.getbDay().toString()));
            fattoriDiRischio.setText(selectedPaziente.getFattoriDiRischio());
        }

        if(selectedPaziente != null) {
            PatientLabel.setText("Paziente selezionato: " + selectedPaziente.getNome() + " " + selectedPaziente.getCognome());
        } else {
            PatientLabel.setText("Nessun paziente selezionato");
        }
    }

    public void aggiornaFattoriRischio(){
        if (  fattoriDiRischio.getText() == null || fattoriDiRischio.getText().isEmpty())
            Functions.alert("completare i fattori di ricschio per aggiornarli", Alert.AlertType.ERROR, null);
        else {
            selectedPaziente.setFattoriDiRischio(fattoriDiRischio.getText());

            if(selectedPaziente.updateRisk()) {
                Functions.alert("Fattori di Rischio aggiornati", Alert.AlertType.INFORMATION, (ButtonType button) -> {
                    WindowsManager.reloadPage();
                });
            } else {
                Functions.alert("Errore inaspettato durante la modifica", Alert.AlertType.ERROR, null);
            }
        }

    }

}
