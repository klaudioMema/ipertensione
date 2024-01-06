package it.univr.Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Paziente extends User{

    private int patientId;

    private String codiceF;
    private Date bDay;
    private String fattoriDiRischio;
    private int doctorId;


    public Paziente(String name, String cognome, String email, String password, int patientId, String codiceF, Date bDay, String fattoriDiRischio, int doctorId ){
        super(name, cognome,email,password);
        this.patientId = patientId;
        this.codiceF = codiceF;
        this.bDay = bDay;
        this.fattoriDiRischio = fattoriDiRischio;
        this.doctorId = doctorId;
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

    // cerca il paziente nel database e ritorna i suoi dati
    public User findUserDB(String username, String password) {
        String tableName = "patients";
        String query = "SELECT * FROM " + tableName + " WHERE username = " + username + " AND password = " + password;

        ResultSet resultSet = DatabaseManager.getItem(query);

        if (resultSet != null) {
            try {
                String name = resultSet.getString("nome");
                String cognome = resultSet.getString("cognome");
                String email = resultSet.getString("email");
                String psw = resultSet.getString("password");
                int patientId = resultSet.getInt("patientId");
                String codiceF = resultSet.getString("codiceF");
                Date bDay = resultSet.getDate("bday");
                String fattoriDiRischio = resultSet.getString("fattoriDiRischio");
                int patientDoctorId = resultSet.getInt("doctorId");

                return new Paziente(name, cognome, email, psw, patientId, codiceF, bDay, fattoriDiRischio, patientDoctorId);

            } catch(SQLException e) {
                return null;
            }
        } else {
            return null;
        }
    }

}
