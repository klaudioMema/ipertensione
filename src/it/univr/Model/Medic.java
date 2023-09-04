package it.univr.Model;

public class Medic extends User{



    private static Medic instance;
    private String surname;
    private int doctordId;

    // This should be private, in theory
    public Medic(){
    }

    public static Medic getInstance(){
        if (instance == null) {
            synchronized (User.class) {
                if (instance == null) {
                    instance = new Medic();
                }
            }
        }
        return instance;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getDoctordId(){
        return doctordId;
    }

    public void setDoctordId(int doctordId){
        this.doctordId = doctordId;
    }
}
