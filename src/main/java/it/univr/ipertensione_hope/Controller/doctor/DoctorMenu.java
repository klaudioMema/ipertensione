package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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
        String path = directory + "Prescription.fxml";
        WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);
    }

    public void logoutEvent(ActionEvent event) {
        String path = "../../View/LoginPageView.fxml";
        WindowsManager.logout(WindowsManager.mainClass.getResource(path), path);
    }

}
