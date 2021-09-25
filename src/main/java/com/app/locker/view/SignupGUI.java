package com.app.locker.view;

import javafx.application.Application;
import javafx.stage.Stage;

public class SignupGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Create your safe");
        primaryStage.show();
    }
}
