package it.univr.Controller;


import it.univr.Model.Medico;
import it.univr.Model.Paziente;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Search {

    // Search for a patient in the DB
    public boolean searchPatientDB(String name, String surname, String codiceF) {
        /*
        ResultSet rs;
        boolean exists = false;
        Paziente selectedUser = null;

        try {
            rs = DatabaseController.getResultSet("SELECT * FROM patients WHERE codicef = '" + codiceF +
                    "' OR (name =  '" + name +
                    "' AND surname = '" + surname + "')");

            if (rs.next()) {
                selectedUser = new Paziente();
                selectedUser.setPatientId(rs.getInt("user_id"));
                selectedUser.setEmail(rs.getString("email"));
                selectedUser.setPassword(rs.getString("password"));
                selectedUser.setName(rs.getString("name"));
                selectedUser.setCognome(rs.getString("surname"));
                selectedUser.setCodiceF(rs.getString("codicef"));
                selectedUser.setbDay(rs.getDate("bday"));
                selectedUser.setDoctorId(rs.getInt("doctor_id"));
                selectedUser.setFattoriDiRischio(rs.getString("fattoriDiRischio"));

                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return selectedUser != null;

         */

        return true;
    }

    // Search for a doctor in the DB
    public boolean searchDoctorDB(String name, String surname, String email) {
        /*
        ResultSet rs;
        boolean exists = false;
        Medico selectedUser = null;

        try {
            rs = DatabaseController.getResultSet("SELECT * FROM medics WHERE email = '" + email +
                    "' OR (name =  '" + name +
                    "' AND surname = '" + surname + "')");

            if (rs.next()) {
                selectedUser = Medico.getInstance();
                selectedUser.setDoctorId(rs.getInt("doctor_id"));
                selectedUser.setEmail(rs.getString("email"));
                selectedUser.setPassword(rs.getString("password"));
                selectedUser.setName(rs.getString("name"));
                selectedUser.setCognome(rs.getString("surname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return selectedUser != null;


         */

        return true;
    }




}

