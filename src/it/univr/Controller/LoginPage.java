package it.univr.Controller;

import it.univr.Controller.admin.AdminViewDashboard;
import it.univr.Controller.doctor.DoctorViewDashboard;
import it.univr.Controller.patient.PatientViewDashboard;
import it.univr.Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import it.univr.Functions;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPage implements Initializable {
    @FXML
    private AnchorPane login_form;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label statusLabel;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private CheckBox login_selectShowPass;
    @FXML
    private TextField login_showPassword;
    @FXML
    private Hyperlink login_forgotPassword;
    // Form per il cambio password
    @FXML
    private AnchorPane forgot_form;
    @FXML
    private ChoiceBox<String> changePass_choiceBox;
    @FXML
    private PasswordField changePass_password;
    @FXML
    private Button changePass_back;
    @FXML
    private PasswordField changePass_Confpassword;
    @FXML
    private TextField changePass_username;
    @FXML
    private Label changePass_statusLabel;
    private final String[] users = {"ADMIN", "PAZIENTE", "MEDICO"};

    private static Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(users);
        changePass_choiceBox.getItems().addAll(users); // Questo serve per la funzione cambio password
    }

    @FXML
    private void login(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String userType = choiceBox.getValue();
        User user = null; // utente da cercare nel database

        if (choiceBox.getValue() == null) {
            Functions.notificationMessage("Seleziona il tipo di utente", Functions.notificationType.ERROR, statusLabel);
        } else {
            switch(UserType.valueOf(userType)){
                case ADMIN -> {
                    user = AgenteSanitario.findUserDB(username, password);
                    if(user == null) {
                        System.out.println("Account non trovato");
                        Functions.notificationMessage("Email o passwor errati", Functions.notificationType.ERROR, statusLabel);
                    } else {
                        Functions.notificationMessage("Login effettuato", Functions.notificationType.CONFIRM, statusLabel);
                        loadAdminDashboard((AgenteSanitario) user, event);
                    }
                }
                case MEDICO -> {
                    user = Medico.findUserDB(username, password);
                    if(user == null) {
                        System.out.println("Account non trovato");
                        Functions.notificationMessage("Email o passwor errati", Functions.notificationType.ERROR, statusLabel);
                    } else {
                        Functions.notificationMessage("Login effettuato", Functions.notificationType.CONFIRM, statusLabel);
                        loadDoctorDashboard((Medico) user, event);
                    }
                }
                case PAZIENTE -> {
                    user = Paziente.findUserDB(username, password);
                    if(user == null) {
                        System.out.println("Account non trovato");
                        Functions.notificationMessage("Email o passwor errati", Functions.notificationType.ERROR, statusLabel);
                    } else {
                        Functions.notificationMessage("Login effettuato", Functions.notificationType.CONFIRM, statusLabel);
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
        System.out.println("Dashboard del medico");
        // Carica home del medico
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/doctor/DoctorDashboard.fxml"));
        DoctorViewDashboard controller = null;
        try {
            root = loader.load();
            controller = loader.getController();
            controller.displayName(user.getNome());
        } catch (IOException e){
            System.out.println("Impossibile caricare la pagina");
        }
        WindowsManager.setScene(new Scene(root));
        WindowsManager.getMainStage().show();
    }

    private void loadAdminDashboard(AgenteSanitario user, ActionEvent event) {
        System.out.println("Dashboard dell'admin");
        // Carica home dell'agente sanitario
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/admin/AdminDashboard.fxml"));
        AdminViewDashboard controller = null;
        try {
            root = loader.load();
            controller = loader.getController();
            controller.setAdmin(user);
            controller.displayName(user.getNome());
        } catch(IOException e) {
            System.out.println("Impossibile caricare la pagina");
        }
        WindowsManager.setScene(new Scene(root));
        WindowsManager.getMainStage().show();
    }

    private void loadPatientDashboard(Paziente user, ActionEvent event) {
        System.out.println("Dashboard del paziente");
        // Carica home del paziente
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/patient/PatientDashboard.fxml"));
        PatientViewDashboard controller = null;
        try{
            root = loader.load();
            controller = loader.getController();
            controller.home();
        }catch (IOException e) {
            System.out.println("Impossibile caricare la pagina");
        }

        WindowsManager.setScene(new Scene(root));
        WindowsManager.getMainStage().show();
    }

    public static void changeWidth(int value){
        if(stage != null)
            stage.setWidth(value);
    }

    // Funzioni per il cambio password
    @FXML
    private void changePassword() { // da rivedere non funziona il bottone "avanti" non va!!!

        String userType = changePass_choiceBox.getValue();
        try{
            if (changePass_username.getText().isEmpty() ||
                    changePass_password.getText().isEmpty() ||
                    changePass_Confpassword.getText().isEmpty() ||
                    changePass_choiceBox.getValue() == null ||
                    !changePass_back.isPressed()) {
                statusLabel.setText("completare tutti i campi !!!");
            }else if(!changePass_password.getText().equals(changePass_Confpassword.getText())) {
                statusLabel.setText("Le Password sono diverse !!!");
            }else if(changePass_password.getText().length() < 1) {
                statusLabel.setText("La password minore di 8 caratteri !!!");
            }else {
                if (userType.equals(users[0])){
                    String query = "UPDATE agentesanitario SET password ='" + changePass_password.getText() +
                            "'WHERE email ='" + changePass_username.getText() + "'";
                    DatabaseController.updateItem(query);
                }
                else if (userType.equals(users[1])){
                    String query = "UPDATE medics SET password ='" + changePass_password.getText() +
                            "'WHERE email ='" + changePass_username.getText() + "'";
                    DatabaseController.updateItem(query);
                }else {
                    if (userType.equals(users[3])){
                        String query = "UPDATE patients SET password ='" + changePass_password.getText() +
                                "'WHERE email ='" + changePass_username.getText() + "'";
                        DatabaseController.updateItem(query);
                    }
                }
                login_form.setVisible(true);
                forgot_form.setVisible(false);
                usernameTextField.setText("");
                passwordField.setVisible(true);
                passwordField.setText("");
                login_showPassword.setVisible(false);
                login_selectShowPass.setSelected(false);
            }
            // manca caso del username non trovato!!!
        }catch (Exception e){e.printStackTrace();}
        /*
        String userType = changePass_choiceBox.getValue();
        try {
            if (changePass_password.getText().isEmpty() ||
                    changePass_Confpassword.getText().isEmpty() ||
                    changePass_username.getText().isEmpty() ||
                    changePass_choiceBox.getValue() == null || !changePass_back.isPressed()) {
          fatto      //changePass_statusLabel.setText("Please fill in all blank fields.");
                //changePass_statusLabel.setTextFill(Color.color(1, 0, 0));
                statusLabel.setText("Please fill in all blank fields.");
                statusLabel.setTextFill(Color.color(1, 0, 0));
            } else if (!changePass_password.getText().equals(changePass_Confpassword.getText())) {
                // CHECK IF THE PASSWORD AND CONFIRMATION ARE NOT MATCH
                //changePass_statusLabel.setText("Passwords do not match.");
          fatto      //changePass_statusLabel.setTextFill(Color.color(1, 0, 0));
                statusLabel.setText("Passwords do not match.");
                statusLabel.setTextFill(Color.color(1, 0, 0));
            } else if (changePass_password.getText().length() < 8) {
                // CHECK IF THE LENGTH OF PASSWORD IS LESS THAN TO 8
                //changePass_statusLabel.setText("Invalid Password, at least 8 characters needed");
           fatto     //changePass_statusLabel.setTextFill(Color.color(1, 0, 0));
                statusLabel.setText("Invalid Password, at least 8 characters needed");
                statusLabel.setTextFill(Color.color(1, 0, 0));
            } else if (!userExists(changePass_username.getText(),userType)) {
                //changePass_statusLabel.setText("User not found.");
                //changePass_statusLabel.setTextFill(Color.color(1, 0, 0));
                statusLabel.setText("User not found.");
                statusLabel.setTextFill(Color.color(1, 0, 0));
            } else {
                if (userType.equals(users[0])){
                    String query = "UPDATE patients SET password = '" + changePass_password.getText() +
               fatto                    "' WHERE email = '" + changePass_username.getText() + "'";
                    DatabaseController.updateItem(query);
                }
                else if (userType.equals(users[1])){
                    String query = "UPDATE medics SET password =  '" + changePass_password.getText() +
                fatto                   "' WHERE email = '" + changePass_username.getText() + "'";
                    DatabaseController.updateItem(query);
                }else {
                    if (userType.equals(users[3])){
                    String query = "UPDATE agentesanitario SET password =  '" + changePass_password.getText() +
                fatto                   "' WHERE email = '" + changePass_username.getText() + "'";
                    DatabaseController.updateItem(query);
                    }
                }

                statusLabel.setText("");
                statusLabel.setText("Successfully Changed Password.");
                statusLabel.setTextFill(Color.color(0, 1, 0));
            }
            // LOGIN_FORM
            login_form.setVisible(true);
            forgot_form.setVisible(false);
            usernameTextField.setText("");
            passwordField.setVisible(true);
            passwordField.setText("");
            login_showPassword.setVisible(false);
            login_selectShowPass.setSelected(false);
            //FORGOT_FORM
            changePass_password.setText("");
            changePass_Confpassword.setText("");
        }catch (Exception e){e.printStackTrace();}
         */
    }
    @FXML
    private void switchForm(ActionEvent event) {
        if (event.getSource() == login_forgotPassword){
            login_form.setVisible(false);
            forgot_form.setVisible(true);
        }
        else if (event.getSource() == changePass_back) {
            login_form.setVisible(true);
            forgot_form.setVisible(false);
        }
    }
}
