package it.univr.ipertensione_hope.Model;

import java.sql.Date;

public class Sintomo {

    private int userId;
    private String name;
    private String description;
    private Date start;
    private Date end;


    public Sintomo(int userId, String name, String description, Date start, Date end){
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public int getUserId(){
        return userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
