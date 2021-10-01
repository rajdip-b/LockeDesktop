package com.app.locker.controller.popups;

import com.app.locker.controller.windows.PasswordGUIController;
import com.app.locker.model.Entry;
import com.app.locker.utils.interfaces.TableEventListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

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
        tableEventListener.onItemDeleted(entries.get(comboBox.getSelectionModel().getSelectedIndex()));
    }
}
