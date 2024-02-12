package it.univr.ipertensione_hope.Controller.patient;

import it.univr.ipertensione_hope.Controller.DatabaseController;
import it.univr.ipertensione_hope.Model.Paziente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MyProfile implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField codiceFField;
    @FXML
    private TextArea fattoriDiRischio;
    @FXML
    private DatePicker bDayField;
    @FXML
    private Label doctorsName;
    @FXML
    private Label doctorsEmail;

    //TODO
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        nameField.setText(Paziente.getInstance().getName());
        surnameField.setText(Paziente.getInstance().getSurname());
        codiceFField.setText(Paziente.getInstance().getCodiceF());
        fattoriDiRischio.setText(Paziente.getInstance().getFattoriDiRischio());
        bDayField.setValue(Paziente.getInstance().getbDay().toLocalDate());
        try {
            getDoctorsData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

         */
    }

    private void getDoctorsData() throws SQLException {
        /*
        String query =  "SELECT * FROM medics WHERE doctor_id = '" + Paziente.getInstance().getDoctorId() + "'";
        ResultSet rs = DatabaseController.getResultSet(query);

        try {
            if (rs.next()) {
                doctorsName.setText("Name: " + rs.getString("name") +
                                    " " + rs.getString("surname"));
                doctorsEmail.setText("Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

         */

    }


}
