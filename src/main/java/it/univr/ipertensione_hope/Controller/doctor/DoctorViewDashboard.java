package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Controller.DatabaseController;
import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.Medico;
import it.univr.ipertensione_hope.Model.Paziente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class DoctorViewDashboard implements Initializable {
    @FXML
    private Label usernameLabel;
    @FXML
    private HBox contentArea;
    @FXML
    private Label PatientLabel;
    @FXML
    private Label SelectPatient;
    @FXML
    private Label welcomeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Medico medico = DoctorAppData.getInstance().getMedicoLoggato();
        Paziente pazienteSelezionato = DoctorAppData.getInstance().getSelectedPatient();

        welcomeLabel.setText("Benvenuto " + medico.getNome());
        if(pazienteSelezionato != null) {
            PatientLabel.setText("Paziente selezionato: " + pazienteSelezionato.getNome() + " " + pazienteSelezionato.getCognome());
        } else {
            PatientLabel.setText("Nessun paziente selezionato");
        }
    }
}
