package it.univr.ipertensione_hope.Model;


import java.sql.Date;

public class Prescrizione {

    private  int userId;
    private  String medication;
    private  String indications;
    private  int days;
    private Date fromDate;

    public Prescrizione(String medication, String indications, int days, Date fromDate){
        this.medication = medication;
        this.indications = indications;
        this.days = days;
        this.fromDate = fromDate;
    }


    public String getMedication(){
        return medication;
    }
    public void setMedication(String medication){
        this.medication = medication;
    }


    public String getIndications(){
        return indications;
    }
    public void setWhen(String indication) {
        this.indications = indication;
    }
    public Date getFromDate() {
        return fromDate;
    }
    public void setFromDate(Date fromDate){
        this.fromDate = fromDate;
    }


    public int getDays(){
        return days;
    }
    public void setDays(int days) {
        this.days = days;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Prescrizione other = (Prescrizione) obj;

        // Compare all instance variables for equality
        return medication.equals(other.medication) &&
                indications.equals(other.indications) &&
                days == other.days &&
                fromDate.equals(other.fromDate);
    }
}
