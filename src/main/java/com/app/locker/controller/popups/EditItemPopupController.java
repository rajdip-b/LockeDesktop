package com.app.locker.controller.popups;

import com.app.locker.controller.windows.PasswordGUIController;
import com.app.locker.model.Entry;
import com.app.locker.utils.interfaces.TableEventListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    @FXML VBox vBoxTxtFields;
    @FXML VBox vBoxLabels;

    @FXML ComboBox<String> comboBox;

    private String existingUsername;
    private String existingPassword;
    private String existingEmail;
    private int selectionIndex;

    private static TableEventListener tableEventListener;
    private ObservableList<String> services;
    private ArrayList<Entry> entries;

    public static void addTableEventListener(TableEventListener tableEventListener){
        EditItemPopupController.tableEventListener = tableEventListener;
    }

    @FXML
    public void initialize(){
        existingEmail = null;
        existingPassword = null;
        existingUsername = null;
        vBoxLabels.setDisable(true);
        vBoxTxtFields.setDisable(true);
        txtCreated.setDisable(true);
        txtService.setDisable(true);
        services = FXCollections.observableArrayList();
        entries = new ArrayList<>();
        for (Entry entry : PasswordGUIController.entries) {
            services.add(entry.getService());
            entries.add(entry);
        }
        comboBox.setItems(services);
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
        Entry entry = entries.get(selectionIndex);
        Entry oldEntry = entry;
        entry.setEmail(newEmail);
        entry.setPassword(newPassword);
        entry.setUsername(newUsername);
        if (!newEmail.equals(existingEmail) || !newPassword.equals(existingPassword) || !newUsername.equals(existingUsername))
            tableEventListener.onItemEdited(oldEntry, entry);
    }

    @FXML
    public void onItemSelected(){
        selectionIndex = comboBox.getSelectionModel().getSelectedIndex();
        Entry entry = entries.get(selectionIndex);
        existingPassword = entry.getPassword();
        existingEmail = entry.getEmail();
        existingUsername = entry.getUsername();
        txtService.setText(entry.getService());
        txtCreated.setText(entry.getCreated());
        txtEmail.setText(entry.getEmail());
        txtPassword.setText(entry.getPassword());
        txtUsername.setText(entry.getUsername());
        vBoxTxtFields.setDisable(false);
        vBoxLabels.setDisable(false);
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
