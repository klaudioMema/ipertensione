package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Model.Paziente;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectPatient implements Initializable {
    private final String directory = "doctor/";
    private TableView<Paziente> listaPaziente;

    @FXML
    private Button selezionaButton;
    @FXML
    private TableView<Paziente> listaPazienti;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Paziente[] pazienti = Paziente.getAll();

        listaPazienti.setItems(FXCollections.observableArrayList(pazienti));

        TableColumn<Paziente, String> nomeCol = new TableColumn<>("nome");
        nomeCol.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());

        TableColumn<Paziente, String> cognomeCol = new TableColumn<>("cognome");
        cognomeCol.setCellValueFactory(cellData -> cellData.getValue().cognomeProperty());

        TableColumn<Paziente, String> mailCol = new TableColumn<>("mail");
        mailCol.setCellValueFactory(cellData -> cellData.getValue().mailProperty());
    }


    @FXML
    private void seleziona() {

    }
}
