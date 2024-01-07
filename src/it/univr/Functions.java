package it.univr;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Functions {

    public enum notificationType{
        ERROR,
        CONFIRM
    }

    public static void notificationMessage(String msg, notificationType type, Label status){
        if(type == notificationType.ERROR){
            status.setTextFill(Color.rgb(255, 0, 0));
        } else if(type == notificationType.CONFIRM){
            status.setTextFill(Color.rgb(0, 255, 0));
        }

        status.setText(msg);
        status.setVisible(true);
    }

}
