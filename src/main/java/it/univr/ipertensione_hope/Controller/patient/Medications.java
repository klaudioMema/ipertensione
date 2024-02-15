package it.univr.ipertensione_hope.Controller.patient;

import it.univr.ipertensione_hope.Controller.DatabaseController;
import it.univr.ipertensione_hope.Model.Paziente;
import it.univr.ipertensione_hope.Model.Prescrizione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Medications implements Initializable {

    @FXML
    private TableView<Prescrizione> tableView;
    @FXML
    private TableColumn<Prescrizione,String> medicationColumn;
    @FXML
    private TableColumn<Prescrizione, String> indicationsColumn;
    @FXML
    private TableColumn<Prescrizione, Date> fromDateColumn;
    @FXML
    private TableColumn<Prescrizione, Date> toDateColumn;
    @FXML
    private Label todayLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Ottieni tutte le prescrizioni del paziente attivo
        Prescrizione[] prescrizioni = Prescrizione.getAllByPatient(PatientAppData.getInstance().getLoggedPatient());

        // Lista per memorizzare le prescrizioni cliccabili
        List<Prescrizione> prescrizioniCliccabili = new ArrayList<>();

        // Lista per memorizzare le prescrizioni non cliccabili
        List<Prescrizione> prescrizioniNonCliccabili = new ArrayList<>();

        // Filtra le prescrizioni in base al valore del campo assumption
        // E se la data di oggi è compresa nel range di assunzione
        for (Prescrizione prescrizione : prescrizioni) {
            if (    prescrizione.getAssumption() > 0 &&
                    !LocalDate.now().isBefore(prescrizione.getFromDate()) &&
                    !LocalDate.now().isAfter(prescrizione.getToDate())) {
                prescrizioniCliccabili.add(prescrizione);
            } else {
                prescrizioniNonCliccabili.add(prescrizione);
            }
        }

        // Se non ci sono prescrizioni, stampa un messaggio nella tabella
        if (prescrizioniCliccabili.isEmpty() && prescrizioniNonCliccabili.isEmpty()) {
            Label messageLabel = new Label("Nessuna prescrizione disponibile.");
            tableView.setPlaceholder(messageLabel);
        } else {
            // Ordina le prescrizioni cliccabili per farle apparire all'inizio della tabella
            prescrizioniCliccabili.sort(Comparator.comparing(Prescrizione::getAssumption).reversed());

            // Aggiungi le prescrizioni cliccabili alla tabella
            tableView.getItems().addAll(prescrizioniCliccabili);

            // Per ora non mostro le prescrizioni vecchie
            // Aggiungi le prescrizioni non cliccabili alla fine della tabella
            // tableView.getItems().addAll(prescrizioniNonCliccabili);

            medicationColumn.setCellValueFactory(new PropertyValueFactory<>("medication"));
            indicationsColumn.setCellValueFactory(new PropertyValueFactory<>("indications"));
            fromDateColumn.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
            toDateColumn.setCellValueFactory(new PropertyValueFactory<>("toDate"));

        }
    }

    @FXML
    private void reportMedicine(ActionEvent event){
        // Ottieni la prescrizione selezionata dalla tabella
        Prescrizione selectedPrescription = tableView.getSelectionModel().getSelectedItem();

        if (selectedPrescription != null) {
            // Azzerare l'assunzione per la prescrizione selezionata
            selectedPrescription.setAssumption(0);

            // Chiamare reportPrescription per aggiornare il campo assumption nel database
            boolean reportGenerated = selectedPrescription.reportPrescription();

            if (reportGenerated) {
                // Se il report è stato generato con successo, aggiorna la tabella
                // Rimuovi la prescrizione dalla tabella e aggiungila nuovamente
                tableView.getItems().remove(selectedPrescription);

                // Notifica all'utente che il report è stato generato e il conteggio di assumption è stato azzerato
                System.out.println("Report generato e conteggio di assumption azzerato per la prescrizione selezionata.");
            } else {
                // Se il report non è stato generato con successo, mostra un messaggio di errore
                System.out.println("Errore durante la generazione del report della prescrizione.");
           }
        } else {
            // Se nessuna prescrizione è selezionata, mostra un messaggio di avviso
            System.out.println("Seleziona una prescrizione per generare il report.");
        }
    }
}
