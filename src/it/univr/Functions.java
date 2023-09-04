package it.univr;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Functions {

    public static void notificationMessage(String msg, String type, Label status){
        if(type.equals("ERROR")){
            status.setTextFill(Color.rgb(255, 0, 0));
        } else if(type.equals("CONFIRM")){
            status.setTextFill(Color.rgb(0, 255, 0));
        }

        status.setText(msg);
        status.setVisible(true);
    }

}
