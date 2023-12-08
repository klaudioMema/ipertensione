package it.univr.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.univr.Model.DatabaseManager;

import javax.xml.crypto.Data;

public class User {

    private String nome;
    private String cognome;
    private String email;
    private String password;

    public User(String nome, String cognome, String email, String password){
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome(){
        return nome;
    }

    public void  setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome){
        this.cognome = cognome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //funzione in ingresso la email, tipo di utente, va a cerarlo
    // nel database e carica l'oggetto se riesce a trovarlo ok senno un null

    public User findUserDB(String username, String password, UserType type) {
        String tableName = getTableName(type);
        String query = "SELECT * FROM " + tableName + " WHERE username = " + username + " AND password = " + password;

        ResultSet resultSet = DatabaseManager.executeQuery(query);

        if(res != null) {
            // int userId = resultSet.getInt("id");

            

            String name = resultSet.getString("name");
            String cognome = resultSet.getString("cognome");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            UserType type = UserType.valueOf(resultSet.getString("type"));

            switch (type) {
                case ADMIN:
                    return new AgenteSanitario(name, cognome, email, password);
                case MEDIC:
                    return new Medico(name, cognome, email, password, userId);
                case PATIENT:
                    // Supponiamo che i campi specifici del paziente siano presenti nel ResultSet
                    int patientId = resultSet.getInt("patientId");
                    String codiceF = resultSet.getString("codiceF");
                    Date bDay = resultSet.getDate("bDay");
                    String fattoriDiRischio = resultSet.getString("fattoriDiRischio");
                    int doctorId = resultSet.getInt("doctorId");
                    return new Paziente(name, cognome, email, password, userId, codiceF, bDay, fattoriDiRischio, doctorId);
                case AGENT:
                    return new AgenteSanitario(name, cognome, email, password, userId);
                default:
                    throw new IllegalArgumentException("Invalid user type");
            }
        } else {
            return null;
        }
    }

    private String getTableName(UserType type) {
        return switch (type) {
            case ADMIN -> "agentesanitario";
            case MEDICO -> "medics";
            case PAZIENTE -> "patients";
            default -> throw new IllegalArgumentException("Invalid user type");
        };
    }

}
