package it.univr.ipertensione_hope.Controller.patient;

import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.BloodPressureData;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import java.io.IOException;

public class PatientMenu {

    private static final String directory = "patient/";

    public void myProfile(ActionEvent event) throws IOException {
        String path = directory + "MyProfile.fxml";
        WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);
    }

    public void myDoctor(ActionEvent event) throws IOException {
        String path = directory + "MyDoctor.fxml";
        WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);
    }

   public void bloodData(ActionEvent event) throws IOException {
        String path = directory + "ReportBloodPressure.fxml";
        if(BloodPressureData.hasReportedToday(PatientAppData.getInstance().getLoggedPatient())) {
            Functions.alert("Hai già riportato i dati di pressione per oggi", Alert.AlertType.INFORMATION, null);
        } else {
            WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);
        }
    }

    public void therapies(ActionEvent event) throws IOException {
        String path = directory + "Medications.fxml";
        WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);
    }

    public void reportSymptoms(ActionEvent event) throws IOException {
        String path = directory + "ViewSymptomsPatient.fxml";
        WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);
    }

    public void logoutEvent(ActionEvent event) throws IOException {
        String path = directory + "LoginPageView.fxml";
        PatientAppData.getInstance().setLoggedPatient(null);
        WindowsManager.logout(WindowsManager.mainClass.getResource(path), path);
    }
}
