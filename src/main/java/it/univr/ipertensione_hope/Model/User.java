package it.univr.ipertensione_hope.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class User {

    private String nome;
    private String cognome;
    private String email;
    private String password;
    private StringProperty mail;

    public User(String nome, String cognome, String email, String password){
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.mail = new SimpleStringProperty(email);
    }

    public User() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public StringProperty getMailProperty() {
    	return mail;
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

}
