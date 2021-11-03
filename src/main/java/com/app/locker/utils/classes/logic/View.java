package com.app.locker.utils.classes.logic;

import com.app.locker.utils.classes.core.ObjectHolder;
import com.app.locker.utils.interfaces.EventListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public abstract class View extends ObjectHolder {
    private Parent parent;
    private Node node;

    public View(EventListener eventListener){
        parent = null;
        node = null;
        FXMLLoader fxmlLoader =  new FXMLLoader((Objects.requireNonNull(getClass().getResource(getResourcePath()))));
        fxmlLoader.setController(this);
        try {
            parent = fxmlLoader.load();
            node = new AnchorPane(parent);
            initializeView();
            fxmlLoader.setRoot(this);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public abstract void initializeView(); // All the assignments/initialization of the fxml file is done here

    public Node getView(){
        return node;
    }

    public Parent getParent(){
        return parent;
    }

    public Node findNodeById(String id){
        return parent.lookup(id);
    }

    public abstract String getResourcePath();

}
