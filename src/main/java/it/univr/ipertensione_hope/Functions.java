package it.univr.ipertensione_hope;

import java.util.Optional;
import java.util.function.Consumer;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

public class Functions {

    public enum notificationType{
        ERROR,
        CONFIRM
    }

    // costruisce un dialog modale per inviare notifiche all'utente
    public static void alert(String message, Alert.AlertType alertType, Consumer<ButtonType> callback) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert.showAndWait();
        
        if(callback != null)
        	result.ifPresent(callback);
    }

}
