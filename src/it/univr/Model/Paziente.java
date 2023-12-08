package it.univr.Model;


import java.sql.Date;

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

}
