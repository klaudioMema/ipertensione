package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Controller.DatabaseController;
import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.Paziente;
import it.univr.ipertensione_hope.Model.Prescrizione;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prescriptions implements Initializable {
    @FXML
    private TableView<Prescrizione> listaPrescrizioni;
    @FXML
    private TableColumn<Prescrizione,String> medicationColumn;
    @FXML
    private TableColumn<Prescrizione, String> indicationsColumn;
    @FXML
    private TableColumn<Prescrizione, Date> fromDateColumn;
    @FXML
    private TableColumn<Prescrizione, Date> toDateColumn;
    @FXML
    private TextField medicationField;
    @FXML
    private TextArea indicationsField;
    @FXML
    private DatePicker FromDateField;
    @FXML
    private DatePicker ToDateField;
    @FXML
    private Label patientLabel;
    private Paziente selectedPaziente;

    private final String directory = "doctor/";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedPaziente = DoctorAppData.getInstance().getSelectedPatient();

        patientLabel.setText("Paziente selezionato: " + selectedPaziente.getNome() + " " + selectedPaziente.getCognome());

        Prescrizione[] prescrizioni = Prescrizione.getAllByPatient(selectedPaziente);

        medicationColumn.setCellValueFactory(new PropertyValueFactory<>("medication"));
        indicationsColumn.setCellValueFactory(new PropertyValueFactory<>("indications"));
        fromDateColumn.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        toDateColumn.setCellValueFactory(new PropertyValueFactory<>("toDate"));

        // Caricamento dei dati nella tabella

    }

    @FXML
    private void addTherapy(ActionEvent event){
        if(     medicationField.getText().isEmpty() ||
                FromDateField == null ||
                ToDateField == null
        ) {
            Functions.alert("Riempire tutti i campi", Alert.AlertType.ERROR, null);
        }else if (!(FromDateField.getValue().isEqual(LocalDate.now()))) {
            Functions.alert("Inserire il giorno di oggi", Alert.AlertType.ERROR, null);

        } else if (!(ToDateField.getValue().isAfter(FromDateField.getValue()))) {
            Functions.alert("La data deve essere maggiore della data di oggi", Alert.AlertType.ERROR, null);

        }
        else {
            Prescrizione prescrizione = new Prescrizione(
                    selectedPaziente.getPatientId(),
                    medicationField.getText(),
                    indicationsField.getText(),
                    FromDateField.getValue(),
                    ToDateField.getValue()
            );
            if(prescrizione.add()) {
                Functions.alert("Prescrizione aggiunta", Alert.AlertType.INFORMATION, (ButtonType button) -> {
                    WindowsManager.reloadPage();
                });
            }

        }
    }

}
