package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.Medico;
import it.univr.ipertensione_hope.Model.Paziente;
import it.univr.ipertensione_hope.Model.Prescrizione;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPrescriptions implements Initializable {
    @FXML
    private DatePicker FromDateField;
    @FXML
    private DatePicker ToDateField;

    @FXML
    private TextArea indicationsField;
    @FXML
    private TextField medicationField;
    @FXML
    private Label patientLabel;
    @FXML
    private TableColumn<?, ?> toDateColumn;
    private Prescrizione selectPrescription;

    public void setData(Prescrizione prescrizione) {

        selectPrescription = prescrizione;
        FromDateField.setValue(this.selectPrescription.getFromDate());
        ToDateField.setValue((this.selectPrescription.getToDate()));
        medicationField.setText((this.selectPrescription.getMedication()));
        indicationsField.setText((this.selectPrescription.getIndications()));
    }
    @FXML
    void salva(ActionEvent event) {
        if(     medicationField.getText().isEmpty() ||
                FromDateField.getValue() == null ||
                ToDateField.getValue() == null )
            Functions.alert("Compilare tutti i campi", Alert.AlertType.ERROR, null);
        else {
            selectPrescription.setMedication(medicationField.getText());
            selectPrescription.setIndications(indicationsField.getText());
            selectPrescription.setFromDate(FromDateField.getValue());
            selectPrescription.setToDate((ToDateField.getValue()));

            if(selectPrescription.updatePrescription()) {
                Functions.alert("Prescrizione modificata correttamente", Alert.AlertType.INFORMATION, (ButtonType button) -> {
                    WindowsManager.previousPage();
                    WindowsManager.reloadPage();
                });
            } else {
                Functions.alert("Errore inaspettato durante la modifica", Alert.AlertType.ERROR, null);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Paziente selectedPaziente = DoctorAppData.getInstance().getSelectedPatient();

        if(selectedPaziente != null) {
            patientLabel.setText("Paziente selezionato: " + selectedPaziente);
        } else {
            patientLabel.setText("Nessun paziente selezionato");
        }
    }
}
