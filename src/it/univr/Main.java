package it.univr;

import it.univr.Controller.WindowsManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        URL location = getClass().getResource("View/LoginPageView.fxml");

        if(location == null) {
            System.out.println("Impossibile caricare la pagina di login");
        } else {
            Parent root = FXMLLoader.load(location);
            WindowsManager.setMainStage(stage, "BloodMonitor");
            WindowsManager.setScene(new Scene(root));
            WindowsManager.getMainStage().show();
        }
    }
}
