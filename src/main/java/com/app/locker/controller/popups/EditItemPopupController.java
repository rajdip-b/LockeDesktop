package com.app.locker.controller.popups;

import com.app.locker.controller.windows.PasswordGUIController;
import com.app.locker.model.Entry;
import com.app.locker.utils.classes.ui.animation.ButtonHoverAnimation;
import com.app.locker.utils.classes.ui.animation.TextFieldAnimation;
import com.app.locker.utils.interfaces.TableEventListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class EditItemPopupController {

    @FXML TextField txtService;
    @FXML TextField txtUsername;
    @FXML TextField txtPassword;
    @FXML TextField txtEmail;
    @FXML TextField txtCreated;
    @FXML Button btnApply;

    private String existingUsername;
    private String existingPassword;
    private String existingEmail;
    private static Entry entry;

    private static TableEventListener tableEventListener;

    public static void addTableEventListener(TableEventListener tableEventListener){
        EditItemPopupController.tableEventListener = tableEventListener;
    }

    @FXML
    public void initialize(){
        entry = PasswordGUIController.currentSelectedEntry;
        new TextFieldAnimation(txtService);
        new TextFieldAnimation(txtUsername);
        new TextFieldAnimation(txtPassword);
        new TextFieldAnimation(txtEmail);
        new TextFieldAnimation(txtCreated);
        new ButtonHoverAnimation(btnApply);
        existingPassword = entry.getPassword();
        existingEmail = entry.getEmail();
        existingUsername = entry.getUsername();
        txtService.setText(entry.getService());
        txtCreated.setText(entry.getCreated());
        txtEmail.setText(entry.getEmail());
        txtPassword.setText(entry.getPassword());
        txtUsername.setText(entry.getUsername());
        txtCreated.setDisable(true);
        txtService.setDisable(true);
    }

    @FXML
    public void onApplyClicked(){
        String newUsername = txtUsername.getText().trim();
        String newPassword = txtPassword.getText().trim();
        String newEmail = txtEmail.getText().trim();
        if (newEmail.equals("")){
            new Alert(Alert.AlertType.ERROR, "Email can't be empty!").showAndWait();
            return;
        }
        if (newPassword.equals("")){
            new Alert(Alert.AlertType.ERROR, "Password can't be empty!").showAndWait();
            return;
        }
        if (newUsername.equals(""))
            newUsername = "None";
        Entry oldEntry = entry;
        entry.setEmail(newEmail);
        entry.setPassword(newPassword);
        entry.setUsername(newUsername);
        if (!newEmail.equals(existingEmail) || !newPassword.equals(existingPassword) || !newUsername.equals(existingUsername))
            tableEventListener.onItemEdited(oldEntry, entry);
    }

    @FXML
    public void onEscapePressed(KeyEvent keyEvent){
        if (keyEvent.getCode() == KeyCode.ESCAPE)
            tableEventListener.onPopupCloseRequested();
    }

    @FXML
    public void onEnterClicked(KeyEvent keyEvent){
        if (keyEvent.getCode() == KeyCode.ENTER)
            onApplyClicked();
    }

}
