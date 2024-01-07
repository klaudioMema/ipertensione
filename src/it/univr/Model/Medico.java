package it.univr.Model;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Medico extends User{

    private int doctorId;
    private final static String tableName = "medics";
    private final static String nameField = "nome";
    private final static String surnameField = "cognome";
    private final static String emailField = "email";
    private final static String passwordField = "password";
    private final static String idField = "doctor_id";

    public Medico(String name, String cognome, String email, String password, int doctorId){
        super(name, cognome, email, password);
        this.doctorId = doctorId;
    }

    public Medico(){}

    public int getDoctorId(){
        return doctorId;
    }

    public void setDoctorId(int doctorId){
        this.doctorId = doctorId;
    }

    // cerca un medico nel db e lo ritorna se lo trova
    public static User findUserDB(String username, String password) {
        String query = "SELECT * FROM " + tableName + " WHERE email = '" + username + "' AND password = '" + password + "'";

        try(ResultSet resultSet = DatabaseManager.getItem(query)) {
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
                passwordField + "," + ") " +
                "VALUES(" +
                "'" + medico.getNome() + "'" +
                "'" + medico.getCognome() + "'" +
                "'" + medico.getEmail() + "'" +
                "'" + medico.getPassword() + "'" + ")";

        return DatabaseManager.updateItem(query);
    }
}
