package com.app.locker.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SignupGUI extends Application {

    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/layouts/SignupGUI.fxml")));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Create your space");
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
        });
        stage = primaryStage;
    }

    public static void closeStage(){
        stage.close();
    }
}
