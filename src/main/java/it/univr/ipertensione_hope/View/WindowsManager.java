package it.univr.ipertensione_hope.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class WindowsManager {
    private static Stage mainStage = null;      // stage corrente
    private static Scene currentScene = null;   // scena corrente
    private static NavigationTree navigationTree = null;

    public static NavigationTree getNavigationTree() {
        return navigationTree;
    }
    public static Class<?> mainClass;

    // istanzia la finestra principale
    public static void setMainStage(Stage stage, String title) {
        mainStage = stage;
        stage.setTitle(title);
    }

    // istanzia l'albero di navigazione con la prima scena
    public static void setRootScene(Scene scene, String FXMLPath, FXMLLoader loader) {
        if(navigationTree == null) {
            navigationTree = new NavigationTree(new TreeItem<>(new NavigationTreeItem(scene, FXMLPath, loader)));
        }
    }

    public static void setScene(Scene newScene) {
        currentScene = newScene;
        mainStage.setScene(newScene);
        mainStage.show();

        // ho fissato la finestra cosi non si può rimpicciolire
        mainStage.setMinHeight(409);
        mainStage.setMinWidth(638);

    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static Scene getCurrentScene() { return currentScene; }

    // imposta alla scena corrente dell'albero di navigazione
    public static void showCurrentScene() {
        mainStage.setScene(navigationTree.getCurrentScene().getScene());
        mainStage.show();
    }
    
    // per ottenere la scena precedente, ovvero quella al livello superiore dell'albero
    public static NavigationTreeItem getPreviousPage() {
    	return getNavigationTree().getPreviousScene();
    }
    
    public static NavigationTreeItem getCurrentPage() {
    	return getNavigationTree().getCurrentScene();
    }

    // ritorna alla pagina precedente
    public static void previousPage() {
        getNavigationTree().previousScene();
        showCurrentScene();
    }

    // per ricaricare la pagina e visualizzare le eventuali modifiche
    public static void reloadPage() {
        try {
        	// ricarico il loader della pagina
        	URL url = getCurrentPage().getLoader().getLocation();
        	getCurrentPage().setLoader(new FXMLLoader(url));

        	// imposto una nuova scena nel nodo attuale
        	Parent root = getCurrentPage().getLoader().load();
        	getCurrentPage().setScene(new Scene(root));
            showCurrentScene();

        } catch(Exception e) {
        	System.out.print("Impossibile caricare questa pagina");
        }
    }

    // aggiunge una nuova pagina ad un livello inferiore dell'albero
    public static FXMLLoader nextPage(URL url, String location) {
        if(url == null) {
            System.out.println("Impossibile caricare la pagina: " + location);
            return null;
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();
                getNavigationTree().newNextScene(new Scene(root), location, loader);
                showCurrentScene();

                return loader;
            } catch(IOException e) {
                System.out.println("Impossibile caricare la pagina: " + location);
                return null;
            }
        }
    }

    public static void loadPage(URL url, String location) {
        if(url == null) {
            System.out.println("Impossibile caricare la pagina: " + location);
        } else {
            try {
            	FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();
                getNavigationTree().switchSiblingScene(new Scene(root), location, loader);
                showCurrentScene();
                //reloadPage();

            } catch(IOException e) {
                System.out.println("Impossibile caricare la pagina: " + location);
            }
        }
    }

    public static void logout(URL url, String location) {
        if(url == null) {
            System.out.println("Impossibile caricare la pagina: " + location);
        } else {
            try {
                Parent root = FXMLLoader.load(url);
                setScene(new Scene(root));
                getMainStage().show();
            } catch(IOException e) {
                System.out.println("Impossibile caricare la pagina: " + location);
            }
        }
    }
}
