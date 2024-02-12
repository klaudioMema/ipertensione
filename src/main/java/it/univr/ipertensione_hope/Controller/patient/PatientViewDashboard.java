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

public class PatientViewDashboard{
    @FXML
    public Label usernameLabel;
    @FXML
    public HBox contentArea;


    public void home() throws IOException {
        Parent fxml = FXMLLoader.load(WindowsManager.mainClass.getResource("../../View/patient/HomePage.fxml"));
        //LoginPage.changeWidth(620);
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        contentArea.setAlignment(Pos.CENTER);
    }

    public void myProfile(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(WindowsManager.mainClass.getResource("../../View/patient/MyProfile.fxml"));
        //LoginPage.changeWidth(620);
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        contentArea.setAlignment(Pos.CENTER);
    }

    public void bloodData(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(WindowsManager.mainClass.getResource("../../View/BloodPressure.fxml"));
        //LoginPage.changeWidth(780);
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        contentArea.setAlignment(Pos.CENTER);
    }

    public void therapies(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(WindowsManager.mainClass.getResource("../../View/patient/Medications.fxml"));
        //LoginPage.changeWidth(780);
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        contentArea.setAlignment(Pos.CENTER);
    }

    public void reportSymptoms(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(WindowsManager.mainClass.getResource("../../View/patient/ViewSymptomsPatient.fxml"));
        //LoginPage.changeWidth(780);
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        contentArea.setAlignment(Pos.CENTER);
    }

    public void logoutEvent(ActionEvent event) throws IOException {
        Parent fxml =  FXMLLoader.load(WindowsManager.mainClass.getResource("../../../../../../ipertensione_/src/LoginPageView.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxml);
        stage.setScene(scene);
        stage.show();
    }


}
