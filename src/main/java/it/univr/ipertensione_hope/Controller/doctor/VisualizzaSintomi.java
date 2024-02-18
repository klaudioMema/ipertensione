package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Model.Prescrizione;
import it.univr.ipertensione_hope.Model.Sintomo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class VisualizzaSintomi implements Initializable {

    @FXML private TableView<Sintomo> sintomiTable;
    @FXML private TableColumn<Sintomo, String> descrizioneColumn = new TableColumn<>("descrizioneColumn");
    @FXML private TableColumn<Sintomo, String> tipologiaColumn = new TableColumn<>("tipologiaColumn");
    @FXML private TableColumn<Sintomo, Integer> gravitaColumn = new TableColumn<>("gravitaColumn");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        descrizioneColumn.setCellValueFactory(cellData -> cellData.getValue().descrizioneProperty());
        tipologiaColumn.setCellValueFactory(cellData -> cellData.getValue().tipologiaProperty());
        gravitaColumn.setCellValueFactory(cellData -> cellData.getValue().gravitaProperty().asObject());

        // Ottenere gli ID dei sintomi del paziente selezionato dal DoctorAppData
        int patientId = DoctorAppData.getInstance().getSelectedPatient().getPatientId();

        // Ottenere i sintomi del paziente dal database
        Sintomo[] symptoms = Sintomo.getAllByPatient(patientId);

        // Aggiungere i sintomi alla tabella
        sintomiTable.getItems().addAll(symptoms);
    }
}
