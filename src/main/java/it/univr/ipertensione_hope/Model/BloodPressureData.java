package it.univr.ipertensione_hope.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BloodPressureData {

    private int userId;
    private int SBP;
    private int DBP;
    private LocalDate date;
    private BloodPressureCategory category;
    private int sintomoId;

    private static final int MAX_SBP = 300;
    private static final int MAX_DBP = 200;

    private static final String tableName = "bloodpressure";
    private static final String sbpFieldName = "sbp";
    private static final String dbpFieldName = "dbp";
    private static final String dateFieldName = "date";
    private static final String userIdFieldName = "user_id";
    private static final String sintomoIdFieldName = "sintomo_id";

    public BloodPressureData(int userId, int SBP, int DBP, LocalDate date){
        this.userId = userId;
        this.SBP = SBP;
        this.DBP = DBP;
        this.date = date;
        this.category = classifyBloodPressure();
    }

    public BloodPressureData(int userId, int SBP, int DBP, LocalDate date, int sintomoId) {
        this(userId, SBP, DBP, date);
        this.sintomoId = sintomoId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public BloodPressureCategory getCategory() {
        return this.category;
    }

    public int getSBP() {
        return SBP;
    }
    public void setSBP(int SBP) {
        this.SBP = SBP;
    }

    public int getDBP() {
        return DBP;
    }
    public void setDBP(int DBP) {
        this.DBP = DBP;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getSintomoId() {
        return sintomoId;
    }
    public void setSintomoId(int sintomoId) {
        this.sintomoId = sintomoId;
    }

    // Classificazione della pressione
    public BloodPressureCategory classifyBloodPressure() {
        if (SBP >= 180 || DBP >= 120) {
            return BloodPressureCategory.HYPERTENSIVE_CRISIS;
        } else if (SBP >= 160 || DBP >= 100) {
            return BloodPressureCategory.HYPERTENSION_STAGE_2;
        } else if (SBP >= 140 || DBP >= 90) {
            return BloodPressureCategory.HYPERTENSION_STAGE_1;
        } else if (SBP >= 130 || DBP >= 85) {
            return BloodPressureCategory.HIGH_BLOOD_PRESSURE_STAGE_1;
        } else if (SBP >= 120 && DBP < 80) {
            return BloodPressureCategory.ELEVATED;
        } else {
            return BloodPressureCategory.NORMAL;
        }
    }

    public static boolean isValid(int sbp, int dbp) {
        return (sbp >= 0 && dbp >= 0) && (sbp <= MAX_SBP && dbp <= MAX_DBP) && dbp <= sbp;
    }

    public boolean add() {
        // Prepara la query per l'inserimento dei dati di pressione nel database
        String query = "INSERT INTO " + tableName + " (" + userIdFieldName + ", " + sbpFieldName + ", " + dbpFieldName + ", " + dateFieldName + ") " +
                "VALUES (" + userId + ", " + SBP + ", " + DBP + ", '" + date + "')";

        // Esegui la query per inserire i dati nel database
        return DatabaseManager.updateItem(query);
    }

    // aggiunge i dati assieme anche al sintomo associato
    public boolean addWithSymptom() {
        String query = "INSERT INTO " + tableName + " (" + userIdFieldName + ", " + sbpFieldName + ", " + dbpFieldName + ", " + dateFieldName + ", " + sintomoIdFieldName + ") " +
            "VALUES (" + userId + ", " + SBP + ", " + DBP + ", '" + date + "', " + sintomoId + ")";

        return DatabaseManager.updateItem(query);
    }

    // carica i dati di pressione ottenuti dal db in un vettore
    private static BloodPressureData[] loadBloodData(ResultSet set) {
        List<BloodPressureData> pressureDataList = new ArrayList<>();

        try {
            while (set.next()) {
                int patientId = set.getInt(userIdFieldName);
                int SBP = set.getInt(sbpFieldName);
                int DBP = set.getInt(dbpFieldName);
                LocalDate date = set.getDate(dateFieldName).toLocalDate();

                BloodPressureData pressureData = new BloodPressureData(patientId, SBP, DBP, date);
                pressureDataList.add(pressureData);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        return pressureDataList.toArray(new BloodPressureData[0]);
    }

    public static BloodPressureData[] getAllByPatient(int patientId) {
        String query = "SELECT * FROM bloodpressure WHERE user_id = " + patientId;
        ResultSet set = DatabaseManager.getItem(query);

        return loadBloodData(set);
    }

}
