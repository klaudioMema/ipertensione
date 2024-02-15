package it.univr.ipertensione_hope.Controller.patient;

import it.univr.ipertensione_hope.Model.Paziente;

public class PatientAppData {
    private static PatientAppData instance;
    private Paziente loggedPatient;

    private PatientAppData() {
        // Costruttore privato per evitare l'istanziazione diretta
    }

    public static PatientAppData getInstance() {
        if (instance == null) {
            instance = new PatientAppData();
        }
        return instance;
    }

    public Paziente getLoggedPatient() {
        return loggedPatient;
    }

    public void setLoggedPatient(Paziente patient) {
        loggedPatient = patient;
    }
}
