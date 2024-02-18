package it.univr.ipertensione_hope.Controller.admin;

import it.univr.ipertensione_hope.Functions;
import it.univr.ipertensione_hope.Model.Medico;
import it.univr.ipertensione_hope.Model.Paziente;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterPatient implements Initializable {
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField codiceFField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private DatePicker bDayField;
    @FXML
    private ChoiceBox<Medico> selezionaMedico;

    // per inizializzare i medici selezionabili
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Medico [] medici = Medico.getAll();
        if(medici != null) {
            // la stringa visualizzata tra le opzioni è quella che ritorna il metodo toString della classe Medico
            selezionaMedico.setItems(FXCollections.observableArrayList(medici));
        } else {
            System.out.println("Impossibile caricare le informazioni dei medici");
        }

        selezionaMedico.setOnAction(event -> {
            Medico selezionato = selezionaMedico.getValue();
            System.out.println(selezionato.getDoctorId());
        });

    }

    @FXML
    private void savePatient(ActionEvent event) {
        if(     nameField.getText().isEmpty() ||
                surnameField.getText().isEmpty() ||
                emailField.getText().isEmpty() ||
                passwordField.getText().isEmpty() ||
                codiceFField.getText().isEmpty() ||
                bDayField.getValue() == null ||
                selezionaMedico.getValue() == null) {

            Functions.alert("Compilare tutti i campi", Alert.AlertType.ERROR, null);

        } else if(!checkCF()) {
            Functions.alert("Codice fiscale non valido", Alert.AlertType.ERROR, null);
        } else if (!checkEM()) {
            Functions.alert("Ricontrolla la email", Alert.AlertType.ERROR, null);

        } else if (passwordField.getText().length() < 8) {
            Functions.alert("La password deve avere almeno 8 caratteri", Alert.AlertType.ERROR, null);
        }
        else if (bDayField.getValue().getYear() < 1910 || bDayField.getValue().getYear() > LocalDate.now().getYear()) {
            Functions.alert("Inserire una data di nascita valida", Alert.AlertType.ERROR, null);

        } else {
            Paziente paziente = (Paziente) Paziente.findUserDB(codiceFField.getText());
            if(paziente != null) {
                Functions.alert("Questo paziente è già esistente nel database", Alert.AlertType.ERROR, null);
            } else {
                paziente = new Paziente(
                        nameField.getText(),
                        surnameField.getText(),
                        emailField.getText(),
                        passwordField.getText(),
                        codiceFField.getText(),
                        Date.valueOf(bDayField.getValue()),
                        selezionaMedico.getValue().getDoctorId()
                );
                if(Paziente.addPatient(paziente)){
                    Functions.alert("Il paziente è stato correttamente inserito nel database", Alert.AlertType.INFORMATION, (ButtonType button) -> {
                        WindowsManager.loadPage(WindowsManager.mainClass.getResource("admin/AdminDashboard.fxml"), "admin/AdminDashboard.fxml");
                        WindowsManager.reloadPage();
                    });
                } else {
                    Functions.alert("Errore inaspettato durante l'inserimento", Alert.AlertType.ERROR, null);
                }
            }



        }
    }

    private boolean checkCF(){
        //String regex = "^[A-Z]{6}\d{2}[0-9]\d[A-Z]\d{2}[0-9]\d[A-Z]\d{3}[0-9]\d[A-Z]$";
        String regex ="^[A-Z]{3}[A-Z]{3}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}$";
        Matcher matcher = Pattern.compile(regex).matcher(codiceFField.getText());
        return matcher.matches();
    }
    private  boolean checkEM(){
        String regex = "^[a-zA-Z0-9_.+-]+@(gmail|outlook|libero).(com|it)$";
        Matcher matcher = Pattern.compile(regex).matcher(emailField.getText());
        return matcher.matches();
    }
}
