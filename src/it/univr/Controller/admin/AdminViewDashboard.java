package it.univr.Controller.admin;

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


public class AdminViewDashboard {


    @FXML
    private Label usernameLabel;
    @FXML
    private HBox contentArea;

    public void displayName(String name){
        usernameLabel.setText(name);
    }

    // Register a new Patient
    @FXML
    private void registerPatientButton(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../../View/admin/RegisterPatient.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        contentArea.setAlignment(Pos.CENTER);
    }

    // Manage an existing Patient
    @FXML
    private void managePatient(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../../View/admin/ManagePatient.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        contentArea.setAlignment(Pos.CENTER);
    }

    @FXML
    private void registerDoctor(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../../View/admin/RegisterDoctor.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        contentArea.setAlignment(Pos.CENTER);
    }

    @FXML
    private void manageDoctor(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../../View/admin/ManageDoctor.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        contentArea.setAlignment(Pos.CENTER);
    }

    public void logoutEvent(ActionEvent event) throws IOException {
        Parent fxml =  FXMLLoader.load(getClass().getResource("../../View/LoginPageView.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxml);
        stage.setScene(scene);
        stage.show();
    }

}
