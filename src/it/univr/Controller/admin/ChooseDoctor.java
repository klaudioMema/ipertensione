package it.univr.Controller.admin;

import it.univr.Controller.DatabaseController;
import it.univr.Functions;
import it.univr.Model.Medic;
import it.univr.Model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChooseDoctor implements Initializable {

    @FXML
    private TableView<Medic> tableView;
    @FXML
    private TableColumn<Medic,String> nameColumn;
    @FXML
    private TableColumn<Medic, String> surnameColumn;
    @FXML
    private TableColumn<Medic, String> emailColumn;
    @FXML
    private Label statusLabel;
    @FXML
    private Label chooseADoctorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseADoctorLabel.setText("Choose a Doctor for: " + Patient.getInstance().getName() + " " + Patient.getInstance().getSurname());

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadTable();
    }


    private void loadTable(){
        ObservableList<Medic> data = FXCollections.observableArrayList();
        try {
            ResultSet rs = DatabaseController.getResultSet("SELECT * FROM medics");
            while (rs.next()){
                Medic medic = new Medic();
                medic.setName(rs.getString("name"));
                medic.setSurname(rs.getString("surname"));
                medic.setEmail(rs.getString("email"));
                medic.setDoctordId(rs.getInt("doctor_id"));
                data.add(medic);
            }
            tableView.setItems(data);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void assignDoctor(ActionEvent event){
        try {
            Medic selectedMedic = tableView.getSelectionModel().getSelectedItem();

            DatabaseController.updateItem("UPDATE bloodmonitor.patients " +
                    "SET doctor_id = " + selectedMedic.getDoctordId() +
                    " WHERE user_id = " + Patient.getInstance().getPatientId()  + ";");

            Functions.notificationMessage("Doctor Assigned!", "CONFIRM", statusLabel);
        } catch (Exception e){
            Functions.notificationMessage("Couldn't assign doctor to patient", "ERROR", statusLabel);
        }


    }
}

