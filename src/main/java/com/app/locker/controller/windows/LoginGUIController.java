package com.app.locker.controller.windows;

import com.app.locker.utils.classes.ui.animation.ButtonHoverAnimation;
import com.app.locker.utils.classes.ui.animation.TextFieldAnimation;
import com.app.locker.utils.interfaces.WindowEventListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static com.app.locker.utils.classes.logic.Hash.toSHA256;

import java.io.File;
import java.sql.SQLException;

import static com.app.locker.utils.classes.core.ObjectHolder.*;

public class LoginGUIController {

    @FXML private PasswordField txtPassword;
    @FXML private Label lblWelcome;
    @FXML private Button btnOpen;
    @FXML private Button btnReset;

    private static WindowEventListener windowEventListener;

    @FXML
    public void initialize(){
        String username = null;
        new ButtonHoverAnimation(btnOpen);
        new ButtonHoverAnimation(btnReset);
        new TextFieldAnimation(txtPassword);
        try{
            getDbConnector().setConnectionWithoutCreate();
            username = getDbConnector().getName();
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
            dbPassword = getDbConnector().getPassword();
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database corrupted!").showAndWait();
            System.exit(1);
        }
        if (dbPassword.equals(password)){
            try {
                getDbConnector().connection.close();
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
            dbPassword = getDbConnector().getPassword();
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
