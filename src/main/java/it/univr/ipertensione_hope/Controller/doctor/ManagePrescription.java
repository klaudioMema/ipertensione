package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Controller.admin.ManageDoctor;
import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.Medico;
import it.univr.ipertensione_hope.Model.Paziente;
import it.univr.ipertensione_hope.Model.Prescrizione;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ManagePrescription implements Initializable {

    @FXML
    private TableColumn<Prescrizione, String> indicationsColumn;
    @FXML
    private TableColumn<Prescrizione,String > medicationColumn;
    @FXML
    private TableColumn<Prescrizione, Date> fromDateColumn;
    @FXML
    private TableColumn<Prescrizione, Date> toDateColumn;
    @FXML
    private Button eliminaButton;
    @FXML
    private TableView<Prescrizione> listaPrescrizioni;
    @FXML
    private Button modificaButton;
    private Paziente selectedPaziente;
    private Prescrizione selectPrescription;
    private final  String directory = "doctor/";

    public void initialize(URL location, ResourceBundle resources) {
        selectedPaziente = DoctorAppData.getInstance().getSelectedPatient();
        Prescrizione[] prescrizioni = Prescrizione.getAllByPatient(selectedPaziente);
        medicationColumn.setCellValueFactory(new PropertyValueFactory<>("medication"));
        indicationsColumn.setCellValueFactory(new PropertyValueFactory<>("indications"));
        fromDateColumn.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        toDateColumn.setCellValueFactory(new PropertyValueFactory<>("toDate"));
        // Creazione della tabella

        // Caricamento dei dati nella tabella
        if (prescrizioni != null) {
            listaPrescrizioni.setItems(FXCollections.observableArrayList(prescrizioni));
        } else {
            // Gestione dell'errore se non ci sono prescrizioni
            ObservableList<Prescrizione> emptyList = FXCollections.observableArrayList();
            emptyList.add(new Prescrizione("Nessuna prescrizione trovata", "", 0, null));
            listaPrescrizioni.setItems(emptyList);
        }
    }

    public void elimina() {
        Prescrizione prescrizioneSelezionata = listaPrescrizioni.getSelectionModel().getSelectedItem();
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
    public void modifica(){
        Prescrizione prescrizioneSelezionata = listaPrescrizioni.getSelectionModel().getSelectedItem();
        if(prescrizioneSelezionata == null) {
            Functions.alert("Selezionare prima un prescrizione", Alert.AlertType.INFORMATION, null);
        } else {
            String path = directory + "ModifyPrescriptions.fxml";
            FXMLLoader loader = WindowsManager.nextPage(WindowsManager.mainClass.getResource(path), path);
            if(loader != null)
                ((ModifyPrescriptions) loader.getController()).setData(prescrizioneSelezionata);
            else System.out.println("Impossibile aprire la prescrizione");

        }
    }


}
