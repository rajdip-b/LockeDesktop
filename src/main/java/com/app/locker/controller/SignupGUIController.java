package com.app.locker.controller;

import com.app.locker.utils.classes.DBConnector;
import com.app.locker.utils.interfaces.WindowEventListener;
import com.app.locker.view.LoginGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;

import static com.app.locker.utils.classes.Hash.toSHA256;

public class SignupGUIController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private ImageView imageView;

    private static WindowEventListener windowEventListener;

    public static void addWindowEventListener(WindowEventListener windowEventListener){
        SignupGUIController.windowEventListener = windowEventListener;
    }

    @FXML
    public void initialize(){
        Image image = new Image(String.valueOf(getClass().getResource("/images/img1.jpg")));
        imageView.setImage(image);
        imageView.setCache(true);
    }

    @FXML
    public void onStartClicked(){
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        if (username.length() > 0 && password.length() > 0){
            password = toSHA256(password);
            try{
                DBConnector.createNewUser(username, password);
                new Alert(Alert.AlertType.INFORMATION, "Successfully created your account!").show();
                windowEventListener.onSignupWindowClosed(); // Invoke the listener in Main.java to handle the progress
            }catch (SQLException e){
                new Alert(Alert.AlertType.ERROR, "Error in the database! Please restart the app.").show();
            }
        }
    }

    private static void launchLoginGUI(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                javafx.application.Application.launch(LoginGUI.class);
            }
        }.start();
    }

}
