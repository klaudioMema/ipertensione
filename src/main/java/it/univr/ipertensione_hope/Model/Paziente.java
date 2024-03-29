package it.univr.ipertensione_hope.Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Paziente extends User{

    private int patientId;

    private String codiceF;
    private Date bDay;
    private String fattoriDiRischio;
    private int doctorId;

    private final static String tableName = "patients";
    private final static String nameField = "nome";
    private final static String surnameField = "cognome";
    private final static String emailField = "email";
    private final static String passwordField = "password";
    private final static String idField = "user_id";
    private final static String CFField = "codicef";
    private final static String bdayField = "bday";
    private final static String doctorIdField = "doctor_id";
    private final static String riskField = "fattoridirischio";


    // campi completi
    public Paziente(String name, String cognome, String email, String password, int patientId, String codiceF, Date bDay, String fattoriDiRischio, int doctorId ){
        super(name, cognome, email, password);
        this.patientId = patientId;
        this.codiceF = codiceF;
        this.bDay = bDay;
        this.fattoriDiRischio = fattoriDiRischio;
        this.doctorId = doctorId;
    }

    // costruttore per inserire nuovi pazienti nel database
    public Paziente(String name, String cognome, String email, String password, String codiceF, Date bDay, int doctorId ){
        super(name, cognome, email, password);
        this.codiceF = codiceF;
        this.bDay = bDay;
        this.doctorId = doctorId;
        this.fattoriDiRischio = "";
    }

    public Paziente(){}

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getCodiceF() {
        return codiceF;
    }

    public void setCodiceF(String codiceF) {
        this.codiceF = codiceF;
    }

    public Date getbDay() {
        return bDay;
    }

    public void setbDay(Date bDay) {
        this.bDay = bDay;
    }

    public String getFattoriDiRischio() {
        return fattoriDiRischio;
    }

    public void setFattoriDiRischio(String fattoriDiRischio) {
        this.fattoriDiRischio = fattoriDiRischio;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    
    public StringProperty nomeProperty() {
        return new SimpleStringProperty(this.getNome());
    }

    public StringProperty cognomeProperty() {
        return new SimpleStringProperty(this.getCognome());
    }

    public StringProperty mailProperty() {
        return new SimpleStringProperty(this.getEmail());
    }

    private static User findUserDBAux (String query) {
        try(ResultSet resultSet = DatabaseManager.getItem(query)) {
            resultSet.next();

            String name = resultSet.getString(nameField);
            String cognome = resultSet.getString(surnameField);
            String email = resultSet.getString(emailField);
            String psw = resultSet.getString(passwordField);
            int patientId = resultSet.getInt(idField);
            String codiceF = resultSet.getString(CFField);
            Date bDay = resultSet.getDate(bdayField);
            String fattoriDiRischio = resultSet.getString(riskField);
            int patientDoctorId = resultSet.getInt(doctorIdField);

            return new Paziente(name, cognome, email, psw, patientId, codiceF, bDay, fattoriDiRischio, patientDoctorId);

        } catch(SQLException e) {
            return null;
        }
    }

    // cerca il paziente nel database per username e password e ritorna i suoi dati
    public static User findUserDB(String username, String password) {
        String query = "SELECT * FROM " + tableName + " WHERE email = '" + username + "' AND password = '" + password + "'";
        return findUserDBAux(query);
    }

    // ricerca per codice fiscale
    public static User findUserDB(String codiceF) {
        String query = "SELECT * FROM " + tableName + " WHERE " + CFField + " = '" + codiceF + "'";
        return findUserDBAux(query);
    }

    // aggiunge un paziente nel db
    public static boolean addPatient(Paziente paziente) {
        String query = "INSERT INTO " + tableName + "(" +
                nameField + ", " +
                surnameField + ", " +
                emailField + ", " +
                passwordField + ", " +
                CFField + ", " +
                bdayField + ", " +
                doctorIdField +
                ") " +
                "VALUES(" +
                "'" + paziente.getNome() + "', " +
                "'" + paziente.getCognome() + "', " +
                "'" + paziente.getEmail() + "', " +
                "'" + paziente.getPassword() + "', " +
                "'" + paziente.getCodiceF() + "', " +
                "'" + paziente.getbDay() + "', " +
                paziente.getDoctorId() +
                ")";

        return DatabaseManager.updateItem(query);
    }

    // funzione che carica i pazienti ottenuti dal database in un vettore
    private static Paziente[] loadPatients(ResultSet set) {
        List<Paziente> pazientiList = new ArrayList<>();

        try {
            while(set.next()) {
                int patientId = set.getInt(idField);
                int doctorId = set.getInt(doctorIdField);
                String name = set.getString(nameField);
                String cognome = set.getString(surnameField);
                String email = set.getString(emailField);
                String password = set.getString(passwordField);
                String codiceF = set.getString(CFField);
                Date bDay = set.getDate(bdayField);
                String fattoriDiRischio = set.getString(riskField);

                Paziente paziente = new Paziente(name,cognome,email,password,patientId,codiceF,bDay,fattoriDiRischio,doctorId);
                pazientiList.add(paziente);
            }
        } catch(SQLException | NullPointerException e) {
            return null;
        }

        return pazientiList.toArray(new Paziente[0]);
    }

    // Metodo per ottenere un paziente dal database dato il suo ID
    public static Paziente getById(int id) {
        String query = "SELECT * FROM " + tableName + " WHERE " + idField + " = " + id;
        ResultSet resultSet = DatabaseManager.getItem(query);

        try {
            if (resultSet.next()) {
                String nome = resultSet.getString(nameField);
                String cognome = resultSet.getString(surnameField);
                String email = resultSet.getString(emailField);
                String password = resultSet.getString(passwordField);
                String risk = resultSet.getString(riskField);
                String codiceF = resultSet.getString(CFField);
                int patientId = resultSet.getInt(idField);
                int doctorId = resultSet.getInt(doctorIdField);
                Date bday = resultSet.getDate(bdayField);


                // Recupera altri campi se necessario
                return new Paziente(nome, cognome, email, password, patientId, codiceF, bday, risk, doctorId);
            }
        } catch (SQLException e) {
            return null;
        }
        return null; // Ritorna null se il paziente non viene trovato
    }

    // ritorna i pazienti di un certo dottore
    public static Paziente[] getAllByDoctor(Medico medico) {
        String query = "SELECT * FROM " + tableName + " WHERE " + doctorIdField + " = " + medico.getDoctorId();
        ResultSet set = DatabaseManager.getItem(query);

        return loadPatients(set);
    }
    
    public static Paziente[] getAll() {
        String query = "SELECT * FROM " + tableName;
        ResultSet set = DatabaseManager.getItem(query);

        return loadPatients(set);
    }

    // elimina dal database il paziente
    public boolean delete() {
        String query = "DELETE FROM " + Paziente.tableName +
                " WHERE " + Paziente.idField + " = " +
                this.getPatientId();

        return DatabaseManager.updateItem(query);
    }
    public boolean updateRisk() {
        String query = "UPDATE " + tableName + " SET " + riskField + " = '" + fattoriDiRischio +
                "' WHERE " + idField + " = " + patientId;

        return DatabaseManager.updateItem(query);
    }
}
