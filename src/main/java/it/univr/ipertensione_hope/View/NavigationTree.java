package it.univr.ipertensione_hope.View;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;

/*
* Classe che gestisce la navigazione tra scene
* Ogni scena è rappresentata come un nodo di un albero
* così è più facile tornare nella scena precedente e
* navigare tra le scene che si alternano nei menù.
* Le scene dello stesso menù sono rappresentati
* nell'albero come siblings.
* */

public class NavigationTree {
    private TreeItem<NavigationTreeItem> root;     // radice dell'albero
    private TreeItem<NavigationTreeItem> currentScene;   // scena corrente dell'albero
    //private int currentSceneIndex;                       // index dentro la lista dei siblings

    public NavigationTree(TreeItem<NavigationTreeItem> root) {
        this.root = root;
        currentScene = root;
    }

    public TreeItem<NavigationTreeItem> getRoot() {
        return this.root;
    }

    public void setRoot(Scene scene, String FXMLPath, FXMLLoader loader) {
        this.root = new TreeItem<>(new NavigationTreeItem(scene, FXMLPath, loader));
    }

    // ricerca il nodo della lista di siblings che corrisponde al path
    private TreeItem<NavigationTreeItem> getSceneByFXMLPath(ObservableList<TreeItem<NavigationTreeItem>> list, String path) {
        for (TreeItem<NavigationTreeItem> sceneItem : list) {
            if(sceneItem.getValue().getFxmlPath().equals(path))
                return sceneItem;
        }
        return null;
    }

    // cambia scena rispetto ai siblings
    // cioè si sposta verso una scena facente parte della lista di sibling corrente
    public boolean switchToSiblingScene(String path) {
        TreeItem<NavigationTreeItem> parentScene = currentScene.getParent();
        TreeItem<NavigationTreeItem> newScene = getSceneByFXMLPath(parentScene.getChildren(), path);

        if(newScene == null) {
            return false;
        } else {
            currentScene = newScene;
            return true;
        }

    }

    // cambia scena in una di un livello inferiore
    public boolean SwitchToNextScene(String path) {
        TreeItem<NavigationTreeItem> newScene = getSceneByFXMLPath(currentScene.getChildren(), path);

        if(newScene == null) {
            return false;
        } else {
            currentScene = newScene;
            return true;
        }
    }

    public void switchSiblingScene(Scene scene, String path, FXMLLoader loader) {
        TreeItem<NavigationTreeItem> parentScene = currentScene.getParent();
        TreeItem<NavigationTreeItem> nextScene = getSceneByFXMLPath(parentScene.getChildren(), path); // scena verso la quale spostarsi

        if(nextScene != null) {             // se la scena è già esistente nell'albero ci spostiamo semplicemente
            currentScene = nextScene;
        } else {                            // altrimenti ne creiamo una nuova e la inseriamo nell'albero
            TreeItem<NavigationTreeItem> newNodeScene = new TreeItem<>(new NavigationTreeItem(scene, path, loader));
            parentScene.getChildren().add(newNodeScene);
            currentScene = newNodeScene;
        }
    }

    public void newNextScene(Scene scene, String path, FXMLLoader loader) {
        TreeItem<NavigationTreeItem> newNodeScene = new TreeItem<>(new NavigationTreeItem(scene, path, loader));

        currentScene.getChildren().add(newNodeScene);
        currentScene = newNodeScene;
    }

    // ritorna alla scena precedente e rimuove tutti i figli
    public void previousScene() {
        currentScene = currentScene.getParent();
        currentScene.getChildren().remove(0, currentScene.getChildren().size() - 1);
    }
    

    
    public NavigationTreeItem getCurrentScene() {
    	return currentScene.getValue();
    }
    
    // per ottenere la scena al livello superiore
    public NavigationTreeItem getPreviousScene() {
    	return currentScene.getParent().getValue();
    }
}
