package com.app.locker.controller;

import com.app.locker.utils.classes.DBConnector;
import com.app.locker.utils.interfaces.WindowEventListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static com.app.locker.utils.classes.Hash.toSHA256;

import java.io.File;

public class LoginGUIController {

    @FXML private PasswordField txtPassword;

    private static WindowEventListener windowEventListener;

    public static void addWindowEventListener(WindowEventListener windowEventListener){
        LoginGUIController.windowEventListener = windowEventListener;
    }

    @FXML
    public void onOpenClicked(){
        String password = txtPassword.getText().trim();
        password = toSHA256(password);
        String dbPassword = DBConnector.getPassword();
        if (dbPassword.equals(password)){
            windowEventListener.onPasswordTableRequested();
        }else {
            new Alert(Alert.AlertType.ERROR, "Incorrect password!").show();
        }
    }

    @FXML
    public void onResetClicked(){
        String password = txtPassword.getText().trim();
        password = toSHA256(password);
        String dbPassword = DBConnector.getPassword();
        if (dbPassword.equals(password)){
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to reset all the settings? This option is irreversible.", ButtonType.YES, ButtonType.NO);
            a.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES){
                    deleteDatabase(new File("database/"));
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
