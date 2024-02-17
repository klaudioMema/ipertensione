package it.univr.ipertensione_hope.Controller.admin;

import it.univr.ipertensione_hope.Model.AgenteSanitario;

public class AdminAppData {
    private static AdminAppData instance;

    private AgenteSanitario loggedAgenteSanitario;
    private final String directory = "admin/"; // memorizza la directory dei file fxml per l'admin

    private AdminAppData() {
        // Costruttore privato per evitare l'istanziazione diretta
    }

    public static AdminAppData getInstance() {
        if (instance == null) {
            instance = new AdminAppData();
        }
        return instance;
    }

    public AgenteSanitario getLoggedAgenteSanitario() {
        return this.loggedAgenteSanitario;
    }

    public void setLoggedAgenteSanitario(AgenteSanitario admin) {
        this.loggedAgenteSanitario = admin;
    }
}
