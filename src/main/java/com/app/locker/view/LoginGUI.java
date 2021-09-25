package com.app.locker.view;

import javafx.application.Application;
import javafx.stage.Stage;

public class LoginGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Log In");
        primaryStage.show();
    }
}
