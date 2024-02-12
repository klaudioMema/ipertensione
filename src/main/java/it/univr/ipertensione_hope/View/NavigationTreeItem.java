package it.univr.ipertensione_hope.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/*
* Rappresenta il nodo di una scena all'interno dell'albero di navigazione
* questo nodo contiene la scena stessa e il path al file FXML della scena
* il path serve ad identificare la scena.
*
* */

public class NavigationTreeItem {
    private String fxmlPath;
    private Scene scene;
    private FXMLLoader loader;

    public NavigationTreeItem(Scene scene, String fxmlPath, FXMLLoader loader) {
        this.fxmlPath = fxmlPath;
        this.scene = scene;
        this.loader = loader;
    }

    public Scene getScene() {
        return this.scene;
    }

    public String getFxmlPath() {
        return this.fxmlPath;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setFxmlPath(String path) {
        this.fxmlPath = path;
    }
    
    public void setLoader(FXMLLoader loader) {
    	this.loader = loader;
    }
    
    public FXMLLoader getLoader() {
    	return this.loader;
    }

}
