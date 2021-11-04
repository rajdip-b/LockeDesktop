package com.app.locker.utils.classes;

import com.app.locker.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class UIManager {

    public Stage getAddItemPopupStage(){
        Parent root = null;
        try{
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/layouts/popups/AddItemPopup.fxml")));
        }catch (IOException e){
            System.out.println("Resource missing: AddItemPopup.fxml");
            e.printStackTrace();
            System.exit(1);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add item");
        stage.setResizable(false);
        return stage;
    }

    public Stage getPasswordTableStage(){
        Stage stage = new Stage();
        Parent root = null;
        try{
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/layouts/windows/PasswordGUI.fxml")));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Resource missing: PasswordGUI.fxml");
            System.exit(1);
        }
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Locker");
        stage.setOnCloseRequest(event -> {
            System.exit(1);
        });
        return stage;
    }

}
