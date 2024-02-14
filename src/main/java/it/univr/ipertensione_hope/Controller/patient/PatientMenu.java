package it.univr.ipertensione_hope.Controller.patient;


import it.univr.ipertensione_hope.Controller.LoginPage;
import it.univr.ipertensione_hope.Model.Paziente;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientMenu {

    private static final String directory = "patient/";

    public void home() throws IOException {
        String path = directory + "HomePage.fxml";
        WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);

    }

    public void myProfile(ActionEvent event) throws IOException {
        String path = directory + "MyProfile.fxml";
        WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);

    }

    public void bloodData(ActionEvent event) throws IOException {
        String path = directory + "BloodPressure.fxml";
        WindowsManager.loadPage(WindowsManager.mainClass.getResource(path), path);

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
        WindowsManager.logout(WindowsManager.mainClass.getResource(path), path);

    }
}
