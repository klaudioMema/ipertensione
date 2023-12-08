package it.univr.Model;

public abstract class User {

    private String nome;
    private String cognome;
    private String email;
    private String password;

    public User(String nome, String cognome, String email, String password){
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }

    public User() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome(){
        return nome;
    }

    public void  setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome){
        this.cognome = cognome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //funzione in ingresso la email, tipo di utente, va a cerarlo
    // nel database e carica l'oggetto se riesce a trovarlo ok senno un null

    public abstract User findUserDB(String username, String password);

}
