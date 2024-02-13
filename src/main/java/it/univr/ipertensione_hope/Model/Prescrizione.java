package it.univr.ipertensione_hope.Model;


import it.univr.ipertensione_hope.Controller.DatabaseController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Prescrizione {

    private final static String tableName = "prescriptions";
    private final static String patientIdField = "user_id";
    private final static String prescriptionNameField = "medication";
    private final static String indicationField = "indications";
    private final static String fromDateField = "fromDate";
    private final static String toDateField = "toDate";
    private final static String assumptionField = "assumption";

    private int userId;
    private String medication;
    private String indications;
    private int days;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int assumption;

    @Deprecated
    public Prescrizione(String medication, String indications, int days, LocalDate fromDate){
        this.medication = medication;
        this.indications = indications;
        this.days = days;
        this.fromDate = fromDate;
    }

    public Prescrizione(int userId, String medication, String indications, LocalDate fromDate, LocalDate toDate) {
        this.userId = userId;
        this.medication = medication;
        this.indications = indications;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.assumption = calculateAssumption();
    }

    /*Calcolo dei giorni consecutivi di assunzione del farmaco
    *
    *
    *
    * */
    private int calculateAssumption() {
        int assumption;
        LocalDate today = LocalDate.now();

        if(!today.isBefore(fromDate) && !today.isAfter(toDate)) { // se today è compresa tra le altre due
            assumption = (int) ChronoUnit.DAYS.between(fromDate, today) + 1;
        } else {
            assumption = 0;
        }

        System.out.println("Assumption: " + assumption);

        return assumption;
    }

    public String getMedication() {
        return medication;
    }
    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getIndications() {
        return indications;
    }
    public void setIndications(String indications) {
        this.indications = indications;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }
    public void setFromDate(LocalDate fromDate){
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
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
                   indicationField + ", " + fromDateField + ", " + toDateField + ", " + assumptionField + ") VALUES (" + userId + ", '" +
                   medication + "', '" + indications + "', '" + fromDate + "', '" + toDate + "', " + assumption + ")";

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
                LocalDate fromDate = set.getDate(fromDateField).toLocalDate();
                LocalDate toDate = set.getDate(toDateField).toLocalDate();

                Prescrizione prescrizione = new Prescrizione(userId, prescriptionName, indication, fromDate, toDate);
                prescrizioniList.add(prescrizione);

            }
        } catch(SQLException | NullPointerException e) {
            return null;
        }

        return prescrizioniList.toArray(new Prescrizione[0]);
    }
}
