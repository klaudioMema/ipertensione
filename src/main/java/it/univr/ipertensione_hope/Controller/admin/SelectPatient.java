package it.univr.ipertensione_hope.Controller.admin;

import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.Paziente;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;


public class SelectPatient  implements Initializable {
    private final String directory = "../../View/admin/";
    private TableView<Paziente> listaPaziente;

    @FXML
    private Button eliminaButton;
    @FXML
    private Label statusLabel;
    @FXML
    private TableView<Paziente> listaPazienti;
    @FXML
    private Button modificaButton;
    
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
    void modifica(ActionEvent event) {
        Paziente pazienteSelezionato = listaPaziente.getSelectionModel().getSelectedItem();
        if (pazienteSelezionato == null){
            Functions.alert("Il paziente non è stato selezionato", Alert.AlertType.ERROR, null);
        }else {
            String path = directory + "ManagePatient.fxml";
            FXMLLoader loader = WindowsManager.nextPage(WindowsManager.mainClass.getResource(path), path);

            if (loader != null) {
                ((ManagePatient) loader.getController()).setData(pazienteSelezionato);
            } else {
                System.out.println("Impossibile aprire la pagina");
            }
        }

    }
    
    @FXML
    void elimina(ActionEvent event) {

    }

    
}