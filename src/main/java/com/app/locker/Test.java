package com.app.locker;

import com.app.locker.controller.PasswordGUIController;
import com.app.locker.utils.classes.core.ImageCache;
import com.app.locker.utils.classes.core.ObjectHolder;
import com.app.locker.utils.classes.UIManager;
import com.app.locker.utils.classes.logic.DBConnector;
import com.app.locker.utils.interfaces.EventListener;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application implements EventListener {

    private Stage currentStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        ObjectHolder.init();
        currentStage = new UIManager().getPasswordTableStage();
        currentStage.show();
//        PasswordGUIController passwordGUIController = new PasswordGUIController(this);
//        Stage stage = new Stage();
//        stage.setScene(new Scene(passwordGUIController.getParent()));
//        stage.setResizable(false);
//        stage.setTitle("Locker");
//        stage.setOnCloseRequest(event -> {
//            System.exit(1);
//        });

    }

}
