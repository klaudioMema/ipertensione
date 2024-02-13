package it.univr.ipertensione_hope.Controller.admin;

import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.Medico;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
    private void saveDoctor(ActionEvent event) {
        if (    nameField.getText().isEmpty() || surnameField.getText().isEmpty() ||
                emailField.getText().isEmpty() || passwordField.getText().isEmpty()) {

            Functions.alert("Compilare tutti i campi", Alert.AlertType.ERROR, null);

        } else {
            if(Medico.findUserDB(emailField.getText()) != null) {
                Functions.alert("Questo medico è già esistente nel database", Alert.AlertType.ERROR, null);
            } else {
                Medico newDoctor = new Medico(
                        nameField.getText(),
                        surnameField.getText(),
                        emailField.getText(),
                        passwordField.getText()
                );

                if(Medico.addDoctor(newDoctor)) {
                    Functions.alert("Il paziente è stato correttamente inserito nel database", Alert.AlertType.INFORMATION, (ButtonType button) -> {
                        WindowsManager.loadPage(WindowsManager.mainClass.getResource("admin/AdminDashboard.fxml"), "admin/AdminDashboard.fxml");
                        WindowsManager.reloadPage();
                    });
                } else {
                    Functions.alert("Errore inaspettato durante l'inserimento", Alert.AlertType.ERROR, null);
                }
            }
        }
    }
}
