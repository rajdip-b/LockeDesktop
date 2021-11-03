package com.app.locker.controller;

import com.app.locker.controller.fragments.EntryViewController;
import com.app.locker.controller.fragments.SidebarCollapsedViewController;
import com.app.locker.controller.fragments.SidebarExpandedViewController;
import com.app.locker.controller.popups.AddItemPopupController;
import com.app.locker.controller.popups.DeleteItemPopupController;
import com.app.locker.controller.popups.EditItemPopupController;
import com.app.locker.model.Entry;
import com.app.locker.utils.classes.core.AppProperties;
import com.app.locker.utils.classes.core.ObjectHolder;
import com.app.locker.utils.classes.logic.View;
import com.app.locker.utils.interfaces.EntryEventListener;
import com.app.locker.utils.interfaces.EventListener;
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
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PasswordGUIController implements SidebarEventListener, EntryEventListener, TableEventListener {

    @FXML private Pane sidebarPane;
    @FXML private VBox mainViewPane;
    @FXML private VBox entryBox;

    private SidebarCollapsedViewController sidebarCollapsedViewController;
    private SidebarExpandedViewController sidebarExpandedViewController;

    private ObservableList<EntryViewController> entryViewsSelected;
    private ArrayList<EntryViewController> entryViews;

    private Stage currentStage;
//
//    public PasswordGUIController(EventListener eventListener) {
//        super(eventListener);
//    }

    @FXML
    public void initialize(){
        entryViewsSelected = FXCollections.observableArrayList();
        entryViews = new ArrayList<>();
        sidebarCollapsedViewController = new SidebarCollapsedViewController(this);
        sidebarExpandedViewController = new SidebarExpandedViewController(this);
        sidebarPane.getChildren().add(sidebarCollapsedViewController.getView());
        sidebarCollapsedViewController.enableButtonEdit(false);
        sidebarCollapsedViewController.enableButtonDelete(false);
        sidebarExpandedViewController.enableButtonDelete(false);
        sidebarExpandedViewController.enableButtonDelete(false);
        entryViewsSelected.addListener(new ListChangeListener<EntryViewController>() {
            @Override
            public void onChanged(Change<? extends EntryViewController> c) {
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
            }
        });
//        loadExistingEntries();
        entryBox.getChildren().add(new EntryViewController(this, new Entry("Google", "Rajdip Bhattacharya", "1234rajB", "rajdipbhattacharya41@gmail.com", new Date().toString())).getView());
        entryBox.getChildren().add(new EntryViewController(this, new Entry("Microsoft", "Rajdip Bhattacharya", "1234rajB47", "rajdipbhattacharya41@gmail.com", new Date().toString())).getView());
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
//        if (currentStage == null){
//            Parent root = null;
//            try{
//                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/layouts/popups/AddItemPopup.fxml")));
//            }catch (IOException e){
//                System.out.println("Resource missing: AddItemPopup.fxml");
//                e.printStackTrace();
//                System.exit(1);
//            }
//            AddItemPopupController addItemPopupController =  new AddItemPopupController(this);
//            Stage stage = new Stage();
//            stage.setScene(new Scene(addItemPopupController.getParent()));
//            stage.setTitle("Add item");
//            stage.setResizable(false);
//            stage.setOnCloseRequest(event -> {
//                onPopupCloseRequested();
//            });
//            currentStage = stage;
//            stage.show();
//            AddItemPopupController.addTableEventListener(this);
//        }
    }
//
    @Override
    public void onEditClicked() {
//        if (currentStage == null){
//            Parent root = null;
//            try{
//                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/layouts/popups/EditItemPopup.fxml")));
//            }catch (IOException e){
//                e.printStackTrace();
//                System.out.println("Resource missing: EditItemPopup.fxml");
//                System.exit(1);
//            }
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.setTitle("Edit item");
//            stage.setResizable(false);
//            stage.setOnCloseRequest(event -> {
//                onPopupCloseRequested();
//            });
//            currentStage = stage;
//            stage.show();
//            EditItemPopupController.addTableEventListener(this);
//        }
    }

    @Override
    public void onDeleteClicked() {
//        if (currentStage == null){
//            Parent root = null;
//            try{
//                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/layouts/popups/DeleteItemPopup.fxml")));
//            }catch (IOException e){
//                System.out.println("Resource missing: DeleteItemPopup.fxml");
//                System.exit(1);
//            }
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.setTitle("Delete item");
//            stage.setResizable(false);
//            stage.setOnCloseRequest(event -> {
//                onPopupCloseRequested();
//            });
//            currentStage = stage;
//            stage.show();
//            DeleteItemPopupController.addTableEventListener(this);
//        }
    }

    @Override
    public void onDeleteAllClicked() {
        if (currentStage == null){
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure? This can't be undone!", ButtonType.YES, ButtonType.CANCEL);
            a.showAndWait();
            if (a.getResult() == ButtonType.YES) {
                try {
                    ObjectHolder.getDbConnector().deleteAllEntries(); ////////////////////////////////////////////////////////
                    new Alert(Alert.AlertType.INFORMATION, "Changes applied successfully.").showAndWait();
                } catch (SQLException e) {
                    System.out.println("Error accessing/writing to database!");
                }
            }
            entryViews.clear();
        }
    }

    private void loadExistingEntries(){
        entryViews.clear();
        ArrayList<Entry> existingEntries = null;
        try {
            existingEntries = ObjectHolder.getDbConnector().getExistingData();
        } catch (SQLException e) {
            System.out.println("Error accessing/writing to database!");
            new Alert(Alert.AlertType.ERROR, "Database corrupted!").showAndWait();
            System.exit(1);
        }
//        if (existingEntries == null) {
//            new Alert(Alert.AlertType.ERROR, "Database corrupted!").showAndWait();
//            System.exit(1);
//            return;
//        }
        for (Entry entry : existingEntries){
            EntryViewController entryViewController = new EntryViewController(this, entry);
            entryViews.add(entryViewController);
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
            ObjectHolder.getDbConnector().addData(entry);
            EntryViewController entryViewController = new EntryViewController(this, entry);
            entryViews.add(entryViewController);
            addEntryViewToDisplay(entryViewController);
            new Alert(Alert.AlertType.INFORMATION, "Changes applied successfully.").showAndWait();
        }
        catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Duplicate field value detected!");
            new Alert(Alert.AlertType.ERROR, "An entry with this service name already exists!").showAndWait();
        }
        catch (SQLException e){
            System.out.println("Error accessing/writing to database");
            new Alert(Alert.AlertType.ERROR, "Database corrupted!").showAndWait();
            System.exit(1);
        }
    }

    @Override
    public void onItemEdited(Entry oldEntry, Entry newEntry) {
        try{
            ObjectHolder.getDbConnector().updateEntry(newEntry);
            EntryViewController entryView = null;
            for (EntryViewController entryViewController : entryViews)
                if (entryViewController.getEntry().equals(oldEntry)) {
                    entryView = entryViewController;
                    break;
                }
            EntryViewController temp = entryView;
            entryView.setEntry(newEntry);
            entryViews.set(entryViews.indexOf(temp), entryView);
            new Alert(Alert.AlertType.INFORMATION, "Changes applied successfully.").showAndWait();
        }catch (SQLException e){
            System.out.println("Error accessing/writing to database");
            new Alert(Alert.AlertType.ERROR, "Database corrupted!").showAndWait();
            System.exit(1);
        }
    }

    @Override
    public void onItemDeleted(Entry entry) {
        try{
            ObjectHolder.getDbConnector().deleteEntry(entry);
            EntryViewController controller = null;
            for (EntryViewController entryViewController : entryViews)
                if (entryViewController.getEntry().equals(entry)){
                    controller = entryViewController;
                    break;
                }
            removeEntryViewFromDisplay(controller);
            entryViews.remove(controller);
            new Alert(Alert.AlertType.INFORMATION, "Changes applied successfully.").showAndWait();
        }catch (SQLException e){
            System.out.println("Error accessing/writing to database!");
            new Alert(Alert.AlertType.ERROR, "Database corrupted!").showAndWait();
            System.exit(1);
        }
    }

    @Override
    public void onPopupCloseRequested() {
        currentStage.close();
        currentStage = null;
    }

//    @Override
//    public void initializeView() {
//
//    }

//    @Override
//    public String getResourcePath() {
//        return AppProperties.PATH_PASSWORD_GUI;
//    }
}
