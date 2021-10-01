package com.app.locker.controller.popups;

import com.app.locker.model.Entry;
import com.app.locker.utils.interfaces.TableEventListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.Date;

public class AddItemPopupController {

    @FXML TextField txtService;
    @FXML TextField txtUsername;
    @FXML TextField txtPassword;
    @FXML TextField txtEmail;

    private static TableEventListener tableEventListener = null;

    public static void addTableEventListener(TableEventListener tableEventListener){
        AddItemPopupController.tableEventListener = tableEventListener;
    }

    @FXML
    public void onAddClicked(){
        String service = txtService.getText().trim();
        String password = txtPassword.getText().trim();
        String username = txtPassword.getText().trim();
        String email = txtEmail.getText().trim();
        if(service.equals("")){
            new Alert(Alert.AlertType.ERROR, "The service can't be empty!").showAndWait();
            return;
        }
        if(password.equals("")){
            new Alert(Alert.AlertType.ERROR, "Password can't be empty!").showAndWait();
            return;
        }
        if (username.equals(""))
            username = "None";
        if (email.equals(""))
            email = "None";
        String created = new Date().toString();
        Entry entry = new Entry(service, username, password, email, created);
        tableEventListener.onItemAdded(entry);
        txtEmail.setText("");
        txtPassword.setText("");
        txtService.setText("");
        txtUsername.setText("");
    }

}
