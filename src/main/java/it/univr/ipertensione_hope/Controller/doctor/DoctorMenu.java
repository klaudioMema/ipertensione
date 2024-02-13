package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DoctorMenu {

    private static final String directory = "doctor/";

    @FXML
    private void myPatient(ActionEvent event) {
        String path = directory + "MyPatient.fxml";
        WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);
    }

    @FXML
    private void selectPatient(ActionEvent event) {
        String path = directory + "SelectPatient.fxml";
        WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);
    }

    @FXML
    private void alertPatient(ActionEvent event) {
        String path = directory + "AlertPatient.fxml";
        WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);
    }

    @FXML
    private void prescription(ActionEvent event) {
        String path = directory + "Prescriptions.fxml";

        if(DoctorAppData.getInstance().getSelectedPatient() == null) {
            Functions.alert("Prima devi selezionare un paziente", Alert.AlertType.ERROR, (ButtonType button) -> {
                selectPatient(new ActionEvent());
            });
        } else {
            WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);
            WindowsManager.reloadPage();
        }

    }

    public void logoutEvent(ActionEvent event) {
        String path = "LoginPageView.fxml";
        WindowsManager.logout(WindowsManager.mainClass.getResource(path), path);
    }

}
