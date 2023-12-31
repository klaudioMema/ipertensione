package it.univr.Controller.patient;

import it.univr.Controller.DatabaseController;
import it.univr.Model.Paziente;
import it.univr.Model.Sintomo;
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
import java.util.Calendar;
import java.util.ResourceBundle;

public class ViewSymptomsPatient implements Initializable {
    public TableView<Sintomo> tableView;
    public TableColumn<Sintomo, String> nameColumn;
    public TableColumn<Sintomo, String>  descriptionColumn;
    public TableColumn<Sintomo, Date>  startColumn;
    public TableColumn<Sintomo, Date>  endColumn;
    public TextField nameField;
    public TextArea descriptionField;
    public DatePicker startField;
    public DatePicker endField;
    public Label statusLabel;
    @FXML
    public Button addButton;
    private HomePage homePageController;

    public void setHomePageController(HomePage homePageController){
        this.homePageController = homePageController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        startField.setValue(new Date(Calendar.getInstance().getTime().getTime()).toLocalDate());
        loadTable();
    }

    
    private void loadTable(){
        /*
        ObservableList<Sintomo> data = FXCollections.observableArrayList();

        try {
            ResultSet rs = DatabaseController.getResultSet("SELECT * FROM symptoms WHERE user_id = '" +
                    Paziente.getInstance().getPatientId() + "'");
            while (rs.next()){
                Sintomo symptoms = new Sintomo(rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDate("start"),
                        rs.getDate("end"));;
                data.add(symptoms);
            }
            tableView.setItems(data);
        } catch(SQLException e){
            e.printStackTrace();
        }

         */
    }

   
    @FXML
    private void addSymptom(ActionEvent event) {
        /*
        if (nameField.getText().equals("") || descriptionField.getText().equals("") ||
                startField.getValue() == null) {
            statusLabel.setText("Please fill in the data first");
            statusLabel.setVisible(true);
            statusLabel.setTextFill(Color.rgb(211, 81, 81));
        } else if (endField.getValue() == null) {
            endField.setValue(startField.getValue());
            statusLabel.setVisible(true);
            statusLabel.setText("Changed end data for today");
        } else {
            String query = "INSERT INTO symptoms (user_id, name, description, start, end)" +
                    "VALUES ('"+ Paziente.getInstance().getPatientId() +"', '" +
                    nameField.getText() + "', '" +
                    descriptionField.getText() + "', '" +
                    startField.getValue() + "', '" +
                    endField.getValue() + "')";
            DatabaseController.updateItem(query);
            statusLabel.setText("New Symptom reported!");
            statusLabel.setVisible(true);
            statusLabel.setTextFill(Color.rgb(60,176,80));
            nameField.clear();
            descriptionField.clear();
            startField.setValue(new Date(Calendar.getInstance().getTime().getTime()).toLocalDate());
            endField.setValue(null);

            if(homePageController != null){
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                homePageController.checkList();
            }
        }

        loadTable();

         */
    }

    //TODO
    @FXML
    private void removeSelected(){
        /*
        Sintomo selectedRow;
        try {
            if(tableView.getSelectionModel().getSelectedItem() == null){
                statusLabel.setVisible(true);
                statusLabel.setText("You need to select and item first!");
            } else {
                selectedRow = tableView.getSelectionModel().getSelectedItem();

                DatabaseController.updateItem("DELETE FROM symptoms " +
                        "WHERE user_id = '" + Paziente.getInstance().getPatientId() +
                        "' AND name = '" + selectedRow.getName() +
                        "' AND description = '" + selectedRow.getDescription() +
                        "' AND end = '" + selectedRow.getEnd() +
                        "' AND end = '" + selectedRow.getEnd() + "'");

                int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
                tableView.getItems().remove(selectedIndex);

                statusLabel.setText("Symptom removed");
                statusLabel.setVisible(true);
                statusLabel.setTextFill(Color.rgb(60, 176, 80));
            }
            } catch (Exception e){
                statusLabel.setText("Couldn't remove symptom");
                statusLabel.setVisible(true);
                statusLabel.setTextFill(Color.rgb(211,81,81));
            }

         */
    }

    public void changeAddButton(){
        addButton.setText("Add and close");
    }


}
