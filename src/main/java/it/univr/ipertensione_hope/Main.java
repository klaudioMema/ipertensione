package it.univr.ipertensione_hope;

import it.univr.ipertensione_hope.Model.DatabaseManager;
import it.univr.ipertensione_hope.View.NavigationTree;
import it.univr.ipertensione_hope.View.WindowsManager;

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
        WindowsManager.mainClass = getClass();

        String path = "LoginPageView.fxml";
        URL location = WindowsManager.mainClass.getResource(path);

        if(DatabaseManager.connect()) {
            System.out.println("Connesso al database");
        } else {
            System.out.println("Impossibile connettersi al database");
        }

       if(location == null) {
          System.out.println("Impossibile caricare la pagina di login");
       } else {
           // funzioni di routine
           PrescriptionScheduler.updateAssumption();

           FXMLLoader loader = new FXMLLoader(location);
           Parent root = loader.load();
           WindowsManager.setRootScene(new Scene(root), path, loader);
           NavigationTree tree = WindowsManager.getNavigationTree();
           WindowsManager.setMainStage(stage, "BloodMonitor");
           WindowsManager.getMainStage().setScene(tree.getCurrentScene().getScene());
           WindowsManager.getMainStage().show();
       }


    }
}
