package it.univr.ipertensione_hope.Model;

import java.sql.Date;

public class Alert {

    private int userId;
    // 0 -> High blood Pressure
    // 1 -> Medication alert
    private int type;
    private Date    data;

    // Blood Pressure alert
    public Alert(int userId, int type, Date data){
        this.userId = userId;
        this.type = type;
        this.data = data;

    }

    public int getUserId(){
        return this.userId;
    }

}
