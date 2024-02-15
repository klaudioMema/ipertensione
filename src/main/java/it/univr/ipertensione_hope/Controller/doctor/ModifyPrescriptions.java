package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.Prescrizione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ModifyPrescriptions {
    @FXML
    private DatePicker FromDateField;
    @FXML
    private DatePicker ToDateField;
    @FXML
    private TableColumn<?, ?> fromDateColumn;
    @FXML
    private TableColumn<?, ?> indicationsColumn;
    @FXML
    private TextArea indicationsField;
    @FXML
    private TableView<?> listaPrescrizioni;
    @FXML
    private TableColumn<?, ?> medicationColumn;
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
    void salve(ActionEvent event) {
        if(     medicationField.getText().isEmpty() || indicationsField.getText().isEmpty() ||
                FromDateField.getValue() == null || ToDateField.getValue() == null )
            Functions.alert("Compilare tutti i campi", Alert.AlertType.ERROR, null);
        else {
                selectPrescription.setMedication(medicationField.getText());
                selectPrescription.setIndications(indicationsField.getText());
                selectPrescription.setFromDate(FromDateField.getValue());
                selectPrescription.setToDate((ToDateField.getValue()));
        }
    }
}
