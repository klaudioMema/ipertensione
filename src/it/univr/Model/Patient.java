package it.univr.Model;


import java.sql.Date;

public class Patient extends User{

    private int patientId;
    private String surname;
    private String codiceF;
    private Date bDay;
    private String fattoriDiRischio;
    private int doctorId;

    private static Patient instance;

    private Patient(){}

    public static Patient getInstance(){
        if (instance == null) {
            synchronized (User.class) {
                if (instance == null) {
                    instance = new Patient();
                }
            }
        }
        return instance;
    }

    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
