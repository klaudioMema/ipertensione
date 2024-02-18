package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Model.BloodPressureCategory;
import it.univr.ipertensione_hope.Model.BloodPressureData;
import it.univr.ipertensione_hope.Model.Paziente;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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
        systolicColumn.setCellValueFactory(new PropertyValueFactory<>("SBP"));
        diastolicColumn.setCellValueFactory(new PropertyValueFactory<>("DBP"));
        categoryColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().classifyBloodPressure().getDescription()));

        // Configura la cell factory per le righe della tabella
        pressureTableView.setRowFactory(tv -> new TableRow<BloodPressureData>() {
            @Override
            protected void updateItem(BloodPressureData item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    // Imposta il colore di sfondo predefinito
                    setStyle("");
                } else {
                    // Imposta il colore di sfondo in base alla categoria
                    switch (item.classifyBloodPressure()) {
                        case HYPERTENSIVE_CRISIS ->
                            // Colore rosso con opacità ridotta per l'ipertensione critica
                                setStyle("-fx-background-color: rgba(255, 204, 204, 0.5);");
                        case HYPERTENSION_STAGE_2 ->
                            // Colore arancione con opacità ridotta per l'ipertensione di stadio 2
                                setStyle("-fx-background-color: rgba(255, 229, 204, 0.5);");
                        case HYPERTENSION_STAGE_1 ->
                            // Colore giallo con opacità ridotta per l'ipertensione di stadio 1
                                setStyle("-fx-background-color: rgba(255, 255, 204, 0.5);");
                        case HIGH_BLOOD_PRESSURE_STAGE_1 ->
                            // Colore verde chiaro con opacità ridotta per l'ipertensione lieve
                                setStyle("-fx-background-color: rgba(204, 255, 204, 0.5);");
                        case ELEVATED ->
                            // Colore azzurro con opacità ridotta per la pressione leggermente elevata
                                setStyle("-fx-background-color: rgba(204, 229, 255, 0.5);");
                        default ->
                            // Imposta il colore di sfondo predefinito
                                setStyle("");
                    }
                }
            }
        });

        pressureTableView.getItems().addAll(datiPazienti);
    }
}
