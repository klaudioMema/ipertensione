package it.univr.ipertensione_hope.Model;

import it.univr.ipertensione_hope.Controller.DatabaseController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.StringProperty;

public class Medico extends User{

    public final static String tableName = "medics";
    public final static String nameField = "nome";
    public final static String surnameField = "cognome";
    public final static String emailField = "email";
    public final static String passwordField = "password";
    public final static String idField = "doctor_id";

    private int doctorId;

    public Medico(String name, String cognome, String email, String password, int doctorId){
        super(name, cognome, email, password);
        this.doctorId = doctorId;
    }

    public Medico(String name, String cognome, String email, String password){
        super(name, cognome, email, password);
    }

    public Medico(){}

    // si può rendere non statica
    public static boolean updateDoctor(Medico medico) {
        String query = "UPDATE " + Medico.tableName + " SET " +
                Medico.nameField + " = '" + medico.getNome() + "', " +
                Medico.surnameField + " = '" + medico.getCognome() + "', " +
                Medico.emailField + " = '" + medico.getEmail() + "', " +
                Medico.passwordField + " = '" + medico.getPassword() + "' " +
                " WHERE " + Medico.idField + " = " + medico.getDoctorId();

        return DatabaseManager.updateItem(query);
    }

    public int getDoctorId(){
        return doctorId;
    }

    public void setDoctorId(int doctorId){
        this.doctorId = doctorId;
    }

    // cerca un medico nel db e lo ritorna se lo trova
    public static User findUserDB(String username, String password) {
        String query = "SELECT * FROM " + tableName + " WHERE email = '" + username + "' AND password = '" + password + "'";
        return findUserDBAux(query);
    }

    // cerca un medico in base alla mail nel database e lo ritorna se lo trova
    public static Medico findUserDB(String username) {
        String query = "SELECT * FROM " + tableName + " WHERE email = '" + username + "'";
        return findUserDBAux(query);
    }

    private static Medico findUserDBAux(String query) {
        try {
            ResultSet resultSet = DatabaseManager.getItem(query);
            resultSet.next();

            String name = resultSet.getString(nameField);
            String cognome = resultSet.getString(surnameField);
            String email = resultSet.getString(emailField);
            String psw = resultSet.getString(passwordField);
            int doctorId = resultSet.getInt(idField);

            return new Medico(name, cognome, email, psw, doctorId);
        } catch(SQLException e) {
            return null;
        }
    }

    // aggiunge un medico nel db
    public static boolean addDoctor(Medico medico) {
        String query = "INSERT INTO " + tableName + "(" +
                nameField + "," +
                surnameField + "," +
                emailField + "," +
                passwordField + ") " +
                "VALUES(" +
                "'" + medico.getNome() + "', " +
                "'" + medico.getCognome() + "', " +
                "'" + medico.getEmail() + "', " +
                "'" + medico.getPassword() + "')";

        return DatabaseManager.updateItem(query);
    }

    // ritorna tutti i medici dal database
    public static Medico[] getAll() {
        String query = "SELECT * FROM " + tableName;
        List<Medico> mediciList = new ArrayList<>();
        ResultSet set;

        set = DatabaseController.getResultSet(query);
        try {
            while(set.next()) {
                int doctorId = set.getInt(idField);
                String name = set.getString(nameField);
                String cognome = set.getString(surnameField);
                String email = set.getString(emailField);
                String password = set.getString(passwordField);

                Medico medico = new Medico(name, cognome, email, password, doctorId);
                mediciList.add(medico);
            }
        } catch(SQLException | NullPointerException e) {
            return null;
        }

        return mediciList.toArray(new Medico[0]);

    }

    // elimina dal database il medico
    public boolean delete() {
        String query = "DELETE FROM " + Medico.tableName +
                " WHERE " + Medico.idField + " = " +
                this.getDoctorId();

        return DatabaseManager.updateItem(query);
    }

    public String toString() {
        return this.getNome() + " " + this.getCognome();
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
}
