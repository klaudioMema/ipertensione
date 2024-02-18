package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Model.BloodPressureData;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.util.*;
import java.time.temporal.WeekFields;
import it.univr.ipertensione_hope.Model.Paziente;

public class PressureData {

    @FXML
    private ChoiceBox<String> modeChoiceBox;
    @FXML
    private Label patientLabel;
    @FXML
    private TextField patientIdField;
    @FXML
    private LineChart<String, Number> pressureChart;

    private Map<String, Double> weeklyAvgMap = new LinkedHashMap<>();
    private Map<String, Double> monthlyAvgMap = new LinkedHashMap<>();

    @FXML
    private void initialize() {
        modeChoiceBox.setOnAction(event -> updatePressureChart());
        modeChoiceBox.getItems().addAll("Giornaliera", "Settimanale", "Mensile");
        modeChoiceBox.setValue("Giornaliera"); // Modalità predefinita

        // Inizializzazione dei dati di pressione
        updatePressureData();

        Paziente selectedPaziente = DoctorAppData.getInstance().getSelectedPatient();

        if(selectedPaziente != null) {
            patientLabel.setText("Paziente selezionato: " + selectedPaziente);
        } else {
            patientLabel.setText("Nessun paziente selezionato");
        }
    }

    private void updatePressureChart() {
        String selectedMode = modeChoiceBox.getValue();
        if ("Settimanale".equals(selectedMode)) {
            calculateWeeklyAverage();
            displayWeeklyAverage();
        } else if ("Mensile".equals(selectedMode)) {
            calculateMonthlyAverage();
            displayMonthlyAverage();
        } else {
            updatePressureData(); // Visualizza i dati giornalieri di default
        }
    }

    private void calculateWeeklyAverage() {
        weeklyAvgMap.clear();

        // La funzione che calcola la media ipotizza che i dati
        // siano ordinati in base alla data, quindi li riordiniamo
        BloodPressureData[] pressureData = BloodPressureData.getAllByPatient(DoctorAppData.getInstance().getSelectedPatient().getPatientId());
        Arrays.sort(pressureData, Comparator.comparing(BloodPressureData::getDate));

        if (pressureData.length > 0) {
            int weekCount = 0;
            double weekTotalSBP = 0;
            double weekTotalDBP = 0;
            LocalDate currentDate = pressureData[0].getDate();
            int currentWeek = currentDate.get(WeekFields.ISO.weekOfWeekBasedYear());

            for (BloodPressureData data : pressureData) {
                int week = data.getDate().get(WeekFields.ISO.weekOfWeekBasedYear());
                if (week == currentWeek) {
                    weekTotalSBP += data.getSBP();
                    weekTotalDBP += data.getDBP();
                    weekCount++;
                } else {
                    // Aggiungi la media settimanale al map
                    double avgSBP = weekTotalSBP / weekCount;
                    double avgDBP = weekTotalDBP / weekCount;
                    weeklyAvgMap.put("Week " + currentWeek, (avgSBP + avgDBP) / 2);

                    // Resetta le variabili per la nuova settimana
                    currentDate = data.getDate();
                    currentWeek = week;
                    weekTotalSBP = data.getSBP();
                    weekTotalDBP = data.getDBP();
                    weekCount = 1;
                }
            }

            // Aggiungi l'ultima settimana al map
            double avgSBP = weekTotalSBP / weekCount;
            double avgDBP = weekTotalDBP / weekCount;
            weeklyAvgMap.put("Week " + currentWeek, (avgSBP + avgDBP) / 2);
        }
    }

    private void calculateMonthlyAverage() {
        monthlyAvgMap.clear();

        // La funzione che calcola la media ipotizza che i dati
        // siano ordinati in base alla data, quindi li riordiniamo
        BloodPressureData[] pressureData = BloodPressureData.getAllByPatient(DoctorAppData.getInstance().getSelectedPatient().getPatientId());
        Arrays.sort(pressureData, Comparator.comparing(BloodPressureData::getDate));

        if (pressureData.length > 0) {
            int monthCount = 0;
            double monthTotalSBP = 0;
            double monthTotalDBP = 0;
            LocalDate currentDate = pressureData[0].getDate();
            int currentMonth = currentDate.getMonthValue();

            for (BloodPressureData data : pressureData) {
                int month = data.getDate().getMonthValue();
                if (month == currentMonth) {
                    monthTotalSBP += data.getSBP();
                    monthTotalDBP += data.getDBP();
                    monthCount ++;
                } else {
                    // Aggiungi la media mensile al map
                    double avgSBP = monthTotalSBP / monthCount;
                    double avgDBP = monthTotalDBP / monthCount;
                    monthlyAvgMap.put(currentDate.getMonth().toString(), (avgSBP + avgDBP) / 2);

                    // Resetta le variabili per il nuovo mese
                    currentDate = data.getDate();
                    currentMonth = month;
                    monthTotalSBP = data.getSBP();
                    monthTotalDBP = data.getDBP();
                    monthCount = 1;
                }
            }

            // Aggiungi l'ultimo mese al map
            double avgSBP = monthTotalSBP / monthCount;
            double avgDBP = monthTotalDBP / monthCount;
            monthlyAvgMap.put(currentDate.getMonth().toString(), (avgSBP + avgDBP) / 2);
        }
    }

    private void displayWeeklyAverage() {
        XYChart.Series<String, Number> weeklySeries = new XYChart.Series<>();
        weeklySeries.setName("Weekly Average");

        // Aggiungi i dati settimanali alla serie
        for (Map.Entry<String, Double> entry : weeklyAvgMap.entrySet()) {
            weeklySeries.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        // Aggiungi la serie alla chart
        pressureChart.getData().clear(); // Pulisci la chart
        pressureChart.getData().add(weeklySeries);
    }

    private void displayMonthlyAverage() {
        XYChart.Series<String, Number> monthlySeries = new XYChart.Series<>();
        monthlySeries.setName("Monthly Average");

        // Aggiungi i dati mensili alla serie
        for (Map.Entry<String, Double> entry : monthlyAvgMap.entrySet()) {
            monthlySeries.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        // Aggiungi la serie alla chart
        pressureChart.getData().clear(); // Pulisci la chart
        pressureChart.getData().add(monthlySeries);
    }

    @FXML
    private void updatePressureData() {
        int patientId = DoctorAppData.getInstance().getSelectedPatient().getPatientId();

        // Ottieni i dati di pressione del paziente
        BloodPressureData[] pressureData = BloodPressureData.getAllByPatient(patientId);

        // Crea una serie di dati per la pressione sistolica
        XYChart.Series<String, Number> sbpSeries = new XYChart.Series<>();
        sbpSeries.setName("Systolic Pressure");

        // Crea una serie di dati per la pressione diastolica
        XYChart.Series<String, Number> dbpSeries = new XYChart.Series<>();
        dbpSeries.setName("Diastolic Pressure");

        // Aggiungi i dati alla serie
        for (BloodPressureData data : pressureData) {
            String date = data.getDate().toString();
            int sbp = data.getSBP();
            int dbp = data.getDBP();

            // Aggiungi i dati alla serie per la pressione sistolica
            sbpSeries.getData().add(new XYChart.Data<>(date, sbp));

            // Aggiungi i dati alla serie per la pressione diastolica
            dbpSeries.getData().add(new XYChart.Data<>(date, dbp));
        }

        // Aggiungi le serie alla chart
        pressureChart.getData().clear(); // Pulisci la chart
        pressureChart.getData().addAll(sbpSeries, dbpSeries);
    }
}
