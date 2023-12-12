package it.univr.Controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class WindowsManager {
    private static Stage mainStage = null;

    public static void setMainStage(Stage stage, String title) {
        mainStage = stage;
        stage.setTitle(title);
    }

    public static void setScene(Scene newScene) {
        mainStage.setScene(newScene);
        mainStage.show();
    }

    public static Stage getMainStage() {
        return mainStage;
    }
}
