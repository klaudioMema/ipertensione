package it.univr.ipertensione_hope.Controller.patient;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

        // Aggiungi un listener per ripristinare il valore del tick più vicino quando viene rilasciato il mouse
        gravitaSlider.setOnMouseReleased(event -> {
            double value = gravitaSlider.getValue();
            int roundedValue = (int) Math.round(value); // Arrotonda il valore
            gravitaSlider.setValue(roundedValue); // Imposta il valore arrotondato
        });
    }

    @FXML
    private void submitSymptom() {
        String tipologia = tipologiaField.getText();
        String descrizione = descrizioneArea.getText();
        int gravita = (int) gravitaSlider.getValue();

        // Qui puoi gestire l'invio dei dati del sintomo al database o fare altre operazioni necessarie
        System.out.println("Tipologia: " + tipologia + ", Descrizione: " + descrizione + ", Gravità: " + gravita);
    }
}