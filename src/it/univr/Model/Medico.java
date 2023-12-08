package it.univr.Model;

public class Medico extends User{

    private int doctorId;

    // This should be private, in theory
    public Medico(String name, String cognome, String email, String password, int doctorId){
        super(name,cognome,email,password);
        this.doctorId = doctorId;
    }

    public int getDoctorId(){
        return doctorId;
    }

    public void setDoctorId(int doctorId){
        this.doctorId = doctorId;
    }
}
