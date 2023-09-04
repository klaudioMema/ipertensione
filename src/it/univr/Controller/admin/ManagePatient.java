package it.univr.Controller.admin;

import it.univr.Controller.DatabaseController;
import it.univr.Controller.Search;
import it.univr.Model.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class ManagePatient extends Search implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField codiceFField;
    @FXML
    private Label statusLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Patient.getInstance().getPatientId() != 0){
            setTextSelectedPatient();
        }
    }

    // Select a patient
    @FXML
    private void searchPatient(ActionEvent event) throws SQLException, ParseException {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String codiceF = codiceFField.getText();
        if ((name.equals("") || surname.equals("")) && codiceF.equals("")) {
            statusLabel.setVisible(true);
            statusLabel.setText("Please fill in the data first");
        } else {
            if (!searchPatientDB(name, surname, codiceF)) {
                statusLabel.setVisible(true);
                setStatusLabel("Patient Not Found!");
            } else {
                statusLabel.setVisible(true);
                setStatusLabel("Patient Selected!");
                setTextSelectedPatient();
            }
        }
    }

    // Open a new window
    @FXML
    private void changeData(ActionEvent event) {
        if (Patient.getInstance().getPatientId() == 0) {
            statusLabel.setVisible(true);
            setStatusLabel("Please search a patient first");
        } else {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../View/admin/ChangeData.fxml"));
                    Parent root1 = fxmlLoader.load();
                    // Pass controller to new window
                    ChangeData changeData = fxmlLoader.getController();
                    changeData.setManagePatientController(this);

                    Stage stage = new Stage();
                    stage.initModality(Modality.WINDOW_MODAL);
                    // Specifies the owner Window (parent) for new window
                    stage.setScene(new Scene(root1));
                    stage.setResizable(false);
                    stage.setTitle("BloodMonitor - Change Patient Data");
                    stage.show();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
    }

    // Change the doctor of a patient
    @FXML
    private void changeDoctor(ActionEvent event) {
        if (Patient.getInstance().getPatientId() == 0) {
            statusLabel.setVisible(true);
            setStatusLabel("Please search a patient first");
        } else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../View/admin/ChooseDoctor.fxml"));
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                // Specifies the owner Window (parent) for new window
                stage.setScene(new Scene(root1));
                stage.setResizable(false);
                stage.setTitle("BloodMonitor - Choose Doctor");
                stage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Remove a patient from DB
    @FXML
    private void removePatient(ActionEvent event){
        if(Patient.getInstance().getPatientId() == 0) {
            statusLabel.setVisible(true);
            setStatusLabel("Please search a patient first");
        } else {
            try {
                DatabaseController.updateItem("DELETE FROM patients " + "WHERE user_id = '" + Patient.getInstance().getPatientId() + "'");
                statusLabel.setText("Patient removed!");
            } catch (Exception e) {
                e.printStackTrace();
                statusLabel.setText("Couldn't remove patient!");
            }
        }
    }


    private void setTextSelectedPatient(){
        nameField.setText(Patient.getInstance().getName());
        surnameField.setText(Patient.getInstance().getSurname());
        codiceFField.setText(Patient.getInstance().getCodiceF());
    }

    public void setStatusLabel(String message) {
        statusLabel.setText(message);
    }
}
