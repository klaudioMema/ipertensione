package it.univr.ipertensione_hope.Controller.admin;

import it.univr.ipertensione_hope.Controller.DatabaseController;
import it.univr.ipertensione_hope.Model.Paziente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ManagePatient{

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField codiceFField;
    @FXML
    private Label statusLabel;
    private Paziente paziente;
    
    public void setData(Paziente paziente) {
        this.paziente = paziente;
        
        nameField.setText(this.paziente.getNome());
        surnameField.setText(this.paziente.getCognome());
        codiceFField.setText(this.paziente.getEmail());
    }

    // Remove a patient from DB
    @FXML
    private void removePatient(ActionEvent event){
            String query = "DELETE FROM patients WHERE name = '" + nameField.getText() +
                    "'AND surname = '" + surnameField.getText() +
                    "' AND codicef = '" + codiceFField.getText() + "'";
            DatabaseController.updateItem(query);
        
    }

}