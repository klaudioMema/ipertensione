package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DoctorMenu {

    private static final String directory = "doctor/";

    // Controlla se il paziente è stato selezionato prima di caricare la pagina
    private void checkPatientSelected(String path) {
        if(DoctorAppData.getInstance().getSelectedPatient() == null) {
            Functions.alert("Prima devi selezionare un paziente", Alert.AlertType.ERROR, (ButtonType button) -> {
                selectPatient(new ActionEvent());
            });
        } else {
            WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);
            WindowsManager.reloadPage();
        }
    }

    @FXML
    private void myPatient(ActionEvent event) {
        String path = directory + "MyPatient.fxml";
        checkPatientSelected(path);
    }

    @FXML
    private void selectPatient(ActionEvent event) {
        String path = directory + "SelectPatient.fxml";
        WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);
    }

    @FXML
    private void alertPatient(ActionEvent event) {
        String path = directory + "AlertPatient.fxml";
        checkPatientSelected(path);
    }

    @FXML
    private void prescription(ActionEvent event) {
        String path = directory + "Prescriptions.fxml";
        checkPatientSelected(path);
    }

    @FXML
    private void managePrescription(ActionEvent event) {
        String path = directory + "ManagePrescription.fxml";
        checkPatientSelected(path);

    }

    @FXML
    public void logoutEvent(ActionEvent event) {
        String path = "LoginPageView.fxml";
        DoctorAppData.getInstance().setMedicoLoggato(null);
        WindowsManager.logout(WindowsManager.mainClass.getResource(path), path);
    }

    @FXML
    public void pressureData(ActionEvent event) {
        String path = directory + "PressureData.fxml";
        checkPatientSelected(path);
    }

    @FXML
    private void checkPrescription(ActionEvent event) {
        String path = directory + "ControllaPrescrizioni.fxml";
        checkPatientSelected(path);
    }

    @FXML
    private void visualizzaSintomi(ActionEvent event) {
        String path = directory + "VisualizzaSintomi.fxml";
        checkPatientSelected(path);
    }
}
