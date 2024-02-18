package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Model.BloodPressureCategory;
import it.univr.ipertensione_hope.Model.BloodPressureData;
import it.univr.ipertensione_hope.Model.Paziente;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PazientiGravi implements Initializable{
    @FXML
    private TableView<BloodPressureData> pressureTableView;
    @FXML
    private TableColumn<BloodPressureData, String> patientColumn;
    @FXML
    private TableColumn<BloodPressureData, String> dateColumn;
    @FXML
    private TableColumn<BloodPressureData, Integer> systolicColumn;
    @FXML
    private TableColumn<BloodPressureData, Integer> diastolicColumn;
    @FXML
    private TableColumn<BloodPressureData, String> categoryColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // ottengo tutti i pazienti del medico
        Paziente[] pazienti = Paziente.getAllByDoctor(DoctorAppData.getInstance().getMedicoLoggato());
        ArrayList<BloodPressureData> datiPazienti = new ArrayList<>(); // memorizza i dati di pressione di tutti i pazienti

        // carico in datiPazienti tutti i dati di pressione gravi
        for(Paziente paziente : pazienti) {
            BloodPressureData[] datiPressione = BloodPressureData.getAllByPatient(paziente.getPatientId());
            for(BloodPressureData data : datiPressione) {
                if(data.classifyBloodPressure().getValue() > 0) {
                    datiPazienti.add(data);
                }
            }
        }

        patientColumn.setCellValueFactory(cellData -> {
            // prendo il paziente che fa riferimento a questo dato di pressione
            Paziente paziente = Paziente.getById(cellData.getValue().getUserId());
            return new SimpleObjectProperty<>(paziente.getNome() + " " + paziente.getCognome());
        });

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        systolicColumn.setCellValueFactory(new PropertyValueFactory<>("systolic"));
        diastolicColumn.setCellValueFactory(new PropertyValueFactory<>("diastolic"));
        categoryColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().classifyBloodPressure().getDescription()));

        pressureTableView.getItems().addAll(datiPazienti);
    }
}
