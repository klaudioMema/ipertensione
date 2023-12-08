package it.univr.Controller.admin;

import it.univr.Controller.DatabaseController;
import it.univr.Functions;
import it.univr.Model.Medico;
import it.univr.Model.Paziente;
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
    private TableView<Medico> tableView;
    @FXML
    private TableColumn<Medico,String> nameColumn;
    @FXML
    private TableColumn<Medico, String> surnameColumn;
    @FXML
    private TableColumn<Medico, String> emailColumn;
    @FXML
    private Label statusLabel;
    @FXML
    private Label chooseADoctorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //chooseADoctorLabel.setText("Choose a Doctor for: " + Paziente.getInstance().getName() + " " + Paziente.getInstance().getSurname());

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadTable();
    }


    private void loadTable(){
        ObservableList<Medico> data = FXCollections.observableArrayList();
        try {
            ResultSet rs = DatabaseController.getResultSet("SELECT * FROM medics");
            while (rs.next()){
                //Medico medico = new Medico();
                //medico.setName(rs.getString("name"));
                //medico.setSurname(rs.getString("surname"));
                //medico.setEmail(rs.getString("email"));
                //medico.setDoctorId(rs.getInt("doctor_id"));
                //data.add(medico);
            }
            tableView.setItems(data);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void assignDoctor(ActionEvent event){
        /*
        try {

            Medico selectedMedico = tableView.getSelectionModel().getSelectedItem();

            DatabaseController.updateItem("UPDATE bloodmonitor.patients " +
                    "SET doctor_id = " + selectedMedico.getDoctorId() +
                    " WHERE user_id = " + Paziente.getInstance().getPatientId()  + ";");

            Functions.notificationMessage("Doctor Assigned!", "CONFIRM", statusLabel);
        } catch (Exception e){
            Functions.notificationMessage("Couldn't assign doctor to patient", "ERROR", statusLabel);
        }
        */

    }
}

