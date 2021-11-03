package com.app.locker.controller.fragments;

import com.app.locker.utils.classes.core.AppProperties;
import com.app.locker.utils.classes.ui.transition.ButtonHoverAnimation;
import com.app.locker.utils.classes.logic.View;
import com.app.locker.utils.interfaces.EventListener;
import com.app.locker.utils.interfaces.SidebarEventListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SidebarCollapsedViewController extends View {

    private final SidebarEventListener sidebarEventListener;
    @FXML private Button btnExpandSidebar;
    @FXML private Button btnSearch;
    @FXML private Button btnAdd;
    @FXML private Button btnEdit;
    @FXML private Button btnDelete;
    @FXML private Button btnDeleteAll;
    @FXML private Button btnGithub;

    public SidebarCollapsedViewController(EventListener eventListener) {
        super(eventListener);
        sidebarEventListener = (SidebarEventListener) eventListener;
    }

    @Override
    public void initializeView() {
        new ButtonHoverAnimation(btnExpandSidebar, getImageCache().getSidebarIconColoured());
        new ButtonHoverAnimation(btnSearch, getImageCache().getSearchIconColoured());
        new ButtonHoverAnimation(btnAdd, getImageCache().getAddIconColoured());
        new ButtonHoverAnimation(btnEdit, getImageCache().getEditIconColoured());
        new ButtonHoverAnimation(btnDelete, getImageCache().getDeleteIconColoured());
        new ButtonHoverAnimation(btnDeleteAll, getImageCache().getDeleteAllIconColoured());
        new ButtonHoverAnimation(btnGithub, getImageCache().getGithubIconColoured());
    }

    public void enableButtonEdit(boolean enabled){
        btnEdit.setDisable(!enabled);
    }

    public void enableButtonDelete(boolean enabled){
        btnDelete.setDisable(!enabled);
    }

    @Override
    public String getResourcePath() {
        return AppProperties.PATH_SIDEBAR_COLLAPSED;
    }

    public void onExpandSidebarClicked(){
        sidebarEventListener.onExpandSidebar();
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
