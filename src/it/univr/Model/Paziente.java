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
    public static User findUserDB(String username, String password) {
        String query = "SELECT * FROM " + tableName + " WHERE email = '" + username + "' AND password = '" + password + "'";

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

    // aggiunge un paziente nel db
    public static boolean addPatient(Paziente paziente) {
        String query = "INSERT INTO " + tableName + "('" +
                nameField + "', '" +
                surnameField + "', '" +
                emailField + "', '" +
                passwordField + "', '" +
                CFField + "', '" +
                bdayField + "', '" +
                riskField + "', " +
                doctorIdField +
                ") " +
                "VALUES(" +
                "'" + paziente.getNome() + "', " +
                "'" + paziente.getCognome() + "', " +
                "'" + paziente.getEmail() + "', " +
                "'" + paziente.getPassword() + "', " +
                "'" + paziente.getCodiceF() + "', " +
                "'" + paziente.getbDay() + "', " +
                "'" + paziente.getFattoriDiRischio() + "', " +
                paziente.getDoctorId() +
                ")";

        return DatabaseManager.updateItem(query);
    }

}
