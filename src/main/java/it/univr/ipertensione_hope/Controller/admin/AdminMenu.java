package it.univr.ipertensione_hope.Controller.admin;

import it.univr.ipertensione_hope.Controller.doctor.DoctorAppData;
import it.univr.ipertensione_hope.Model.AgenteSanitario;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AdminMenu {

    private final String directory = "admin/";

    @FXML
    private void registerPatient(ActionEvent event) {
        String path = directory + "RegisterPatient.fxml";
        WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);
        WindowsManager.reloadPage();
    }

    @FXML
    private void managePatient(ActionEvent event) {
        String path = directory + "SelectPatient.fxml";
        WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);
        WindowsManager.reloadPage();
    }

    @FXML
    private void registerDoctor(ActionEvent event) {
        String path = directory + "RegisterDoctor.fxml";
        WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);
        WindowsManager.reloadPage();
    }

    @FXML
    private void manageDoctor(ActionEvent event) {
        String path = directory + "SelectDoctor.fxml";
        WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);
        WindowsManager.reloadPage();
    }

    public void logoutEvent(ActionEvent event) {
        String path = "LoginPageView.fxml";
        AdminAppData.getInstance().setLoggedAgenteSanitario(null);
        WindowsManager.logout(WindowsManager.mainClass.getResource(path), path);
    }
}
