package it.univr.ipertensione_hope;

import it.univr.ipertensione_hope.Model.DatabaseManager;
import it.univr.ipertensione_hope.Model.Prescrizione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

/*Classe che implementa l'incremento del campo assumption per tutte le prescrizioni.
* Assumption va incrementato di uno per ogni giorno che passa, se passano più giorni
* senza che il programma viene aperto, bisogna incrementarlo di tanti quanti giorni sono passati
* */

public class PrescriptionScheduler {

    private static final String tableName = "LastExecutionDate";
    private static final String fieldName = "lastExecutionDate";
    private static final int id = 1;

    // Metodo per eseguire il processo di aggiornamento per i giorni mancanti
    public static void updateAssumption() {
        // Ottieni la data dell'ultima esecuzione della funzione di aggiornamento
        LocalDate lastExecutionDate = getLastExecutionDate();

        if(lastExecutionDate == null) {
            createExecutionDate();
        } else {
            // Calcola quanti giorni sono passati dall'ultima esecuzione
            LocalDate currentDate = LocalDate.now();
            int daysPassed = (int) ChronoUnit.DAYS.between(lastExecutionDate, currentDate);
            incrementAssumption(daysPassed);
            setLastExecutionDate(LocalDate.now()); // imposto il giorno di oggi come ultima esecuzione
        }
    }

    private static boolean createExecutionDate() {
        String query = "INSERT INTO " + tableName +
                "(" + fieldName +
                ") VALUES ('" + LocalDate.now() +
                "')";

        return DatabaseManager.updateItem(query);
    }

    // Metodi per il recupero e l'aggiornamento della data dell'ultima esecuzione nel database
    private static LocalDate getLastExecutionDate() {
        // Query per ottenere lastExecutionDate dal database
        String query = "SELECT " + fieldName +
                " FROM " + tableName +
                " WHERE id = " + id;

        // Esegui la query e ottieni il risultato
        ResultSet resultSet = DatabaseManager.getItem(query);

        try {
            if (resultSet.next()) {
                LocalDate date = resultSet.getDate("lastExecutionDate").toLocalDate();
                if(date != null)
                    return date;
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        return null; // Se non viene trovata una data nel database, restituisci null
    }

    private static boolean setLastExecutionDate(LocalDate date) {
        String query = "UPDATE " + tableName +
                   " SET " + fieldName + " = '" + date + "'" +
                   " WHERE id = " + id;

        return DatabaseManager.updateItem(query);
    }

    // Metodo per incrementare l'assunzione delle prescrizioni
    private static void incrementAssumption(int inc) {
        Prescrizione.updateAllAssumptionField(inc);
    }
}
