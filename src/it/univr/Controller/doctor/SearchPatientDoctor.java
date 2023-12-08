package it.univr.Controller.doctor;

import it.univr.Controller.Search;
import it.univr.Model.Paziente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;


public class SearchPatientDoctor extends Search implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField codiceFField;
    @FXML
    private Label statusLabel;


    @FXML
    private void search(ActionEvent event) throws SQLException, ParseException {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String codiceF = codiceFField.getText();
        if ((name.equals("") || surname.equals("")) && codiceF.equals("")) {
            statusLabel.setVisible(true);
            statusLabel.setText("Please fill in the data first");
        } else {
            if (!searchPatientDB(name, surname, codiceF)) {
                statusLabel.setVisible(true);
                statusLabel.setText("Patient Not Found!");
            } else {
                statusLabel.setVisible(true);
                statusLabel.setText("Patient Selected!");
                setTextSelectedPatient();
            }
        }
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

