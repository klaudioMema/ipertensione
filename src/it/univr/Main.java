package it.univr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception{

        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("View/LoginPageView.fxml"));
        scene = new Scene(root);
        stage.setTitle("BloodMonitor");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    // meglio  consegnare a Febbraio :)
}
