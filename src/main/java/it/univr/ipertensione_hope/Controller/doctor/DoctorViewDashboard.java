package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Controller.DatabaseController;
import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.Paziente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class DoctorViewDashboard  {
    @FXML
    private Label usernameLabel;
    @FXML
    private HBox contentArea;
    @FXML
    private Label PatientLabel;
    @FXML
    private Label SelectPatient;
    private Paziente selectedPaziente;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedPaziente=DoctorAppData.getInstance().getSelectedPatient();
        if ( selectedPaziente == null)
            Functions.alert("Paziente non selezionato", Alert.AlertType.ERROR, null);
        else{
            SelectPatient.setText(selectedPaziente.getNome() +" "+ selectedPaziente.getCognome());

        }
    }

    public void displayName(String name){
        // visualizzare il nome del medico da qualche parte
    }

    public void checkMedication() {
        /*

        // prendere tutte le prescrizioni dalla tabella
        String query = "SELECT * FROM prescriptions";
        ResultSet rs = DatabaseController.getResultSet(query); // lista prescrizioni
        ResultSet rs_medication; // lista medicazioni relative ad una prescrizione
        ResultSet rs_alert; // lista alert relativa ad una prescrizione
        int index = 0;
        int prescription_id;
        Date date_prescription;
        Date last_alert_date;
        Date current_date; // data corrente del controllo delle medicazioni
        Date today = new Date(System.currentTimeMillis());
        int prescription_days;
        int counter = 0;
        long diff; // differenza tra la data di oggi e la data di inizio prescrizone
        long diff_date;

        try {
            while (rs.next()) {
                date_prescription = rs.getDate("fromDate");
                prescription_id = rs.getInt("prescription_id");
                prescription_days = rs.getInt("days");

                System.out.println("controllo prescription " + prescription_id);

                query = "SELECT * FROM alert WHERE prescription_id = " + prescription_id + " order by alert_id desc limit 1";
                rs_alert = DatabaseController.getResultSet(query);

                if(rs_alert.getRow() == 0) { // se non c'è nessun alert
                    last_alert_date = date_prescription;
                } else {
                    last_alert_date = rs_alert.getDate("date");
                }


                query = "SELECT * FROM takenmedication WHERE prescription_id = " + prescription_id;
                rs_medication = DatabaseController.getResultSet(query);

                current_date = last_alert_date;

                diff = current_date.getTime() - last_alert_date.getTime();
                diff_date = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

                query = "SELECT * FROM takenmedication WHERE prescription_id = '" + prescription_id + "' AND daythatwastaken = '" + current_date + "'";

                String indication = "";

                counter = 0;
                while(current_date.compareTo(today) <= 0 && diff_date <= prescription_days) {
                    // la data della medicazione è ottenuta da fromDate + diff_date
                    rs_medication = DatabaseController.getResultSet(query);
                    if(!rs_medication.next()) {
                        counter ++;
                    } else {
                        if(counter >= 3) {
                            String query_alert = "INSERT INTO alert (type, indication, prescription_id, date) VALUES (" + 1 +
                                    ", '" + indication +
                                    "', " + prescription_id +
                                    ", '" + today +
                                    "')";
                            DatabaseController.updateItem(query_alert);
                        }
                        counter = 0;
                    }

                    System.out.println("Counter: " + counter);

                    current_date = new Date(current_date.getTime() + TimeUnit.DAYS.toMillis(1)); // controlliamo il prossimo giorno
                    diff_date ++;
                }

                // controllo nel caso in cui c'è una sequenza di giorni finale senza assunzioni
                if(counter >= 3) {
                    String query_alert = "INSERT INTO alert (type, indication, prescription_id, date) VALUES (" + 1 +
                            ", '" + indication +
                            "', " + prescription_id +
                            ", '" + today +
                            "')";
                    DatabaseController.updateItem(query_alert);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
         */
    }

}
