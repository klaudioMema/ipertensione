package it.univr.Controller.patient;

import it.univr.Controller.DatabaseController;
import it.univr.Model.Patient;
import it.univr.Model.Prescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Medications implements Initializable {

    @FXML
    private TableView<Prescription> tableView;
    @FXML
    private TableColumn<Prescription,String> medicationColumn;
    @FXML
    private TableColumn<Prescription, String> indicationsColumn;
    @FXML
    private TableColumn<Prescription, Integer> daysLeftColumn;
    @FXML
    private TableColumn<Prescription, Date> untilDateColumn;
    @FXML
    private Label todayLabel;
    @FXML
    private Label statusLabel;
    private Patient activeUser;
    private HomePage homePageController;

    public void setHomePageController(HomePage homePageController){
        this.homePageController = homePageController;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //set cell value -> FACTORY PATTERN
        activeUser = Patient.getInstance();
        medicationColumn.setCellValueFactory(new PropertyValueFactory<>("medication"));
        indicationsColumn.setCellValueFactory(new PropertyValueFactory<>("indications"));
        daysLeftColumn.setCellValueFactory(new PropertyValueFactory<>("days"));
        untilDateColumn.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        todayLabel.setText("Medications I have to take today: " + LocalDate.now());

        loadTable();
    }


    private void loadTable(){
        ObservableList<Prescription> data = FXCollections.observableArrayList();
        ArrayList<Prescription> activePrescriptions = new ArrayList<>();
        ArrayList<Prescription> takenMedicine = new ArrayList<>();
        Prescription prescription;
        try {
            // Get all active prescriptions from DB
            ResultSet rs = DatabaseController.getResultSet("SELECT * FROM prescriptions WHERE user_id = '" +
                    activeUser.getPatientId() + "'");
            while (rs.next()){
                 prescription = new Prescription(rs.getString("medication"),
                        rs.getString("indications"),
                        rs.getInt("days"),
                        rs.getDate("fromDate"));
                // If any prescriptions is expired, remove it from DB
                if(isValidYet(prescription.getFromDate(), prescription.getDays()))
                        activePrescriptions.add(prescription);
                else
                    deleteExpiredPrescription(prescription);
            }
            rs = DatabaseController.getResultSet("SELECT * FROM takenmedication WHERE user_id = '" +
                    activeUser.getPatientId() + "'");
            while (rs.next()) {
                 prescription = new Prescription(rs.getString("medication"),
                        rs.getString("indications"),
                        rs.getInt("days"),
                        rs.getDate("daythatwastaken"));
                        takenMedicine.add(prescription);

            }
            //se c'e il farmaco su activePrescription ma non c'è su takenMedicines = Ancora da prendere
            //se c'e il farmaco su activePrescription e c'è su takenMedicines = Già preso oggi
            //se non c'e su activePrescription ma c'è su taken = Expired, da cancellare;
            Date today = Date.valueOf(LocalDate.now());
            for(Prescription p : activePrescriptions){
                if((p.getFromDate().equals(today) && !takenMedicine.contains(p)) || !takenMedicine.contains(new Prescription(p.getMedication(), p.getIndications(),p.getDays(),today)))
                        data.add(p);
            }
            tableView.setItems(data);
            if(data.size() == 0){
                statusLabel.setVisible(true);
                statusLabel.setText("You're all set for today :)");
                todayLabel.setVisible(false);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static boolean hasMedicineToTake(){
        ArrayList<Prescription> activePrescriptions = new ArrayList<>();
        ArrayList<Prescription> takenMedicine = new ArrayList<>();
        Prescription prescription;

        try {
            // Get all active prescriptions from DB
            ResultSet rs = DatabaseController.getResultSet("SELECT * FROM prescriptions WHERE user_id = '" +
                    Patient.getInstance().getPatientId() + "'");
            while (rs.next()){
                prescription = new Prescription(rs.getString("medication"),
                        rs.getString("indications"),
                        rs.getInt("days"),
                        rs.getDate("fromDate"));
                // If any prescriptions is expired, remove it from DB
                if(isValidYet(prescription.getFromDate(), prescription.getDays()))
                    activePrescriptions.add(prescription);
                else
                    deleteExpiredPrescription(prescription);
            }
            rs = DatabaseController.getResultSet("SELECT * FROM takenmedication WHERE user_id = '" +
                    Patient.getInstance().getPatientId() + "'");
            while (rs.next()) {
                prescription = new Prescription(rs.getString("medication"),
                        rs.getString("indications"),
                        rs.getInt("days"),
                        rs.getDate("daythatwastaken"));
                takenMedicine.add(prescription);

            }
            //se c'e il farmaco su activePrescription ma non c'è su takenMedicines = Ancora da prendere
            //se c'e il farmaco su activePrescription e c'è su takenMedicines = Già preso oggi
            //se non c'e su activePrescription ma c'è su taken = Expired, da cancellare;
            Date today = Date.valueOf(LocalDate.now());
            for(Prescription p : activePrescriptions){
                if((p.getFromDate().equals(today) && !takenMedicine.contains(p)) || !takenMedicine.contains(new Prescription(p.getMedication(), p.getIndications(),p.getDays(),today)))
                    return true;
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    @FXML
    private void reportMedicine(ActionEvent event){
        Prescription selectedItem;
        if(tableView.getSelectionModel().getSelectedItem() != null) {
            selectedItem = tableView.getSelectionModel().getSelectedItem();

            String query = "INSERT INTO takenmedication (user_id, medication, indications, days, daythatwastaken)" +
                    "VALUES ('" + activeUser.getPatientId() + "', '" +
                    selectedItem.getMedication() + "', '" +
                    selectedItem.getIndications() + "', '" +
                    selectedItem.getDays() + "', '" +
                    new Date(System.currentTimeMillis()) + "')";
            DatabaseController.updateItem(query);
            loadTable();
            if(tableView.getItems().size() == 0)
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        } else {
            statusLabel.setVisible(true);
            statusLabel.setText("You need to select an item first!");
        }
    }

    private static boolean isValidYet(Date fromDate, int days){
        Date today = new Date(System.currentTimeMillis());
        long daysPassed = ChronoUnit.DAYS.between(fromDate.toLocalDate(), today.toLocalDate());
        return daysPassed <= days;
    }

    private static void deleteExpiredPrescription(Prescription prescription){
        DatabaseController.updateItem("DELETE FROM prescriptions WHERE user_id = '" +
                Patient.getInstance().getPatientId() + "' AND medication LIKE '" +
                prescription.getMedication() + "' AND indications LIKE '" +
                prescription.getIndications() +"' AND days LIKE '" +
                prescription.getDays() + "' AND fromDate LIKE '" +
                prescription.getFromDate() + "'");
    }



}
