package com.app.locker.controller.fragments;

import com.app.locker.model.Entry;
import com.app.locker.utils.classes.core.AppProperties;
import com.app.locker.utils.classes.logic.View;
import com.app.locker.utils.classes.ui.animation.EntryViewAnimation;
import com.app.locker.utils.interfaces.EntryEventListener;
import com.app.locker.utils.interfaces.EventListener;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class EntryViewController extends View {

    private EntryEventListener entryEventListener;
    private Entry entry;

    @FXML private ImageView imgService;
    @FXML private ImageView imgDate;
    @FXML private ImageView imgPerson;
    @FXML private ImageView imgEmail;
    @FXML private ImageView imgPassword;

    @FXML private Label txtService;
    @FXML private Label txtCreatedOn;
    @FXML private Label txtUsername;
    @FXML private Label txtEmail;
    @FXML private Label txtPassword;

    @FXML private CheckBox checkBoxSelect;

    public EntryViewController(EventListener eventListener, Entry entry) {
        super(eventListener);
        this.entry = entry;
        entryEventListener = (EntryEventListener) eventListener;
        txtService.setText(entry.getService());
        txtCreatedOn.setText(entry.getCreated());
        txtUsername.setText(entry.getUsername());
        txtEmail.setText(entry.getEmail());
        txtPassword.setText(entry.getPassword());
    }

    @Override
    public void initializeView() {
        new EntryViewAnimation(getView());
        imgService.setImage(getImageCache().getServiceIconColoured());
        imgDate.setImage(getImageCache().getCreatedOnIconColoured());
        imgPerson.setImage(getImageCache().getPersonIconColoured());
        imgEmail.setImage(getImageCache().getEmailIconColoured());
        imgPassword.setImage(getImageCache().getPasswordIconColoured());
        checkBoxSelect.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue){
                entryEventListener.onEntrySelected(this);
            }else
                entryEventListener.onEntryDeselected(this);
        }));
    }

    public Entry getEntry(){
        return entry;
    }

    public void setEntry(Entry entry){
        this.entry.updateEntry(entry);
        txtUsername.setText(entry.getUsername());
        txtEmail.setText(entry.getEmail());
        txtPassword.setText(entry.getPassword());
        checkBoxSelect.setSelected(false);
    }

    @FXML
    public void onViewSelected(){
        checkBoxSelect.setSelected(!checkBoxSelect.isSelected());
    }

    @Override
    public String getResourcePath() {
        return AppProperties.PATH_ENTRY_LAYOUT;
    }
}
