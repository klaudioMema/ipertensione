package it.univr.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AgenteSanitario extends User{

    public AgenteSanitario(String name, String cognome, String email, String password){
        super(name,cognome,email,password);
    }

    public AgenteSanitario(){}

    public User findUserDB(String username, String password) {
        String tableName = "agentesanitario";
        String query = "SELECT * FROM " + tableName + " WHERE email = '" + username + "' AND password = '" + password + "'";

        try (ResultSet resultSet = DatabaseManager.getItem(query)) {
            resultSet.next();

            String name = resultSet.getString("nome");
            String cognome = resultSet.getString("cognome");
            String email = resultSet.getString("email");
            String psw = resultSet.getString("password");

            return new AgenteSanitario(name, cognome, email, psw);

        } catch(SQLException e) {
            return null;
        }

    }

}
