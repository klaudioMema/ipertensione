package it.univr.Controller;

import it.univr.Controller.admin.AdminViewDashboard;
import it.univr.Controller.doctor.DoctorViewDashboard;
import it.univr.Controller.patient.PatientViewDashboard;
import it.univr.Model.AgenteSanitario;
import it.univr.Model.Medic;
import it.univr.Model.Patient;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Function;

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
    // forget form
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


    private final String[] users = {"Patient", "Doctor", "Admin"};

    private static Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(users);
        changePass_choiceBox.getItems().addAll(users);
    }


    @FXML
    private void login(ActionEvent event) {
        String usermail = usernameTextField.getText();
        String userpass = passwordField.getText();
        String userType = choiceBox.getValue();

        // Check if user type is selected
        //alertMassage alert = new alertMassage();
        if (choiceBox.getValue() == null) {
            Functions.notificationMessage("Seleziona il tipo di utente", "ERROR", statusLabel);
        } else {
            try {
                // Check if user exists
                if (userExists(usermail, userType)) {
                    // Check if password matches with database
                    if (passwordCheck(usermail, userpass, userType)) {

                        statusLabel.setText("Successful login!");
                        statusLabel.setTextFill(Color.color(0, 1, 0));
                        // Load next scene

                        if (userType.equals(users[2])) {
                            loadAdminDashboard(usermail, event);
                        } else if (userType.equals(users[1])) {
                            loadDoctorDashboard(usermail, event);
                        } else {
                            loadPatientDashboard(usermail, event);
                        }

                    } else {

                        statusLabel.setText("Incorrect password!");
                        statusLabel.setTextFill(Color.color(1, 0, 0));
                    }
                } else {

                    statusLabel.setText("Account not found!");
                    statusLabel.setTextFill(Color.color(1, 0, 0));
                }
            } catch (Exception e) {
                e.printStackTrace();
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
    @FXML
    private void changePassword() {
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

    }

    private void loadDoctorDashboard(String username, ActionEvent event) throws IOException {
        // Load next scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/doctor/DoctorDashboard.fxml"));
        root = loader.load();
        //  Get user data
        Medic activeUser;
        try {
            ResultSet rs = DatabaseController.getResultSet("SELECT * FROM medics WHERE email = '" + username + "'");
            rs.next();

            activeUser = new Medic();
            activeUser.setEmail(rs.getString("email"));
            activeUser.setPassword(rs.getString("password"));
            activeUser.setNome(rs.getString("name"));
            activeUser.setCognome(rs.getString("surname"));

            rs.close();

            DoctorViewDashboard menuController = loader.getController();
            menuController.displayName(activeUser.getNome());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // set new stage;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    private void loadAdminDashboard(String username,ActionEvent event) throws IOException {
        // Load next scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/admin/AdminDashboard.fxml"));
        root = loader.load();
        // Get user data
        AgenteSanitario activeUser = AgenteSanitario.getInstance();
        try {
            ResultSet rs = DatabaseController.getResultSet("SELECT * FROM agentesanitario WHERE email = '" + username + "'");
            rs.next();
            activeUser.setEmail(rs.getString("email"));
            activeUser.setPassword(rs.getString("password"));
            activeUser.setName(rs.getString("name"));
            rs.close();

            AdminViewDashboard adminViewDashboard = loader.getController();
            adminViewDashboard.displayName(activeUser.getName());


        } catch (SQLException e) {
            e.printStackTrace();
        }
        // set new stage;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void loadPatientDashboard(String username, ActionEvent event) throws  IOException {
        // Load next scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/patient/PatientDashboard.fxml"));
        root = loader.load();
        // Get user data from DB
        Patient activeUser;
        try {

            ResultSet rs = DatabaseController.getResultSet("SELECT * FROM patients WHERE email = '" + username + "'");
            rs.next();
            activeUser = Patient.getInstance();
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
    }

    private boolean userExists(String username,String userType) {
        boolean result = false;

        try {

            ResultSet rs;
            if(userType.equals(users[2]))
                rs = DatabaseController.getResultSet("SELECT email FROM agentesanitario WHERE email = '" + username + "'");
            else if(userType.equals(users[1]))
                rs = DatabaseController.getResultSet("SELECT email FROM medics WHERE email = '" + username + "'");
            else
                rs = DatabaseController.getResultSet("SELECT email FROM patients WHERE email = '" + username + "'");


            if (rs.next())
                result = true;

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    private boolean passwordCheck(String username, String password,String userType){
        ResultSet rs;
        boolean result = false;


        try {
            if(userType.equals(users[2]))
                rs = DatabaseController.getResultSet("SELECT password FROM agentesanitario WHERE email = '" + username + "'");
            else if(userType.equals(users[1]))
                rs = DatabaseController.getResultSet("SELECT password FROM medics WHERE email = '" + username + "'");
            else
                rs = DatabaseController.getResultSet("SELECT password FROM patients WHERE email = '" + username + "'");

            rs.next();

            if(rs.getString("password").equals(password))
                result = true;

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void changeWidth(int value){
        if(stage != null)
            stage.setWidth(value);
    }

}
