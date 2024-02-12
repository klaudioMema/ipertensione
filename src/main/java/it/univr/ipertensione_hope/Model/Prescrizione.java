package it.univr.ipertensione_hope.Model;


import it.univr.ipertensione_hope.Controller.DatabaseController;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Prescrizione {

    private final static String tableName = "prescriptions";
    private final static String patientIdField = "user_id";
    private final static String prescriptionNameField = "medication";
    private final static String indicationField = "indications";
    private final static String fromDateField = "fromDate";
    private final static String toDateField = "toDate";

    private int userId;
    private String medication;
    private String indications;
    private int days;
    private Date fromDate;
    private Date toDate;

    public Prescrizione(String medication, String indications, int days, Date fromDate){
        this.medication = medication;
        this.indications = indications;
        this.days = days;
        this.fromDate = fromDate;
    }

    public Prescrizione(int userId, String medication, String indications, Date fromDate, Date toDate) {
        this.userId = userId;
        this.medication = medication;
        this.indications = indications;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }


    public String getMedication(){
        return medication;
    }
    public void setMedication(String medication){
        this.medication = medication;
    }


    public String getIndications(){
        return indications;
    }
    public void setWhen(String indication) {
        this.indications = indication;
    }
    public Date getFromDate() {
        return fromDate;
    }
    public void setFromDate(Date fromDate){
        this.fromDate = fromDate;
    }


    public int getDays(){
        return days;
    }
    public void setDays(int days) {
        this.days = days;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Prescrizione other = (Prescrizione) obj;

        // Compare all instance variables for equality
        return medication.equals(other.medication) &&
                indications.equals(other.indications) &&
                days == other.days &&
                fromDate.equals(other.fromDate);
    }

    // aggiunge una prescrizione nella tabella
    public boolean add() {
        String query = "INSERT INTO " + tableName + " (" + patientIdField + ", " + prescriptionNameField + ", " +
                   indicationField + ", " + fromDateField + ", " + toDateField + ") VALUES (" + userId + ", '" +
                   medication + "', '" + indications + "', '" + fromDate + "', '" + toDate + "')";

        return DatabaseManager.updateItem(query);
    }

    // ottiene tutte le prescrizioni di un certo paziente
    public static Prescrizione[] getAllByPatient(Paziente paziente) {
        String query = "SELECT * FROM " + tableName + " WHERE " +
                patientIdField + " = " + paziente.getPatientId();

        List<Prescrizione> prescrizioniList = new ArrayList<>();
        ResultSet set = DatabaseController.getResultSet(query);

        try {
            while(set.next()) {
                int userId = set.getInt(patientIdField);
                String prescriptionName = set.getString(prescriptionNameField);
                String indication = set.getString(indicationField);
                Date fromDate = set.getDate(fromDateField);
                Date toDate = set.getDate(toDateField);

                Prescrizione prescrizione = new Prescrizione(userId, prescriptionName, indication, fromDate, toDate);
                prescrizioniList.add(prescrizione);

            }
        } catch(SQLException | NullPointerException e) {
            return null;
        }

        return prescrizioniList.toArray(new Prescrizione[0]);
    }
}
