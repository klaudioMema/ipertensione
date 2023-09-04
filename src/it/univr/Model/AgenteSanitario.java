package it.univr.Model;

public class AgenteSanitario extends User{

    private static AgenteSanitario instance;
    private AgenteSanitario(){
    }

    public static AgenteSanitario getInstance(){
        if (instance == null) {
            synchronized (User.class) {
                if (instance == null) {
                    instance = new AgenteSanitario();
                }
            }
        }
        return instance;
    }



}
