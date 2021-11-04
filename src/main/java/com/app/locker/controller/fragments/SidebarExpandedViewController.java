package com.app.locker.controller.fragments;

import com.app.locker.utils.classes.ui.animation.TextFieldAnimation;
import com.app.locker.utils.classes.ui.animation.ButtonHoverAnimation;
import com.app.locker.utils.classes.ui.animation.HBoxHoverAnimation;
import com.app.locker.utils.classes.core.AppProperties;
import com.app.locker.utils.classes.logic.View;
import com.app.locker.utils.interfaces.EventListener;
import com.app.locker.utils.interfaces.SidebarEventListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class SidebarExpandedViewController extends View {
    public TextField txtSearch;
    public Button btnSearch;
    @FXML private Button btnCollapseSidebar;
    @FXML private ImageView imgAdd;
    @FXML private ImageView imgEdit;
    @FXML private ImageView imgDelete;
    @FXML private ImageView imgDeleteAll;
    @FXML private ImageView imgGitHub;

    @FXML private HBox boxAdd;
    @FXML private HBox boxEdit;
    @FXML private HBox boxDelete;
    @FXML private HBox boxDeleteAll;
    @FXML private HBox boxGithub;

    private final SidebarEventListener sidebarEventListener;

    public SidebarExpandedViewController(EventListener eventListener) {
        super(eventListener);
        sidebarEventListener = (SidebarEventListener) eventListener;
    }

    @Override
    public void initializeView() {
        imgAdd.setImage(getImageCache().getAddIconColoured());
        imgDelete.setImage(getImageCache().getDeleteIconColoured());
        imgEdit.setImage(getImageCache().getEditIconColoured());
        imgDeleteAll.setImage(getImageCache().getDeleteAllIconColoured());
        imgGitHub.setImage(getImageCache().getGithubIconColoured());
        new HBoxHoverAnimation(boxAdd);
        new HBoxHoverAnimation(boxEdit);
        new HBoxHoverAnimation(boxDeleteAll);
        new HBoxHoverAnimation(boxDelete);
        new HBoxHoverAnimation(boxGithub);
        new ButtonHoverAnimation(btnCollapseSidebar, getImageCache().getSidebarIconColoured());
        new TextFieldAnimation(txtSearch);
    }

    @Override
    public String getResourcePath() {
        return AppProperties.PATH_SIDEBAR_EXPANDED;
    }

    public void enableButtonEdit(boolean enabled){
        boxEdit.setDisable(!enabled);
    }

    public void enableButtonDelete(boolean enabled){
        boxDelete.setDisable(!enabled);
    }

    @FXML
    public void onHBoxHoveredIn(MouseEvent mouseEvent){
    }

    @FXML
    public void onHBoxHoveredOut(MouseEvent mouseEvent){
    }

    public void onCollapseSidebarClicked(){
        sidebarEventListener.onCollapseSidebar();
    }

    @FXML
    public void onAddClicked(){
        sidebarEventListener.onAddClicked();
    }

    @FXML
    public void onEditClicked(){
        sidebarEventListener.onEditClicked();
    }

    @FXML
    public void onDeleteClicked(){
        sidebarEventListener.onDeleteClicked();
    }

    @FXML
    public void onDeleteAllClicked(){
        sidebarEventListener.onDeleteAllClicked();
    }

    @FXML
    public void onGithubClicked(){
        sidebarEventListener.onGithubClicked();
    }

}
