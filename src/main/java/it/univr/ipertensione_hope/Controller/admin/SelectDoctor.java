package it.univr.ipertensione_hope.Controller.admin;

import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.Medico;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectDoctor implements Initializable {

    @FXML
    private TableView<Medico> listaMedici;
    @FXML
    private Button eliminaButton;
    @FXML
    private Button modificaButton;
    @FXML
    private TextField emailField;
    @FXML
    private Button exitButton;
    @FXML
    private Button searchButton;

    private final String directory = "admin/";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Medico[] medici = Medico.getAll();

        // Creazione della tabella
        listaMedici.setItems(FXCollections.observableArrayList(medici));

        // Colonne per le informazioni dei medici
        TableColumn<Medico, String> nomeCol = new TableColumn<>("nome");
        nomeCol.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());

        TableColumn<Medico, String> cognomeCol = new TableColumn<>("cognome");
        cognomeCol.setCellValueFactory(cellData -> cellData.getValue().cognomeProperty());

        TableColumn<Medico, String> mailCol = new TableColumn<>("mail");
        mailCol.setCellValueFactory(cellData -> cellData.getValue().mailProperty());


    }

    public void modifica() {
        Medico medicoSelezionato = listaMedici.getSelectionModel().getSelectedItem();
        
        if(medicoSelezionato == null) {
            Functions.alert("Selezionare prima un medico", Alert.AlertType.INFORMATION, null);
        } else {
            String path = directory + "ManageDoctor.fxml";
            FXMLLoader loader = WindowsManager.nextPage(WindowsManager.mainClass.getResource(path), path);

            if(loader != null) {
                ((ManageDoctor) loader.getController()).setData(medicoSelezionato);
            } else {
                System.out.println("Impossibile aprire la pagina");
            }
        }
    }

    public void elimina() {
        Medico medicoSelezionato = listaMedici.getSelectionModel().getSelectedItem();

        if(medicoSelezionato == null) {
            Functions.alert("Selezionare prima un medico dalla tabella", Alert.AlertType.INFORMATION, null);
        } else {
            if(medicoSelezionato.delete()){
                WindowsManager.reloadPage();
            } else {
                Functions.alert("Errore inaspettato durnte l'eliminazione", Alert.AlertType.ERROR, null);
            }

        }
    }

}
