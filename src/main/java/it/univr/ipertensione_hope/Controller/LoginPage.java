package it.univr.ipertensione_hope.Controller;

import it.univr.ipertensione_hope.Controller.doctor.DoctorAppData;
import it.univr.ipertensione_hope.Controller.patient.PatientAppData;
import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Controller.admin.AdminViewDashboard;
import it.univr.ipertensione_hope.Controller.doctor.DoctorViewDashboard;
import it.univr.ipertensione_hope.Controller.patient.PatientViewDashboard;
import it.univr.ipertensione_hope.Model.*;
import it.univr.ipertensione_hope.View.WindowsManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPage implements Initializable {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private CheckBox login_selectShowPass;
    @FXML
    private TextField login_showPassword;
    @FXML
    private ChoiceBox<String> changePass_choiceBox;

    private final String[] users = {"ADMIN", "PAZIENTE", "MEDICO"};
    private Parent root;
    private final String location = ""; // posizione della view

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(users);
    }

    @FXML
    private void login(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String userType = choiceBox.getValue();
        User user = null; // utente da cercare nel database

        if (choiceBox.getValue() == null) {
            Functions.alert("Seleziona il tipo di utente", Alert.AlertType.ERROR, null);
        } else {
            switch(UserType.valueOf(userType)){
                case ADMIN -> {
                    user = AgenteSanitario.findUserDB(username, password);
                    if(user == null) {
                        System.out.println("Account non trovato");
                        Functions.alert("Email o password errati", Alert.AlertType.ERROR, null);
                    } else {
                        loadAdminDashboard((AgenteSanitario) user, event);
                    }
                }
                case MEDICO -> {
                    user = Medico.findUserDB(username, password);
                    if(user == null) {
                        System.out.println("Account non trovato");
                        Functions.alert("Email o passwor errati", Alert.AlertType.ERROR, null);
                    } else {
                        loadDoctorDashboard((Medico) user, event);
                    }
                }
                case PAZIENTE -> {
                    user = Paziente.findUserDB(username, password);
                    if(user == null) {
                        System.out.println("Account non trovato");
                        Functions.alert("Email o password errati", Alert.AlertType.ERROR, null);
                    } else {
                        loadPatientDashboard((Paziente) user, event);
                    }
                }
            }

        }
        //SHOW PASSWORD
        if(login_selectShowPass.isSelected()){
            passwordField.setText(login_showPassword.getText());
        }else{
            login_showPassword.setText(passwordField.getText());
        }
    }
    @FXML
    // FUNZIONE ShowPassword -> mostra la Password
    private void showPassword(){
        if(login_selectShowPass.isSelected()){
            login_showPassword.setText(passwordField.getText());
            login_showPassword.setVisible(true);
            passwordField.setVisible(false);
        }else{
            passwordField.setText(login_showPassword.getText());
            login_showPassword.setVisible(false);
            passwordField.setVisible(true);
        }

    }

    private void loadDoctorDashboard(Medico user, ActionEvent event) {
        DoctorAppData.getInstance().setMedicoLoggato(user);

        String file = "doctor/DoctorDashboard.fxml";
        FXMLLoader loader = new FXMLLoader(WindowsManager.mainClass.getResource(location + file));
        DoctorViewDashboard controller;
        try {
        	
            root = loader.load();
            controller = loader.getController();
            controller.displayName(user.getNome());

            WindowsManager.getNavigationTree().newNextScene(new Scene(root), file, loader);
            WindowsManager.showCurrentScene();

        } catch (IOException e){
            System.out.println("Impossibile caricare la pagina");
        }
    }

    private void loadAdminDashboard(AgenteSanitario user, ActionEvent event) {
        System.out.println("Dashboard dell'admin");

        // Carica home dell'agente sanitario

        String file = "admin/AdminDashboard.fxml";
        FXMLLoader loader = new FXMLLoader(WindowsManager.mainClass.getResource(location + file));
        AdminViewDashboard controller;
        try {
            root = loader.load();
            controller = loader.getController();
            controller.setAdmin(user);
            controller.displayName(user.getNome());

            WindowsManager.getNavigationTree().newNextScene(new Scene(root), file, loader);
            WindowsManager.showCurrentScene();

        } catch(IOException e) {
            System.out.println("Impossibile caricare la pagina");
        }

    }

    private void loadPatientDashboard(Paziente user, ActionEvent event) {
        PatientAppData.getInstance().setLoggedPatient(user); // imposto il paziente loggato

        String file = "patient/PatientDashboard.fxml";
        FXMLLoader loader = new FXMLLoader(WindowsManager.mainClass.getResource(location + file));
        PatientViewDashboard controller;
        try{
            root = loader.load();

            WindowsManager.getNavigationTree().newNextScene(new Scene(root), file, loader);
            WindowsManager.showCurrentScene();

        }catch (IOException e) {
            System.out.println("Impossibile caricare la pagina del paziente");
        }
    }

}
