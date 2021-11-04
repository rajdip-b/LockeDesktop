package com.app.locker.controller.windows;

import com.app.locker.utils.classes.core.ObjectHolder;
import com.app.locker.utils.classes.logic.DBConnector;
import com.app.locker.utils.classes.ui.animation.ButtonHoverAnimation;
import com.app.locker.utils.classes.ui.animation.TextFieldAnimation;
import com.app.locker.utils.interfaces.WindowEventListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.sql.SQLException;

import static com.app.locker.utils.classes.logic.Hash.toSHA256;

public class SignupGUIController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Button btnStart;
    @FXML private ImageView imageView;

    private static WindowEventListener windowEventListener;
    private DBConnector dbConnector;

    public static void addWindowEventListener(WindowEventListener windowEventListener){
        SignupGUIController.windowEventListener = windowEventListener;
    }

    @FXML
    public void initialize(){
        dbConnector = new DBConnector();
        imageView.setImage(ObjectHolder.getImageCache().getSignupBg());
        new TextFieldAnimation(txtPassword);
        new TextFieldAnimation(txtUsername);
        new ButtonHoverAnimation(btnStart);
        try {
            dbConnector.setConnectionWithCreate();
        }catch (SQLException ignored){
        }
    }

    @FXML
    public void onKeyPressed(KeyEvent event){
        if (event.getCode() == KeyCode.ENTER){
            onStartClicked();
        }
    }

    @FXML
    public void onStartClicked(){
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        if (username.length() > 0 && password.length() > 0){
            password = toSHA256(password);
            try{
                dbConnector.createNewUser(username, password);
                dbConnector.connection.close();
                new Alert(Alert.AlertType.INFORMATION, "Successfully created your account!").showAndWait();
                windowEventListener.onSignupWindowClosed(); // Invoke the listener in Main.java to handle the progress
            }catch (SQLException e){
                new Alert(Alert.AlertType.ERROR, "Error in the database! Please restart the app.").show();
            }
        }
    }

}
