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
    private Button changePass_back;
    @FXML
    private PasswordField changePass_password;
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

        // changePass_choiceBox.getItems().addAll(users); // Questo serve per la funzione cambio password
    }


    @FXML
    private void login(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String userType = choiceBox.getValue();

        if (choiceBox.getValue() == null) {
            Functions.notificationMessage("Seleziona il tipo di utente", "ERROR", statusLabel);
        } else {
            switch(UserType.valueOf(userType)){
                case ADMIN -> {
                    User user = new AgenteSanitario();
                    user = user.findUserDB(username, password);

                    if(user == null) {
                        System.out.println("Account non trovato");
                        statusLabel.setText("Email o password sbagliati");
                        statusLabel.setTextFill(Color.color(1, 0, 0));
                    } else {
                        statusLabel.setText("Successful login!");
                        statusLabel.setTextFill(Color.color(0, 1, 0));

                        loadAdminDashboard((AgenteSanitario) user, event);
                    }
                }
                case MEDICO -> {
                    User user = new Medico();
                    user = user.findUserDB(username, password);

                    if(user == null) {
                        System.out.println("Account non trovato");
                        statusLabel.setText("Email o password sbagliati");
                        statusLabel.setTextFill(Color.color(1, 0, 0));
                    } else {
                        statusLabel.setText("Successful login!");
                        statusLabel.setTextFill(Color.color(0, 1, 0));

                        loadDoctorDashboard(username, event);
                    }
                }
                case PAZIENTE -> {
                    User user = new Paziente();
                    user = user.findUserDB(username, password);

                    if(user == null) {
                        System.out.println("Account non trovato");
                        statusLabel.setText("Email o password sbagliati");
                        statusLabel.setTextFill(Color.color(1, 0, 0));
                    } else {
                        statusLabel.setText("Successful login!");
                        statusLabel.setTextFill(Color.color(0, 1, 0));

                        loadPatientDashboard(username, event);
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

    private void loadDoctorDashboard(String username, ActionEvent event) {
        System.out.println("Dashboard del medico");

        // Carica home del medico

        /*
        // Load next scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/doctor/DoctorDashboard.fxml"));
        root = loader.load();

        DoctorViewDashboard menuController = loader.getController();
        menuController.displayName(activeUser.getNome());

        // set new stage;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

         */
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


    private void loadPatientDashboard(String username, ActionEvent event) {
        System.out.println("Dashboard del paziente");

        // Carica home del paziente

    /*
        // Load next scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/patient/PatientDashboard.fxml"));
        root = loader.load();
        // Get user data from DB
        Paziente activeUser;
        try {

            ResultSet rs = DatabaseController.getResultSet("SELECT * FROM patients WHERE email = '" + username + "'");
            rs.next();
            activeUser = Paziente.getInstance();
            activeUser.setPatientId(rs.getInt("user_id"));
            activeUser.setEmail(rs.getString("email"));
            activeUser.setPassword(rs.getString("password"));
            activeUser.setName(rs.getString("name"));
            activeUser.setSurname(rs.getString("surname"));
            activeUser.setCodiceF(rs.getString("codiceF"));
            activeUser.setbDay(java.sql.Date.valueOf(rs.getString("bDay")));
            activeUser.setDoctorId(Integer.parseInt(rs.getString("doctor_id")));
            activeUser.setFattoriDiRischio(rs.getString("fattoridirischio"));
            rs.close();

            PatientViewDashboard menuController = loader.getController();
            menuController.displayName();
            menuController.home();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

     */
    }

    public static void changeWidth(int value){
        if(stage != null)
            stage.setWidth(value);
    }

    // Funzioni per il cambio password
    @FXML
    private void changePassword() {
        /*
        String userType = changePass_choiceBox.getValue();
        try {
            if (changePass_password.getText().isEmpty() ||
                    changePass_Confpassword.getText().isEmpty() ||
                    changePass_username.getText().isEmpty() ||
                    changePass_choiceBox.getValue() == null || !changePass_back.isPressed()) {
                //changePass_statusLabel.setText("Please fill in all blank fields.");
                //changePass_statusLabel.setTextFill(Color.color(1, 0, 0));
                statusLabel.setText("Please fill in all blank fields.");
                statusLabel.setTextFill(Color.color(1, 0, 0));
            } else if (!changePass_password.getText().equals(changePass_Confpassword.getText())) {
                // CHECK IF THE PASSWORD AND CONFIRMATION ARE NOT MATCH
                //changePass_statusLabel.setText("Passwords do not match.");
                //changePass_statusLabel.setTextFill(Color.color(1, 0, 0));
                statusLabel.setText("Passwords do not match.");
                statusLabel.setTextFill(Color.color(1, 0, 0));
            } else if (changePass_password.getText().length() < 8) {
                // CHECK IF THE LENGTH OF PASSWORD IS LESS THAN TO 8
                //changePass_statusLabel.setText("Invalid Password, at least 8 characters needed");
                //changePass_statusLabel.setTextFill(Color.color(1, 0, 0));
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
                                   "' WHERE email = '" + changePass_username.getText() + "'";
                    DatabaseController.updateItem(query);
                }
                else if (userType.equals(users[1])){
                    String query = "UPDATE medics SET password =  '" + changePass_password.getText() +
                                   "' WHERE email = '" + changePass_username.getText() + "'";
                    DatabaseController.updateItem(query);
                }else {
                    if (userType.equals(users[3])){
                    String query = "UPDATE agentesanitario SET password =  '" + changePass_password.getText() +
                                   "' WHERE email = '" + changePass_username.getText() + "'";
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
        /*
        if (event.getSource() == login_forgotPassword){
            login_form.setVisible(false);
            forgot_form.setVisible(true);
        }
        else if (event.getSource() == changePass_back) {

            login_form.setVisible(true);
            forgot_form.setVisible(false);
        }
         */
    }

}
