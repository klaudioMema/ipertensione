package it.univr.Model;

public class Medic extends User{

    private int doctordId;

    // This should be private, in theory
    public Medic(String name,String cognome,String email,String password,int doctordId){
        super(name,cognome,email,password);
        this.doctordId = doctordId;
    }

    public int getDoctordId(){
        return doctordId;
    }

    public void setDoctordId(int doctordId){
        this.doctordId = doctordId;
    }
}
