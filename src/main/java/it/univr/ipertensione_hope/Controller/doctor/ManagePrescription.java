package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Controller.admin.ManageDoctor;
import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.Medico;
import it.univr.ipertensione_hope.Model.Paziente;
import it.univr.ipertensione_hope.Model.Prescrizione;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagePrescription implements Initializable {
    @FXML
    private Button eliminaButton;
    @FXML
    private TableView<Prescrizione> listaPrescizioni;
    @FXML
    private Button modificaButton;
    private Paziente paziente;
    public void initialize(URL location, ResourceBundle resources) {
        Prescrizione[] prescrizione = Prescrizione.getAllByPatient(paziente);

        // Creazione della tabella
        listaPrescizioni.setItems(FXCollections.observableArrayList(prescrizione));

        // Colonne per le informazioni dei Prescizioni
        TableColumn<Prescrizione, String> medicationCol = new TableColumn<>("medication");
        medicationCol.setCellValueFactory(cellData -> cellData.getValue().medicationProperty());

        TableColumn<Prescrizione, String> indicationsCol = new TableColumn<>("indications");
        indicationsCol.setCellValueFactory(cellData -> cellData.getValue().indicationsProperty());


    }
    @FXML
    void elimina() {
        Prescrizione prescrizioneSelezionata = listaPrescizioni.getSelectionModel().getSelectedItem();
        if(prescrizioneSelezionata == null) {
            Functions.alert("Selezionare prima un medico dalla tabella", Alert.AlertType.INFORMATION, null);
        } else {
            if(prescrizioneSelezionata.delete()){
                WindowsManager.reloadPage();
            } else {
                Functions.alert("Errore inaspettato durnte l'eliminazione", Alert.AlertType.ERROR, null);
            }

        }
    }

    void modifica(){
        /*
        Prescrizione prescrizioneSelezionata = listaPrescizioni.getSelectionModel().getSelectedItem();

        if(prescrizioneSelezionata == null) {
            Functions.alert("Selezionare prima un prescrizione", Alert.AlertType.INFORMATION, null);
        } else {
            String path = directory + "";
            FXMLLoader loader = WindowsManager.nextPage(WindowsManager.mainClass.getResource(path), path);

            if(loader != null) {
                (() loader.getController()).setData(prescrizioneSelezionata);
            } else {
                System.out.println("Impossibile aprire la prescrizione");
            }
        }
        */


    }


}
