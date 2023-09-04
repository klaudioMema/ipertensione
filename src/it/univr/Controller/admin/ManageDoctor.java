package it.univr.Controller.admin;

import it.univr.Controller.DatabaseController;
import it.univr.Controller.Search;
import it.univr.Model.Medic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.text.ParseException;

public class ManageDoctor extends Search {

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private Label statusLabel;

    // Select a doctor
    @FXML
    private void searchDoctor(ActionEvent event) throws SQLException, ParseException {

        String name = nameField.getText();
        String surname = surnameField.getText();
        String email = emailField.getText();
        if ((name.equals("") || surname.equals("")) && email.equals("")) {
            setStatusLabel("Please fill in the data first");
        } else {
            if (!searchDoctorDB(name, surname, email)) {
                setStatusLabel("Doctor Not Found!");
            } else {
                nameField.setText(Medic.getInstance().getName());
                surnameField.setText(Medic.getInstance().getSurname());
                emailField.setText(Medic.getInstance().getEmail());
                setStatusLabel("Doctor Selected!");
            }
        }
    }

    // Save chanhes in the DB
    @FXML
    private void saveButton(ActionEvent event) {
        Medic selectedUser = Medic.getInstance();
        if (Medic.getInstance().getDoctordId() == 0) {
            setStatusLabel("Please search a doctor first");
        } else {
            if (DatabaseController.updateItem("UPDATE medics SET name = '" + nameField.getText() +
                    "', surname = '" + surnameField.getText() +
                    "', email = '" + emailField.getText() +
                    "' WHERE doctor_id = '" + Medic.getInstance().getDoctordId() + "'")) {

                selectedUser.setName(nameField.getText());
                selectedUser.setSurname(surnameField.getText());
                selectedUser.setEmail(emailField.getText());
                setStatusLabel("Update Successful!");
            } else {
                setStatusLabel("Update not Successful!");
            }
        }
    }

    // Remove a doctor from DB
    @FXML
    private void removeDoctor(ActionEvent event){
        if(Medic.getInstance().getDoctordId() == 0) {
            setStatusLabel("Please search a doctor first");
        } else {
            try {
                DatabaseController.updateItem("DELETE FROM medics " + "WHERE doctor_id = '" + Medic.getInstance().getDoctordId() + "'");
                setStatusLabel("Doctor removed!");
            } catch (Exception e) {
                e.printStackTrace();
                setStatusLabel("Couldn't remove doctor!");
            }
        }
    }

    // Support method
    public void setStatusLabel(String message) {
        statusLabel.setVisible(true);
        statusLabel.setText(message);
    }
}
