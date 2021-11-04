package com.app.locker.controller.windows;

import com.app.locker.controller.fragments.EntryViewController;
import com.app.locker.controller.fragments.SidebarCollapsedViewController;
import com.app.locker.controller.fragments.SidebarExpandedViewController;
import com.app.locker.controller.popups.AddItemPopupController;
import com.app.locker.controller.popups.EditItemPopupController;
import com.app.locker.model.Entry;
import com.app.locker.utils.classes.core.AppProperties;
import com.app.locker.utils.classes.logic.DBConnector;
import com.app.locker.utils.interfaces.EntryEventListener;
import com.app.locker.utils.interfaces.SidebarEventListener;
import com.app.locker.utils.interfaces.TableEventListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Objects;

import static com.app.locker.utils.classes.core.ObjectHolder.*;

public class PasswordGUIController implements SidebarEventListener, EntryEventListener, TableEventListener {

    public static Entry currentSelectedEntry;
    @FXML private Pane sidebarPane;
    @FXML private VBox mainViewPane;
    @FXML private VBox entryBox;

    private SidebarCollapsedViewController sidebarCollapsedViewController;
    private SidebarExpandedViewController sidebarExpandedViewController;

    private ObservableList<EntryViewController> entryViewsSelected;
    private ObservableList<Entry> entries;
    private ObservableList<EntryViewController> entryViews;
    private DBConnector dbConnector;

    private Stage currentStage;

    @FXML
    public void initialize() throws SQLException {
//        getDbConnector().setConnectionWithoutCreate();
        dbConnector = new DBConnector();
        dbConnector.setConnectionWithoutCreate();
        entries = FXCollections.observableArrayList();
        entryViewsSelected = FXCollections.observableArrayList();
        entryViews = FXCollections.observableArrayList();
        sidebarCollapsedViewController = new SidebarCollapsedViewController(this);
        sidebarExpandedViewController = new SidebarExpandedViewController(this);
        sidebarPane.getChildren().add(sidebarCollapsedViewController.getView());
        sidebarCollapsedViewController.enableButtonEdit(false);
        sidebarCollapsedViewController.enableButtonDelete(false);
        sidebarExpandedViewController.enableButtonDelete(false);
        sidebarExpandedViewController.enableButtonEdit(false);
        entryViewsSelected.addListener((ListChangeListener<EntryViewController>) c -> {
            int size = entryViewsSelected.size();
            if (size == 0){
                sidebarCollapsedViewController.enableButtonDelete(false);
                sidebarCollapsedViewController.enableButtonEdit(false);
                sidebarExpandedViewController.enableButtonDelete(false);
                sidebarExpandedViewController.enableButtonEdit(false);
            } else if (size == 1){
                sidebarCollapsedViewController.enableButtonDelete(true);
                sidebarCollapsedViewController.enableButtonEdit(true);
                sidebarExpandedViewController.enableButtonDelete(true);
                sidebarExpandedViewController.enableButtonEdit(true);
            } else {
                sidebarCollapsedViewController.enableButtonDelete(true);
                sidebarCollapsedViewController.enableButtonEdit(false);
                sidebarExpandedViewController.enableButtonDelete(true);
                sidebarExpandedViewController.enableButtonEdit(false);
            }
        });
        loadExistingEntries();
    }

    public void addEntryViewToDisplay(EntryViewController entryViewController){
        entryBox.getChildren().add(entryViewController.getView());
    }

    public void removeEntryViewFromDisplay(EntryViewController entryViewController){
        entryBox.getChildren().remove(entryViewController.getView());
    }

    @Override
    public void onCollapseSidebar() {
        mainViewPane.setPrefWidth(mainViewPane.getWidth()+165);
        sidebarPane.setPrefWidth(sidebarPane.getWidth()-165);
        sidebarPane.getChildren().clear();
        sidebarPane.getChildren().add(sidebarCollapsedViewController.getView());
    }

    @Override
    public void onExpandSidebar() {
        mainViewPane.setPrefWidth(mainViewPane.getWidth()-165);
        sidebarPane.setPrefWidth(sidebarPane.getWidth()+165);
        sidebarPane.getChildren().clear();
        sidebarPane.getChildren().add(sidebarExpandedViewController.getView());
    }

    @Override
    public void onAddClicked() {
        if (currentStage == null){
            Stage stage = getUiManager().getAddItemPopupStage();
            stage.setOnCloseRequest(event -> onPopupCloseRequested());
            currentStage = stage;
            stage.show();
            AddItemPopupController.addTableEventListener(this);
        }
    }
//
    @Override
    public void onEditClicked() {
        currentSelectedEntry = entryViewsSelected.get(0).getEntry();
        if (currentStage == null){
            Parent root = null;
            try{
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/layouts/popups/EditItemPopup.fxml")));
            }catch (IOException e){
                e.printStackTrace();
                System.out.println("Resource missing: EditItemPopup.fxml");
                System.exit(1);
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Edit item");
            stage.setResizable(false);
            stage.setOnCloseRequest(event -> {
                onPopupCloseRequested();
                currentSelectedEntry = null;
            });
            currentStage = stage;
            stage.show();
            EditItemPopupController.addTableEventListener(this);
        }
    }

    @Override
    public void onDeleteClicked() {
        new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected item(s)?", ButtonType.YES, ButtonType.NO).showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.YES){
                try{
                    for (EntryViewController entryViewController : entryViewsSelected) {
                        dbConnector.deleteEntry(entryViewController.getEntry());
                        entries.remove(entryViewController.getEntry());
                        entryViews.remove(entryViewController);
                        removeEntryViewFromDisplay(entryViewController);
                    }
                    entryViewsSelected.clear();
                } catch (SQLException e){
                    new Alert(Alert.AlertType.INFORMATION, "Database corrupted!").showAndWait();
                }
            }
        });
    }

    @Override
    public void onDeleteAllClicked() {
        new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete everything?", ButtonType.YES, ButtonType.NO).showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.YES){
                try{
                    dbConnector.deleteAllEntries();
                    entryBox.getChildren().clear();
                    entryViews.clear();
                    entries.clear();
                    entryViewsSelected.clear();
                } catch (SQLException e){
                    new Alert(Alert.AlertType.INFORMATION, "Database corrupted!").showAndWait();
                }
            }
        });
    }

    private void loadExistingEntries(){
        entryViews.clear();
        ArrayList<Entry> existingEntries = null;
        try {
            existingEntries = dbConnector.getExistingData();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error accessing/writing to database!");
            new Alert(Alert.AlertType.ERROR, "Database corrupted!").showAndWait();
            System.exit(1);
        }
        for (Entry entry : existingEntries){
            EntryViewController entryViewController = new EntryViewController(this, entry);
            entryViews.add(entryViewController);
            entries.add(entry);
            entryBox.getChildren().add(entryViewController.getView());
        }
    }

    @Override
    public void onGithubClicked() {
        new Thread(() -> {
            try {
                Desktop desktop = java.awt.Desktop.getDesktop();
                URI oURL = new URI(AppProperties.GITHUB_LINK);
                desktop.browse(oURL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void onEntrySelected(EntryViewController entryViewController) {
        entryViewsSelected.add(entryViewController);
    }

    @Override
    public void onEntryDeselected(EntryViewController entryViewController) {
        entryViewsSelected.remove(entryViewController);
    }

    @Override
    public void onItemAdded(Entry entry) {
        try{
            dbConnector.addData(entry);
            entries.add(entry);
            EntryViewController entryViewController = new EntryViewController(this, entry);
            entryViews.add(entryViewController);
            addEntryViewToDisplay(entryViewController);
            new Alert(Alert.AlertType.INFORMATION, "Changes applied successfully.").showAndWait();
        } catch (SQLIntegrityConstraintViolationException e){
            new Alert(Alert.AlertType.ERROR, "A similar service name already exists!").showAndWait();
        } catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, "Database corrupt!").showAndWait();
        }
    }

    @Override
    public void onItemEdited(Entry oldEntry, Entry newEntry) {
        try{
            dbConnector.updateEntry(newEntry);
            entryViewsSelected.clear();
            entries.set(entries.indexOf(oldEntry), newEntry);
            for (EntryViewController e : entryViews){
                if (e.getEntry().equals(oldEntry)){
                    e.setEntry(newEntry);
                    break;
                }
            }
            new Alert(Alert.AlertType.INFORMATION, "Changes applied successfully.").showAndWait();
        } catch (SQLException e){
            new Alert(Alert.AlertType.INFORMATION, "Database corrupted!").showAndWait();
        }
    }

    @Override
    public void onPopupCloseRequested() {
        currentStage.close();
        currentStage = null;
    }

}
