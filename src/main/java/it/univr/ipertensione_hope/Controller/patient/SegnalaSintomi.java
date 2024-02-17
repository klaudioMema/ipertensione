package it.univr.ipertensione_hope.Controller.patient;

import it.univr.ipertensione_hope.Functions;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SegnalaSintomi{

    @FXML
    private TextField tipologiaField;

    @FXML
    private TextArea descrizioneArea;

    @FXML
    private Slider gravitaSlider;

    @FXML
    private Label gravitaLabel;

    @FXML
    private void initialize() {
        gravitaSlider.setMin(1);
        gravitaSlider.setMax(10);
        gravitaSlider.setMajorTickUnit(1);
        gravitaSlider.setBlockIncrement(1);
        gravitaSlider.setSnapToTicks(true);

        gravitaSlider.setShowTickLabels(true);
        gravitaSlider.setShowTickMarks(true);

        gravitaLabel.setText(String.valueOf(gravitaSlider.getValue()));

        gravitaSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            gravitaLabel.setText(String.valueOf(newValue.intValue()));
        });

        gravitaSlider.setOnMouseReleased(event -> {
            double value = gravitaSlider.getValue();
            int roundedValue = (int) Math.round(value); // Arrotonda il valore
            gravitaSlider.setValue(roundedValue); // Imposta il valore arrotondato
        });
    }

    @FXML
    private void submitSymptom() {
        String tipoSintomo = tipologiaField.getText().trim();
        String descrizione = descrizioneArea.getText().trim();

        if (tipoSintomo.isEmpty() || descrizione.isEmpty()) {
            // Mostra un messaggio di errore se uno dei campi è vuoto
            Functions.alert("Per favore, compila tutti i campi.", Alert.AlertType.ERROR, null);
            return;
        }

        int gravita = (int) gravitaSlider.getValue(); // Converte il valore in intero

        // Ora puoi procedere con l'aggiunta del sintomo al database
        // Esegui la query o chiama il metodo per l'aggiunta del sintomo al database
    }
}
