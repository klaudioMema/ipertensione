package it.univr.ipertensione_hope.Controller.doctor;

import it.univr.ipertensione_hope.Model.Medico;
import it.univr.ipertensione_hope.Model.Paziente;

public class DoctorAppData {
    private static DoctorAppData instance;
    private Paziente selectedPatient;
    private Medico medicoLoggato;

    private DoctorAppData() {
        // Costruttore privato per impedire l'istanziazione diretta
    }

    public static DoctorAppData getInstance() {
        if (instance == null) {
            instance = new DoctorAppData();
        }
        return instance;
    }

    public Paziente getSelectedPatient() {
        return selectedPatient;
    }

    public void setSelectedPatient(Paziente selectedPatient) {
        this.selectedPatient = selectedPatient;
    }

    public void setMedicoLoggato(Medico medico) {
        this.medicoLoggato = medico;
    }

    public Medico getMedicoLoggato() {
        return this.medicoLoggato;
    }
}