package it.univr.ipertensione_hope.Controller.patient;

import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.BloodPressureCategory;
import it.univr.ipertensione_hope.Model.BloodPressureData;
import it.univr.ipertensione_hope.Model.Paziente;
import it.univr.ipertensione_hope.Model.Sintomo;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class ReportBloodPressure {

    @FXML
    private TextField sbpField;
    @FXML
    private TextField dbpField;
    @FXML
    private Button segnalaSintomi;

    private Sintomo sintomoSegnalato = null;

    @FXML
    private void confirm(ActionEvent event) {
        // Metodo chiamato quando l'utente conferma i valori di pressione
        String sbpText = sbpField.getText();
        String dbpText = dbpField.getText();

        try {
            int sbp = Integer.parseInt(sbpText);
            int dbp = Integer.parseInt(dbpText);

            if(!BloodPressureData.isValid(sbp, dbp)) {
                Functions.alert("I dati di pressione non sembrano validi", Alert.AlertType.ERROR, null);
            } else {
                BloodPressureData dati;

                if(sintomoSegnalato == null) {
                    dati = new BloodPressureData(PatientAppData.getInstance().getLoggedPatient().getPatientId(), sbp, dbp, LocalDate.now());

                    // !! stampa qualche messaggio per avvertire l'utente sullo stato della pressione
                    BloodPressureCategory stato = dati.classifyBloodPressure();

                    if(dati.add()) {
                        Functions.alert("Dati inseriti correttamente", Alert.AlertType.INFORMATION, (ButtonType button) -> {
                            String page = "patient/PatientDashboard.fxml";
                            WindowsManager.loadPage(getClass().getResource(page), page);
                        });
                    } else {
                        Functions.alert("Errore inaspettato durante il salvataggio nel database", Alert.AlertType.ERROR, null);
                    }

                } else {
                    dati = new BloodPressureData(PatientAppData.getInstance().getLoggedPatient().getPatientId(), sbp, dbp, LocalDate.now(), getSintomoSegnalato().getId());

                    // !! stampa qualche messaggio per avvertire l'utente sullo stato della pressione
                    BloodPressureCategory stato = dati.classifyBloodPressure();

                    if(dati.addWithSymptom()) {
                        Functions.alert("Dati inseriti correttamente", Alert.AlertType.INFORMATION, (ButtonType button) -> {
                            String page = "patient/PatientDashboard.fxml";
                            WindowsManager.loadPage(getClass().getResource(page), page);
                        });
                    } else {
                        Functions.alert("Errore inaspettato durante il salvataggio nel database", Alert.AlertType.ERROR, null);
                    }
                }
            }

        } catch (NumberFormatException e) {
            Functions.alert("Inserisci valori numerici validi", Alert.AlertType.ERROR, null);
        }
    }

    @FXML
    private void segnalaSintomi(ActionEvent event) {
        String path = PatientAppData.getInstance().getDirectory() + "SegnalaSintomi.fxml";
        WindowsManager.nextPage(WindowsManager.mainClass.getResource(path), path);
    }

    public void setSintomoSegnalato(Sintomo sintomoSegnalato) {
        this.sintomoSegnalato = sintomoSegnalato;
    }

    public Sintomo getSintomoSegnalato() {
        return this.sintomoSegnalato;
    }
}
