package com.app.locker;

import com.app.locker.controller.windows.LoginGUIController;
import com.app.locker.controller.windows.SignupGUIController;
import com.app.locker.utils.classes.DBConnector;
import com.app.locker.utils.interfaces.WindowEventListener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application implements WindowEventListener {

    private static final int LOAD_SIGNUP_WINDOW = 1;
    private static final int LOAD_LOGIN_WINDOW = 2;

    private static int loadWindow; // Flag to check the screen to be loaded

    private static Stage currentStage; // Stage currently enabled and under operation

    @Override
    public void start(Stage primaryStage) throws Exception {
        DBConnector dbConnector = new DBConnector();
        // Tries to connect to the existing derby database. If it exists, prompts for login.
        // Else, creates a new database and asks the user for a new signup.
        // For any corruption in the db, the app doesnt work.
        try{
            dbConnector.setConnectionWithoutCreate();
            loadWindow = LOAD_LOGIN_WINDOW;
        }catch (Exception e){
            dbConnector.setConnectionWithCreate();
            loadWindow = LOAD_SIGNUP_WINDOW;
        }

        switch (loadWindow){
            case LOAD_LOGIN_WINDOW -> loadLogin();
            case LOAD_SIGNUP_WINDOW -> loadSignup();
        }
    }

    // Returns a stage with the SignupGUI.fxml file loaded up
    private Stage getSignupStage() {
        Stage stage = new Stage();
        Parent root = null;
        try{
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/layouts/windows/SignupGUI.fxml")));
        }catch (IOException e){
            System.out.println("Resource missing: SignupGUI.fxml");
            System.exit(1);
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Create your space");
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            // The sole purpose of this code is to delete the
            // database that got created so that the user gets prompted back again to
            // create a login when the app runs the next time.
            deleteDatabase(new File("database/"));
        });
        return stage;
    }

    // Loads up the signup window
    private void loadSignup(){
        currentStage = getSignupStage();
        currentStage.show();
        SignupGUIController.addWindowEventListener(this);
    }

    // Returns a stage with the LoginGUI.fxml file loaded up
    private Stage getLoginStage(){
        Stage stage = new Stage();
        Parent root = null;
        try{
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/layouts/windows/LoginGUI.fxml")));
        }catch (IOException e){
            System.out.println("Resource missing: LoginGUI.fxml");
            System.exit(1);
        }
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Log In");
        return stage;
    }

    // Loads up the login window
    private void loadLogin(){
        currentStage = getLoginStage();
        currentStage.show();
        LoginGUIController.addWindowEventListener(this);
    }

    private Stage getPasswordTableStage(){
        Stage stage = new Stage();
        Parent root = null;
        try{
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/layouts/windows/PasswordGUI.fxml")));
        }catch (IOException e){
            System.out.println("Resource missing: PasswordGUI.fxml");
            System.exit(1);
        }
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Log In");
        stage.setOnCloseRequest(event -> {
            System.exit(1);
        });
        return stage;
    }

    // Loads up the login window
    private void loadPasswordTable(){
        currentStage.close();
        currentStage = getPasswordTableStage();
        currentStage.show();
    }

    // Deletes the local database
    private static void deleteDatabase(File file){
        for (File subFile : file.listFiles()){
            if (subFile.isDirectory())
                deleteDatabase(subFile);
            else
                subFile.delete();
        }
        file.delete();
    }

    // Gets called when an account has been created. This code loads the login window.
    @Override
    public void onSignupWindowClosed() {
        currentStage.close();
        loadLogin();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void onPasswordTableRequested() {
        loadPasswordTable();
    }

    @Override
    public void onDatabaseResetRequested() {
        System.exit(1);
    }
}
