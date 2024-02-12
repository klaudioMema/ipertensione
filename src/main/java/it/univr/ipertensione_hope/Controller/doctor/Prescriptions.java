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
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class Prescriptions implements Initializable {
    @FXML
    private TableView<Prescrizione> listaPrescrizioni;
    @FXML
    private TableColumn<Prescrizione,String> medicationColumn;
    @FXML
    private TableColumn<Prescrizione, String> indicationsColumn;
    @FXML
    private TableColumn<Prescrizione, Integer> daysColumn;
    @FXML
    private TableColumn<Prescrizione, Date> fromDateColumn;
    @FXML
    private TextField medicationField;
    @FXML
    private TextArea indicationsField;
    @FXML
    private DatePicker FromDateField;

    @FXML DatePicker ToDateField;

    private Paziente selectedPaziente;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // controllo se il paziente è stato selezionato

        Prescrizione[] prescrizioni = Prescrizione.getAllByPatient(selectedPaziente);

        medicationColumn.setCellValueFactory(new PropertyValueFactory<>("medication"));
        indicationsColumn.setCellValueFactory(new PropertyValueFactory<>("indications"));
        daysColumn.setCellValueFactory(new PropertyValueFactory<>("days"));
        fromDateColumn.setCellValueFactory(new PropertyValueFactory<>("fromDate"));

        // Caricamento dei dati nella tabella
        if (prescrizioni != null) {
            listaPrescrizioni.setItems(FXCollections.observableArrayList(prescrizioni));
        } else {
            // Gestione dell'errore se non ci sono prescrizioni
            ObservableList<Prescrizione> emptyList = FXCollections.observableArrayList();
            emptyList.add(new Prescrizione("Nessuna prescrizione trovata", "", 0, null));
            listaPrescrizioni.setItems(emptyList);
        }

    }

    @FXML
    private void addTherapy(ActionEvent event){
        if(     medicationField.getText().isEmpty() ||
                indicationsField.getText().isEmpty() ||
                FromDateField == null ||
                ToDateField == null
        ) {
            Functions.alert("Riempire tutti i campi", Alert.AlertType.ERROR, null);
        } else {
            Prescrizione prescrizione = new Prescrizione(
                    selectedPaziente.getPatientId(),
                    medicationField.getText(),
                    indicationsField.getText(),
                    Date.valueOf(FromDateField.getValue()),
                    Date.valueOf(ToDateField.getValue())
            );
            if(prescrizione.add()) {
                Functions.alert("Prescrizione aggiunta", Alert.AlertType.INFORMATION, (ButtonType button) -> {
                    WindowsManager.reloadPage();
                });
            }

        }
    }

    // Check if prescription still valid today
    private boolean isValidYet(Date fromDate, int days){
        Date today = new Date(System.currentTimeMillis());
        long daysPassed = ChronoUnit.DAYS.between(fromDate.toLocalDate(), today.toLocalDate());
        return daysPassed <= days;
    }

    private void deleteExpiredPrescription(Prescrizione prescrizione){
        DatabaseController.updateItem("DELETE FROM prescriptions WHERE user_id = '" +
                selectedPaziente.getPatientId() + "' AND medication LIKE '" +
                prescrizione.getMedication() + "' AND indications LIKE '" +
                prescrizione.getIndications() +"' AND days LIKE '" +
                prescrizione.getDays() + "' AND fromDate LIKE '" +
                prescrizione.getFromDate() + "'");
    }

}
