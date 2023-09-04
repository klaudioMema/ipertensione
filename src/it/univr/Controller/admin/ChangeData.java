package it.univr.Controller.admin;

import it.univr.Controller.DatabaseController;
import it.univr.Model.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class ChangeData implements Initializable {
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField codiceFField;
    @FXML
    private DatePicker bDayField;
    private Patient selectedPatient;
    @FXML
    private Label statusLabel;
    private ManagePatient managePatientPatientController;

    // Set controller
    public void setManagePatientController(ManagePatient managePatientPatientController){
        this.managePatientPatientController = managePatientPatientController;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedPatient = Patient.getInstance();
        nameField.setText(selectedPatient.getName());
        surnameField.setText(selectedPatient.getSurname());
        emailField.setText(selectedPatient.getEmail());
        codiceFField.setText(selectedPatient.getCodiceF());
        bDayField.setValue(selectedPatient.getbDay().toLocalDate());
    }


    @FXML
    private void saveAndExit(ActionEvent event){
        try {

            if (bDayField.getValue().getYear() < 1900 || bDayField.getValue().getYear() > LocalDate.now().getYear()) {
                statusLabel.setVisible(true);
            } else {
                if (DatabaseController.updateItem("UPDATE patients SET name = '" + nameField.getText() +
                        "', surname = '" + surnameField.getText() +
                        "', email = '" + emailField.getText() +
                        "', bday = '" + Date.valueOf(bDayField.getValue()) +
                        "', codicef = '" + codiceFField.getText() +
                        "' WHERE user_id = '" + selectedPatient.getPatientId() + "'")) {

                    selectedPatient.setName(nameField.getText());
                    selectedPatient.setSurname(surnameField.getText());
                    selectedPatient.setEmail(emailField.getText());
                    selectedPatient.setbDay(Date.valueOf(bDayField.getValue()));
                    selectedPatient.setCodiceF(codiceFField.getText());
                    ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                    managePatientPatientController.setStatusLabel("Update Successful!");
                } else {
                    managePatientPatientController.setStatusLabel("Update not Successful!");
                }
            }
        } catch (DateTimeParseException e){
            statusLabel.setText("Not a valid date");
            statusLabel.setVisible(true);
        }

    }
}
