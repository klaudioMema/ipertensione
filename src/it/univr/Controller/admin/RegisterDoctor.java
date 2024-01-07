package it.univr.Controller.admin;

import it.univr.Controller.DatabaseController;
import it.univr.Functions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class RegisterDoctor {

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label statusLabel;

    @FXML
    private void saveDoctor(ActionEvent event) {
        if (    nameField.getText().equals("") || surnameField.getText().equals("") ||
                emailField.getText().equals("") || passwordField.getText().equals("")) {

            Functions.notificationMessage("Compilare tutti i campi", Functions.notificationType.ERROR, statusLabel);

        } else {
            String query = "INSERT INTO bloodmonitor.medics (name, surname, email, password) " +
                    "VALUES ('" + nameField.getText() + "', '" + surnameField.getText() + "', '" + emailField.getText() +
                    "', '" + passwordField.getText() + "')";
            DatabaseController.updateItem(query);
            // Get Primary Key for the new Patient

            Functions.notificationMessage("Salvato!", Functions.notificationType.CONFIRM, statusLabel);

            statusLabel.setText("Saved!");
            statusLabel.setTextFill(Color.rgb(155, 222, 111));
            statusLabel.setVisible(true);

        }
    }
}
