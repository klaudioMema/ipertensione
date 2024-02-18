package it.univr.ipertensione_hope.Model;


import javafx.beans.binding.BooleanExpression;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.property.*;

public class Sintomo {

    private int id;
    private String descrizione;
    private String tipologia;
    private int gravita;

    private static final String tableName = "Sintomi";
    private static final String idField = "id";
    private static final String descrizioneField = "descrizione";
    private static final String tipologiaField = "tipologia";
    private static final String gravitaField = "gravita";

    public Sintomo(String descrizione, String tipologia, int gravita) {
        this.descrizione = descrizione;
        this.tipologia = tipologia;
        this.gravita = gravita;
    }

    public Sintomo(int id, String descrizione, String tipologia, int gravita) {
        this(descrizione, tipologia, gravita);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public int getGravita() {
        return gravita;
    }

    public void setGravita(int gravita) {
        this.gravita = gravita;
    }

    public boolean add() {
        String query = "INSERT INTO " + tableName + " (" + descrizioneField + ", " + tipologiaField + ", " + gravitaField + ") " +
                       "VALUES ('" + descrizione + "', '" + tipologia + "', " + gravita + ")";

        return DatabaseManager.updateItem(query);
    }

    // recupera l'id del sintomo che è stato appena inserito tramite add()
    public int getIdFromDB() {
        String query = "SELECT LAST_INSERT_ID() AS last_id";
        ResultSet resultSet = DatabaseManager.getItem(query);
        int lastInsertedId = -1;

        try {
            if (resultSet.next()) {
                lastInsertedId = resultSet.getInt("last_id");
            }
            return lastInsertedId;
        } catch (SQLException e) {
            return -1;
        }
    }

    public static Sintomo getById(int id) {
        String query = "SELECT * FROM " + tableName + " WHERE " + idField + " = " + id;

        ResultSet  set = DatabaseManager.getItem(query);

        try {
            if (set.next()) {
                String descrizione = set.getString(descrizioneField);
                String tipologia = set.getString(tipologiaField);
                int gravita = set.getInt(gravitaField);

                return new Sintomo(id, descrizione, tipologia, gravita);
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    // ritorna tutti i sintomi di un certo paziente
    public static Sintomo[] getAllByPatient(int patientId) {
        ArrayList<Sintomo> sintomi = new ArrayList<>();

        // prima bisogna recuperare tutti i dati di pressione del paziente
        BloodPressureData[] data = BloodPressureData.getAllByPatient(patientId);

        // per ognuno di essi recuperiamo il sintomo associato
        for(BloodPressureData pressureData : data) {
            sintomi.add(getById(pressureData.getSintomoId()));
        }

        return sintomi.toArray(new Sintomo[0]);
    }

    public StringProperty descrizioneProperty() {
        return new SimpleStringProperty(descrizione);
    }

    public StringProperty tipologiaProperty() {
        return new SimpleStringProperty(tipologia);
    }

    public IntegerProperty idProperty() {
        return new SimpleIntegerProperty(id);
    }

    public IntegerProperty gravitaProperty() {
        return new SimpleIntegerProperty(gravita);
    }
}
