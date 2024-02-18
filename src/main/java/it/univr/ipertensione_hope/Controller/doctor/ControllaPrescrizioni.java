package it.univr.ipertensione_hope.Controller.doctor;
import it.univr.ipertensione_hope.Model.Paziente;
import it.univr.ipertensione_hope.Model.Prescrizione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.sql.Date;

public class ControllaPrescrizioni implements Initializable {
    @FXML
    private Label PatientLabel;
    @FXML
    private Label SelectPatient;
    @FXML
    private TableColumn<Prescrizione, Date> fromDateCol;
    @FXML
    private TableColumn<Prescrizione, String> indicationCol;
    @FXML
    private TableView<Prescrizione> listaPazienti;
    @FXML
    private TableView<Prescrizione> listaPrescrizioni;
    @FXML
    private TableColumn<Prescrizione, String> medicationCol;
    @FXML
    private TableColumn<Prescrizione, Integer> notAssumptionCol;
    @FXML
    private TableColumn<Prescrizione, Date> toDateCol;
    private Paziente selectedPaziente;
    private Prescrizione prescrizione;

    public void initialize(URL location, ResourceBundle resources) {
            selectedPaziente = DoctorAppData.getInstance().getSelectedPatient();
            Prescrizione[] prescrizioni = Prescrizione.getAllByPatient(selectedPaziente);
            ArrayList<Prescrizione> controlloPrescrizione = new ArrayList<>();
        for( Prescrizione pre : prescrizioni)
                if(pre.getAssumption()>3)
                    controlloPrescrizione.add(pre);
            medicationCol.setCellValueFactory(new PropertyValueFactory<>("medication"));
            indicationCol.setCellValueFactory(new PropertyValueFactory<>("indications"));
            fromDateCol.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
            toDateCol.setCellValueFactory(new PropertyValueFactory<>("toDate"));
            notAssumptionCol.setCellValueFactory((new PropertyValueFactory<>("assumption")));
            if (!controlloPrescrizione.isEmpty()) {
                listaPrescrizioni.setItems(FXCollections.observableArrayList(controlloPrescrizione));
            } else {
                // Gestione dell'errore se non ci sono prescrizioni
                ObservableList<Prescrizione> emptyList = FXCollections.observableArrayList();
                emptyList.add(new Prescrizione("Nessuna prescrizione trovata", "", 0, null));
                listaPrescrizioni.setItems(emptyList);
            }
    }

}