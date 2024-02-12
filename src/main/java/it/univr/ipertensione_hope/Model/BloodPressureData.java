package it.univr.ipertensione_hope.Model;

import java.sql.Date;


public class BloodPressureData {

    private int userId;
    private String cat;
    private int SBP;
    private int DBP;
    private Date date;

    public BloodPressureData(int SBP, int DBP, Date date){
        this.SBP = SBP;
        this.DBP = DBP;
        this.date = date;
        setCat(SBP,DBP);
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getCat() {
        return cat;
    }

    public void setCat(int SBP, int DBP) {

        if(SBP >= 140 && SBP <= 149 && DBP < 90) {
            cat = "Hypertension Systolic Borderline";
        } else if(SBP >= 150 && DBP < 90) {
            cat = "Hypertension Systolic";
        } else if(SBP < 120 && DBP < 80) {
            cat = "Optimal";
        } else if (SBP >= 120 && SBP < 130 ||  DBP < 85) {
            if(DBP > 85 && DBP <= 89)
                cat = "Normal - High";
            else
                cat = "Normal";
        }else if (SBP >= 130 && SBP <= 139 || DBP <= 89) {
            if(DBP > 89 && DBP <= 94)
                cat = "Hypertension 1 Borderline";
            else
                cat = "Normal - High";
        }else if (SBP >= 140 && SBP <= 149 || DBP <= 94) {
            if(DBP > 94 && DBP <= 99)
                cat = "Hypertension 1 Light";
            else
                cat = "Hypertension 1 Borderline";
        }else if (SBP >= 150 && SBP <= 159 || DBP <= 99) {
            if(DBP > 99 && DBP <= 109)
                cat = "Hypertension 2 Moderate";
            else
                cat = "Hypertension 1 Light";
        }else if (SBP >= 160 && SBP <= 179 || DBP <= 109) {
            if(DBP > 109 && DBP <= 180)
                cat = "Hypertension 3 Critic!"; //
            else
                cat = "Hypertension 2 Moderate"; //
        }else if (SBP >= 180) {
            cat = "Hypertension 3 Critic!"; //
        }else {
            cat = "undefined";
        }
    }

    public int getSBP() {
        return SBP;
    }
    public void setSBP(int SBP) {
        this.SBP = SBP;
    }
    public int getDBP() {
        return DBP;
    }
    public void setDBP(int DBP) {
        this.DBP = DBP;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
