package it.univr.Controller.admin;

import it.univr.Controller.DatabaseController;
import it.univr.Functions;
import it.univr.Model.Paziente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterPatient {
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField codiceFField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private DatePicker bDayField;
    @FXML
    private Label statusLabel;

    private void chooseDoctor() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../View/admin/ChooseDoctor.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            // Specifies the owner Window (parent) for new window
            stage.setScene(new Scene(root1));
            stage.setTitle("BloodMonitor - Choose Doctor");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void savePatient(ActionEvent event) throws SQLException {
        if(nameField.getText().equals("") || surnameField.getText().equals("") ||
                passwordField.getText().equals("") || codiceFField.getText().equals("") ||
                bDayField.getValue() == null){ // controllo se l'utente non inserisce nulla -> provato e funziona !!!
            statusLabel.setVisible(true);
        } else if (passwordField.getText().length() < 8) {// controllo password !!! -> provato e funziona
            statusLabel.setText("Password deve essere lunga 8");
        } else if (bDayField.getValue().getYear() < 1900 || bDayField.getValue().getYear() > LocalDate.now().getYear()) {
            statusLabel.setText("Data nascita non valida"); // non funziona  da vedere

        }else{//controllo il codie fiscale:
            String query = "SELECT * FROM bloodmonitor.patients WHERE codicef = '" + codiceFField.getText() + "'";
            ResultSet check_codicef = DatabaseController.getResultSet(query);
            if (check_codicef.next()){
                statusLabel.setText("Paziente già inserito");
            }

            else {//inserisco il novo paziente:
                query = "INSERT INTO bloodmonitor.patients (name, surname, email, password, codicef, bday) " +
                        "VALUES ('" + nameField.getText() + "', '" + surnameField.getText() + "', '" + emailField.getText() +
                        "', '" + passwordField.getText() + "', '" + codiceFField.getText() + "', '" + bDayField.getValue() + "')";
                DatabaseController.updateItem(query);
                //String queryPatientsID = "SELECT user_id FROM bloodmonitor.patients " + "ORDER  BY user_id DESC LIMIT 1";
                //ResultSet rs = DatabaseController.getResultSet(queryPatientsID);
                // aggiunta nuovo paziente: DA FARE.
            }

        }
       /* // Empty field
        if(nameField.getText().equals("") || surnameField.getText().equals("") || emailField.getText().equals("") ||
                passwordField.getText().equals("") || codiceFField.getText().equals("") || bDayField.getValue() == null) {
            statusLabel.setVisible(true);
        // Password needs to be at leat 8 characters long
        } else if (passwordField.getText().length() < 8) {
            Functions.notificationMessage("Password needs to be at leat 8 characters long", "ERROR", statusLabel);
        // Codice fiscale
        } else if (false) {
            Functions.notificationMessage("Codice fiscale is not valid", "ERROR", statusLabel);
        // birthdate check
        } else if (bDayField.getValue().getYear() < 1900 || bDayField.getValue().getYear() > LocalDate.now().getYear()) {
            Functions.notificationMessage("Choose a valid birthdate", "ERROR", statusLabel);
        } else {
            // Controllo se il codice fiscale è già esistente
            String query = "SELECT * FROM bloodmonitor.patients WHERE codicef = '" + codiceFField.getText() + "'";
            ResultSet check_codiceF = DatabaseController.getResultSet(query);
            if(check_codiceF.next()){ // se c'è un paziente con quel codice fiscale
                Functions.notificationMessage("Patient already inserted", "ERROR", statusLabel);
            } else {
                query = "INSERT INTO bloodmonitor.patients (name, surname, email, password, codicef, bday) " +
                        "VALUES ('" + nameField.getText() + "', '" + surnameField.getText() + "', '" + emailField.getText() +
                        "', '" + passwordField.getText() + "', '" + codiceFField.getText() + "', '" + bDayField.getValue() + "')";
                DatabaseController.updateItem(query);
                // Get Primary Key for the new Patient
                String queryPatientId = "SELECT user_id FROM bloodmonitor.patients " +
                        "ORDER BY user_id DESC LIMIT 1";
                ResultSet rs = DatabaseController.getResultSet(queryPatientId);

                Paziente newPaziente = Paziente.getInstance();
                if (rs.next())
                    newPaziente.setPatientId(rs.getInt("user_id"));
                newPaziente.setName(nameField.getText());
                newPaziente.setSurname(surnameField.getText());
                newPaziente.setEmail(emailField.getText());
                newPaziente.setPassword(passwordField.getText());
                newPaziente.setCodiceF(codiceFField.getText());
                newPaziente.setbDay(Date.valueOf(bDayField.getValue()));
                chooseDoctor();
                Functions.notificationMessage("Saved!", "CONFIRM", statusLabel);
            }

        }

 */
    }

    // Check if codice fiscale is valid
    private boolean checkCF(){
        String regex = "^[A-Z]{6}\\d{2}[0-9]\\d[A-Z]\\d{2}[0-9]\\d[A-Z]\\d{3}[0-9]\\d[A-Z]$";
        Matcher matcher = Pattern.compile(regex).matcher(codiceFField.getText());
        return matcher.matches();
    }

    // Mostra notifiche
    public static void FnotificationMessage(String msg, String type, Label status){
        if(type.equals("ERROR")){
            status.setTextFill(Color.rgb(255, 0, 0));
        } else if(type.equals("CONFIRM")){
            status.setTextFill(Color.rgb(0, 255, 0));
        }

        status.setText(msg);
        status.setVisible(true);
    }



}
