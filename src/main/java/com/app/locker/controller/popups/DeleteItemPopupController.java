package com.app.locker.controller.popups;

import com.app.locker.controller.windows.PasswordGUIController;
import com.app.locker.model.Entry;
import com.app.locker.utils.interfaces.TableEventListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class DeleteItemPopupController {

    @FXML ComboBox<String> comboBox;

    private static TableEventListener tableEventListener;
    private ObservableList<String> services;
    private ArrayList<Entry> entries;

    public static void addTableEventListener(TableEventListener tableEventListener) {
        DeleteItemPopupController.tableEventListener = tableEventListener;
    }

    @FXML
    private void initialize(){
        services = FXCollections.observableArrayList();
        entries = new ArrayList<>();
        for (Entry entry : PasswordGUIController.entries) {
            services.add(entry.getService());
            entries.add(entry);
        }
        comboBox.setItems(services);
    }

    @FXML
    public void onDeleteClicked(){
        int selectionIndex = comboBox.getSelectionModel().getSelectedIndex();
        try{
            services.remove(selectionIndex);
        }catch (ArrayIndexOutOfBoundsException ignored){
        }
        tableEventListener.onItemDeleted(entries.get(selectionIndex));
    }

    @FXML
    public void onEscapePressed(KeyEvent keyEvent){
        if (keyEvent.getCode() == KeyCode.ESCAPE)
            tableEventListener.onPopupCloseRequested();
    }

    @FXML
    public void onEnterClicked(KeyEvent keyEvent){
        if (keyEvent.getCode() == KeyCode.ENTER)
            onDeleteClicked();
    }
}
