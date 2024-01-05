package it.univr.Controller.admin;

import it.univr.Controller.WindowsManager;
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
import java.net.URL;

import it.univr.Model.AgenteSanitario;


public class AdminViewDashboard {

    @FXML
    private Label usernameLabel;
    @FXML
    private HBox contentArea;
    AgenteSanitario admin = null;
    public AdminViewDashboard(){
    }
    public AdminViewDashboard(AgenteSanitario admin) {
        this.admin = admin;
    }
    public void setAdmin(AgenteSanitario admin) {
        this.admin = admin;
    }
    public AgenteSanitario getAdmin() {
        return this.admin;
    }
    public void displayName(String name){
        usernameLabel.setText(name);
    }
    @FXML
    private void registerPatientButton(ActionEvent event) {
        loadPage(getClass().getResource("../../View/admin/RegisterPatient.fxml"));
    }
    @FXML
    private void managePatient(ActionEvent event) {
        loadPage(getClass().getResource("../../View/admin/ManagePatient.fxml"));
    }
    @FXML
    private void registerDoctor(ActionEvent event) {
        loadPage(getClass().getResource("../../View/admin/RegisterDoctor.fxml"));
    }

    @FXML
    private void manageDoctor(ActionEvent event) {
        loadPage(getClass().getResource("../../View/admin/ManageDoctor.fxml"));
    }
    public void logoutEvent(ActionEvent event) throws IOException {
        URL location = getClass().getResource("../../View/LoginPageView.fxml");
        if(location == null) {
            System.out.println("Impossibile caricare la pagina di login");
        } else {
            Parent root = FXMLLoader.load(location);
            WindowsManager.setScene(new Scene(root));
            WindowsManager.getMainStage().show();
        }
    }
    private void loadPage(URL location) {
        try {
            Parent fxml = FXMLLoader.load(location);
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
            contentArea.setAlignment(Pos.CENTER);
        } catch (IOException e) {
            System.out.println("Impossibile caricare la pagina");
        }
    }
}
