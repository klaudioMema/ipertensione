package it.univr.ipertensione_hope.Controller;

import it.univr.ipertensione_hope.Controller.patient.HomePage;
import it.univr.ipertensione_hope.Model.BloodPressureData;
import it.univr.ipertensione_hope.Model.Paziente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

public class BloodPressure implements Initializable {


    @FXML
    private TableView<BloodPressureData> tableView;
    @FXML
    private TableColumn<BloodPressureData,String> categoryColumn;
    @FXML
    private TableColumn<BloodPressureData,Integer> SBPColumn;
    @FXML
    private TableColumn<BloodPressureData,Integer> DBPColumn;
    @FXML
    private TableColumn<BloodPressureData, Date> whenColumn;
    @FXML
    private TextField SBPField;
    @FXML
    private TextField DBPField;
    @FXML
    private DatePicker dateField;
    @FXML
    private Label statusLabel;
    @FXML
    private Button addButton;
    private HomePage homePageController;

    // Set Controller
    public void setHomePageController(HomePage homePageController){
        this.homePageController = homePageController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("cat"));
        SBPColumn.setCellValueFactory(new PropertyValueFactory<>("SBP"));
        DBPColumn.setCellValueFactory(new PropertyValueFactory<>("DBP"));
        whenColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateField.setValue(new Date(Calendar.getInstance().getTime().getTime()).toLocalDate());
        loadTable();
    }

    // Populate the table
    private void loadTable(){
        /*
        ObservableList<BloodPressureData> data = FXCollections.observableArrayList();

        try {
            ResultSet rs = DatabaseController.getResultSet("SELECT * FROM bloodpressure WHERE user_id = '" +
                    Paziente.getInstance().getPatientId() + "'");
            while (rs.next()){
                BloodPressureData bloodpressure = new BloodPressureData(rs.getInt("sbp"),
                        rs.getInt("dbp"),
                        rs.getDate("date"));;
                data.add(bloodpressure);
            }
            tableView.setItems(data);
        } catch(SQLException e){
            e.printStackTrace();
        }


         */
    }

    // Add a new measurement
    @FXML
    private void addMeasurement(ActionEvent event){

        /*
        if(SBPField.getText().equals("") || DBPField.getText().equals("") || dateField.getValue() == null) {
            statusLabel.setText("Couldn't Add Blood Data");
            statusLabel.setVisible(true);
            statusLabel.setTextFill(Color.rgb(211,81,81));
        } else {
            String query = "INSERT INTO bloodpressure (user_id, sbp, dbp, date)" +
                    "VALUES ('"+ Paziente.getInstance().getPatientId() +"', '" +
                    Integer.parseInt(SBPField.getText()) + "', '" +
                    Integer.parseInt(DBPField.getText()) + "', '" +
                    dateField.getValue() + "')";
            DatabaseController.updateItem(query);

            // verifica della pressione alta e notifica al medico
            Date pressure_date = new Date(dateField.getValue().getYear(), dateField.getValue().getMonthValue(), dateField.getValue().getDayOfMonth());
            BloodPressureData pressure = new BloodPressureData(Integer.parseInt(SBPField.getText()), Integer.parseInt(DBPField.getText()), pressure_date);

            if(!(pressure.getCat().equals("Normal") || pressure.getCat().equals("Optimal") || pressure.getCat().equals("Normal - High"))) {
                // inserisco l'alert nel database
                query = "INSERT INTO alert (user_id, type, indication) VALUES (" + Paziente.getInstance().getPatientId() +
                        ", 0, '" + pressure.getCat() + "')";
                DatabaseController.updateItem(query);
            }

            statusLabel.setText("Blood Data Added!");
            statusLabel.setVisible(true);
            statusLabel.setTextFill(Color.rgb(60,176,80));
            SBPField.clear();
            DBPField.clear();
            dateField.setValue(new Date(Calendar.getInstance().getTime().getTime()).toLocalDate());

            if(homePageController != null){
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                homePageController.checkList();
            }
        }

        // reloads the table
        loadTable();

         */
    }

    // Remove the selected measurement form table
    @FXML
    private void removeSelected(ActionEvent event){
        /*
        try {
            BloodPressureData selectedRow = tableView.getSelectionModel().getSelectedItem();

            DatabaseController.updateItem("DELETE FROM bloodpressure " +
                    "WHERE user_id = '" + Paziente.getInstance().getPatientId() + "' AND sbp = '" +
                    selectedRow.getSBP() + "'  " +
                    " AND dbp = '" + selectedRow.getDBP()  +"' AND date = '" +
                    selectedRow.getDate()  + "'");

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            tableView.getItems().remove(selectedIndex);

            statusLabel.setText("Blood data removed");
            statusLabel.setVisible(true);
            statusLabel.setTextFill(Color.rgb(60,176,80));
        } catch (Exception e){
            statusLabel.setText("Couldn't remove blood data");
            statusLabel.setVisible(true);
            statusLabel.setTextFill(Color.rgb(211,81,81));
        }

         */
    }
/*
    // Check if the user already submitted their blood pressure today
    public static boolean hasDailyPressure() throws SQLException {

        try {
            Date date = Date.valueOf(LocalDate.now());
            ResultSet rs = DatabaseController.getResultSet("SELECT * FROM bloodpressure WHERE user_id = '" +
                    Paziente.getInstance().getPatientId() + "' AND date = '" + date + "'");
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

 */

    // Support method
    public void changeAddButton(){
        addButton.setText("Add and Close");
    }
}
