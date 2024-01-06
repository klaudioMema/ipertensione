package it.univr.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Medico extends User{

    private int doctorId;

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

    public User findUserDB(String username, String password) {
        String tableName = "medics";
        String query = "SELECT * FROM " + tableName + " WHERE email = '" + username + "' AND password = '" + password + "'";

        try(ResultSet resultSet = DatabaseManager.getItem(query)) {
                resultSet.next();

                String name = resultSet.getString("name");
                String cognome = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String psw = resultSet.getString("password");
                int doctorId = resultSet.getInt("doctor_id");

                return new Medico(name, cognome, email, psw, doctorId);
        } catch(SQLException e) {
              return null;
        }
    }
}
