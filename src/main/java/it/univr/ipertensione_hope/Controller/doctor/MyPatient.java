package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Controller.DatabaseController;
import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.Paziente;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MyPatient implements Initializable {

    @FXML
    private Label nameField;
    @FXML
    private Label surnameField;
    @FXML
    private Label codiceFField;
    @FXML
    private Label bDayField;
    @FXML
    private TextArea fattoriDiRischio;
    @FXML
    private Label statusLabel;
    @FXML
    private Label selectPatientLabel;

    private Paziente selectedPaziente;
    @FXML
    private Rectangle errorRectangle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (DoctorAppData.getInstance().getSelectedPatient()== null)
            Functions.alert("Paziente non selezionato", Alert.AlertType.ERROR, null);
        else{
            nameField.setText(selectedPaziente.getNome());
            surnameField.setText(selectedPaziente.getCognome());
            codiceFField.setText(selectedPaziente.getCodiceF());
            bDayField.setText((selectedPaziente.getbDay().toString()));
            fattoriDiRischio.setText(selectedPaziente.getFattoriDiRischio());
        }
    }


    @FXML
    public void viewPrescriptions(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WindowsManager.mainClass.getResource("../../View/doctor/Prescriptions.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            // Specifies the owner Window (parent) for new window
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.setTitle("BloodMonitor - Prescriptions");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void viewBloodPressureData(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WindowsManager.mainClass.getResource("../../View/BloodPressure.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            // Specifies the owner Window (parent) for new window
            stage.setScene(new Scene(root1));
            stage.setTitle("BloodMonitor - Patient's Blood Pressure Data");
            stage.setResizable(false);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void viewReportedSymptoms(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WindowsManager.mainClass.getResource("../../View/doctor/ViewSymptomsDoctor.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            // Specifies the owner Window (parent) for new window
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.setTitle("BloodMonitor - Reported Symptoms");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkCF(){
        String regex = "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$";
        Matcher matcher = Pattern.compile(regex).matcher(codiceFField.getText());
        return matcher.matches();
    }

}
