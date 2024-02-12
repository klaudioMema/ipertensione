package it.univr.ipertensione_hope.Controller.doctor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;


public class SearchPatientDoctor implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField codiceFField;
    @FXML
    private Label statusLabel;


    @FXML
    private void search(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        if(Paziente.getInstance().getPatientId() != 0){
            setTextSelectedPatient();
        }

         */
    }

    private void setTextSelectedPatient(){
        /*
        nameField.setText(Paziente.getInstance().getName());
        surnameField.setText(Paziente.getInstance().getSurname());
        codiceFField.setText(Paziente.getInstance().getCodiceF());

         */
    }
}

