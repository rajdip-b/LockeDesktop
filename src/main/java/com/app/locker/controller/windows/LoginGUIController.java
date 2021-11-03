package com.app.locker.controller.windows;

import com.app.locker.utils.classes.logic.DBConnector;
import com.app.locker.utils.interfaces.WindowEventListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static com.app.locker.utils.classes.logic.Hash.toSHA256;

import java.io.File;
import java.sql.SQLException;

public class LoginGUIController {

    @FXML private PasswordField txtPassword;
    @FXML private Label lblWelcome;

    private DBConnector dbConnector;
    private static WindowEventListener windowEventListener;
    private String username;

    @FXML
    public void initialize(){
        username = null;
        dbConnector = new DBConnector();
        try{
            dbConnector.setConnectionWithoutCreate();
            username = dbConnector.getName();
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database corrupted!").showAndWait();
            System.exit(1);
        }
        lblWelcome.setText("Welcome "+ username + "!");
    }

    public static void addWindowEventListener(WindowEventListener windowEventListener){
        LoginGUIController.windowEventListener = windowEventListener;
    }

    @FXML
    public void onOpenClicked(){
        String password = txtPassword.getText().trim();
        password = toSHA256(password);
        String dbPassword = null;
        try{
            dbPassword = dbConnector.getPassword();
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database corrupted!").showAndWait();
            System.exit(1);
        }
        if (dbPassword.equals(password)){
            try {
                dbConnector.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            windowEventListener.onPasswordTableRequested();
        }else {
            new Alert(Alert.AlertType.ERROR, "Incorrect password!").show();
        }
    }

    @FXML
    public void onResetClicked(){
        String password = txtPassword.getText().trim();
        password = toSHA256(password);
        String dbPassword = null;
        try{
            dbPassword = dbConnector.getPassword();
        }catch (SQLException e){
            System.out.println("Error accessing database!");
            new Alert(Alert.AlertType.ERROR, "Database corrupted!").showAndWait();
            System.exit(1);
        }
        if (dbPassword.equals(password)){
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to reset all the settings? This option is irreversible.", ButtonType.YES, ButtonType.NO);
            a.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES){
                    windowEventListener.onDatabaseResetRequested();
                }
            });
        }else {
            new Alert(Alert.AlertType.ERROR, "Incorrect password!").show();
        }
    }

    @FXML
    public void onKeyPressed(KeyEvent event){
        if (event.getCode() == KeyCode.ENTER){
            onOpenClicked();
        }
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

}
