package it.univr.ipertensione_hope.Controller.admin;

import it.univr.ipertensione_hope.Model.AgenteSanitario;


public class AdminViewDashboard {

    private AgenteSanitario admin = null;
    private final String location = "admin/";

    public AdminViewDashboard() {}
    public AdminViewDashboard(AgenteSanitario admin) {
        this.admin = admin;
    }
    public void setAdmin(AgenteSanitario admin) {
        this.admin = admin;
    }
    public AgenteSanitario getAdmin() {
        return this.admin;
    }
    public void displayName(String name){
        //usernameLabel.setText(name);
    }
}
