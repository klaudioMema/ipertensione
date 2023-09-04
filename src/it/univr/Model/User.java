package it.univr.Model;

public class User {

    private String name;
    private  String cognome;
    private String email;
    private String password;
    private static  User ActiveUser = null;



    public User(String name,String cognome,String email,String password){
        this.name = name;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public void  setName(String name) {
        this.name = name;
    }

    public String getCognome() {
        return cognome;
    }
    public void setCognome(String email) {
        this.cognome = cognome;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public static User getActiveUser(User ActiveUser){
        return ActiveUser;
    }
    public static  void  setActiveUser(User ActiveUser){
            User.ActiveUser = ActiveUser;
    }
    //funzione in ingresso la email, tipo di utente, va a cerarlo
    // nel database e carica l'oggetto se riesce a trovarlo ok senno un null



}
