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

        // ho fissato la finestra cosi non si può rimpicciolire
        mainStage.setMinHeight(409);
        mainStage.setMinWidth(638);

    }

    public static Stage getMainStage() {
        return mainStage;
    }
}
