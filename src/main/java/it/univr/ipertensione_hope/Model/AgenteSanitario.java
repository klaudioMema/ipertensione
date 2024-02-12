package it.univr.ipertensione_hope.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AgenteSanitario extends User{

    // informazioni sulla tabella del database
    private final static String tableName = "agentesanitario";
    private final static String nameField = "nome";
    private final static String surnameField = "cognome";
    private final static String emailField = "email";
    private final static String passwordField = "password";

    public AgenteSanitario(String name, String cognome, String email, String password){
        super(name,cognome,email,password);
    }

    public static User findUserDB(String username, String password) {
        String query = "SELECT * FROM " + tableName + " WHERE email = '" + username + "' AND password = '" + password + "'";

        try (ResultSet resultSet = DatabaseManager.getItem(query)) {
            resultSet.next();

            String name = resultSet.getString(nameField);
            String cognome = resultSet.getString(surnameField);
            String email = resultSet.getString(emailField);
            String psw = resultSet.getString(passwordField);

            return new AgenteSanitario(name, cognome, email, psw);

        } catch(SQLException e) {
            return null;
        }

    }

}
