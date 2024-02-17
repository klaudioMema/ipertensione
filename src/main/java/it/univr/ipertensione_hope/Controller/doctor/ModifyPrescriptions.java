package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.Medico;
import it.univr.ipertensione_hope.Model.Prescrizione;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ModifyPrescriptions {
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
        if(     medicationField.getText().isEmpty() || indicationsField.getText().isEmpty() ||
                FromDateField.getValue() == null || ToDateField.getValue() == null )
            Functions.alert("Compilare tutti i campi", Alert.AlertType.ERROR, null);
        else {
                selectPrescription.setMedication(medicationField.getText());
                selectPrescription.setIndications(indicationsField.getText());
                selectPrescription.setFromDate(FromDateField.getValue());
                selectPrescription.setToDate((ToDateField.getValue()));
                /*
            if(Medico.updateDoctor(selectedDoctor)) {
                Functions.alert("Medico salvato correttamente", Alert.AlertType.INFORMATION, (ButtonType button) -> {
                    WindowsManager.previousPage();
                });
            } else {
                Functions.alert("Errore inaspettato", Alert.AlertType.ERROR, null);
            }
            */


        }
    }
}
