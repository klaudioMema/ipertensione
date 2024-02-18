package it.univr.ipertensione_hope.Controller.admin;

import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.Medico;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class ManageDoctor {

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

    private Medico selectedDoctor;

    public void setData(Medico medico) {
    	selectedDoctor = medico;
    	
    	nameField.setText(this.selectedDoctor.getNome());
    	surnameField.setText(this.selectedDoctor.getCognome());
    	emailField.setText(this.selectedDoctor.getEmail());
    }

    @FXML
    private void saveButton(ActionEvent event) {
        if (    nameField.getText().isEmpty() || surnameField.getText().isEmpty() ||
                emailField.getText().isEmpty() || passwordField.getText().isEmpty()) {

            Functions.alert("Compilare tutti i campi", Alert.AlertType.ERROR, null);

        } else {
            selectedDoctor.setNome(nameField.getText());
            selectedDoctor.setCognome(surnameField.getText());
            selectedDoctor.setEmail(emailField.getText());
            selectedDoctor.setPassword(passwordField.getText());

            if(Medico.updateDoctor(selectedDoctor)) {
                Functions.alert("Medico salvato correttamente", Alert.AlertType.INFORMATION, (ButtonType button) -> {
                    WindowsManager.previousPage();
                });
            } else {
                Functions.alert("Errore inaspettato", Alert.AlertType.ERROR, null);
            }
        }
    }

}
