package it.univr.ipertensione_hope.Controller.patient;

import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.Paziente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;
public class MyDoctor implements Initializable {
    @FXML
    private Label bDayField;
    @FXML
    private Label codiceFField;
    @FXML
    private Label nameField;
    @FXML
    private Label surnameField;
    private Paziente doctorid;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doctorid = Paziente.getAll()[doctorid.getDoctorId()]; ;
        if ( doctorid == null)
            Functions.alert("Medico non trovato", Alert.AlertType.ERROR, null);
        else{
            nameField.setText(doctorid.getNome());
            surnameField.setText(doctorid.getCognome());
            codiceFField.setText(doctorid.getCodiceF());
            bDayField.setText((doctorid.getbDay().toString()));

        }
    }
}
