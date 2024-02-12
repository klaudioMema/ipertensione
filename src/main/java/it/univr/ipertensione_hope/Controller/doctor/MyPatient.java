package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Controller.DatabaseController;
import it.univr.ipertensione_hope.Model.Paziente;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField codiceFField;
    @FXML
    private DatePicker bDayField;
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
        /*
        try {
            selectedPaziente = Paziente.getInstance();
            nameField.setText(selectedPaziente.getName());
            surnameField.setText(selectedPaziente.getSurname());
            codiceFField.setText(selectedPaziente.getCodiceF());
            bDayField.setValue(selectedPaziente.getbDay().toLocalDate());
            fattoriDiRischio.setText(selectedPaziente.getFattoriDiRischio());
        } catch (NullPointerException e){
            errorRectangle.setVisible(true);
            selectPatientLabel.setVisible(true);
        }

         */

    }

    @FXML
    public void save(ActionEvent event) {
        try {
            if (nameField.getText().equals("") || surnameField.getText().equals("") ||
                    codiceFField.getText().equals("") || bDayField.getValue() == null) {
                statusLabel.setText("Please Fill in all data before saving");
                statusLabel.setVisible(true);
                // Codice Fiscale needs to be valid
            } else if (!checkCF()) {
                statusLabel.setText("Codice fiscale is not valid");
                statusLabel.setVisible(true);
                // birthdate check
            } else if (bDayField.getValue().getYear() < 1900 || bDayField.getValue().getYear() > LocalDate.now().getYear()) {
                statusLabel.setText("Birthdate is not valid");
                statusLabel.setVisible(true);
            } else {
                if (DatabaseController.updateItem("UPDATE patients SET name = '" + nameField.getText() +
                        "', surname = '" + surnameField.getText() + "', codiceF = '" + codiceFField.getText() +
                        "', bday = '" + Date.valueOf(bDayField.getValue()) + "', fattoridirischio = '" + fattoriDiRischio.getText() +
                        "' WHERE user_id = '" + selectedPaziente.getPatientId() + "'")) {
                    statusLabel.setVisible(true);
                    statusLabel.setText("Update Successful!");
                } else {
                    statusLabel.setVisible(true);
                    statusLabel.setText("Couldn't update data!");
                }
            }
        } catch (DateTimeParseException e){
            e.printStackTrace();
            statusLabel.setVisible(true);
            statusLabel.setText("Insert a valid birthdate!");
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
