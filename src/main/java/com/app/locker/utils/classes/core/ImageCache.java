package com.app.locker.utils.classes.core;

import javafx.scene.image.Image;

import java.util.Objects;

public class ImageCache {

    private final Image addIconWhite;
    private final Image addIconColoured;
    private final Image searchIconWhite;
    private final Image searchIconColoured;
    private final Image editIconWhite;
    private final Image editIconColoured;
    private final Image deleteIconWhite;
    private final Image deleteIconColoured;
    private final Image deleteAllIconWhite;
    private final Image deleteAllIconColoured;
    private final Image githubIconWhite;
    private final Image githubIconColoured;
    private final Image sidebarIconWhite;
    private final Image sidebarIconColoured;
    private final Image personIconColoured;
    private final Image createdOnIconColoured;
    private final Image passwordIconColoured;
    private final Image emailIconColoured;
    private final Image serviceIconColoured;

    public ImageCache(){
        addIconWhite = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/add_white.png")));
        addIconColoured = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/add_coloured.png")));
        searchIconWhite = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/search_white.png")));
        searchIconColoured = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/search_coloured.png")));
        editIconWhite = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/edit_white.png")));
        editIconColoured = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/edit_coloured.png")));
        deleteIconWhite = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/delete_white.png")));
        deleteIconColoured = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/delete_coloured.png")));
        deleteAllIconWhite = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/delete_all_white.png")));
        deleteAllIconColoured = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/delete_all_coloured.png")));
        githubIconWhite = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/github_white.png")));
        githubIconColoured = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/github_coloured.png")));
        sidebarIconWhite = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/sidebar_white.png")));
        sidebarIconColoured = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/sidebar_coloured.png")));
        personIconColoured = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/person_coloured.png")));
        createdOnIconColoured = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/created_coloured.png")));
        passwordIconColoured = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/password_coloured.png")));
        emailIconColoured = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/email_coloured.png")));
        serviceIconColoured = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/service_coloured.png")));
    }

    public Image getAlternateImage(Image image){
        if (image.equals(addIconWhite)) return addIconColoured;
        if (image.equals(searchIconWhite)) return searchIconColoured;
        if (image.equals(editIconWhite)) return editIconColoured;
        if (image.equals(deleteIconWhite)) return deleteIconColoured;
        if (image.equals(deleteAllIconWhite)) return deleteAllIconColoured;
        if (image.equals(githubIconWhite)) return githubIconColoured;
        if (image.equals(sidebarIconWhite)) return sidebarIconColoured;

        if (image.equals(addIconColoured)) return addIconWhite;
        if (image.equals(searchIconColoured)) return searchIconWhite;
        if (image.equals(editIconColoured)) return editIconWhite;
        if (image.equals(deleteIconColoured)) return deleteIconWhite;
        if (image.equals(deleteAllIconColoured)) return deleteAllIconWhite;
        if (image.equals(githubIconColoured)) return githubIconWhite;
        if (image.equals(sidebarIconColoured)) return sidebarIconWhite;

        else
            return null;
    }

    public Image getAddIconWhite() {
        return addIconWhite;
    }

    public Image getAddIconColoured() {
        return addIconColoured;
    }

    public Image getSearchIconWhite() {
        return searchIconWhite;
    }

    public Image getSearchIconColoured() {
        return searchIconColoured;
    }

    public Image getEditIconWhite() {
        return editIconWhite;
    }

    public Image getEditIconColoured() {
        return editIconColoured;
    }

    public Image getDeleteIconWhite() {
        return deleteIconWhite;
    }

    public Image getDeleteIconColoured() {
        return deleteIconColoured;
    }

    public Image getDeleteAllIconWhite() {
        return deleteAllIconWhite;
    }

    public Image getDeleteAllIconColoured() {
        return deleteAllIconColoured;
    }

    public Image getGithubIconWhite() {
        return githubIconWhite;
    }

    public Image getGithubIconColoured() {
        return githubIconColoured;
    }

    public Image getSidebarIconWhite() {
        return sidebarIconWhite;
    }

    public Image getSidebarIconColoured() {
        return sidebarIconColoured;
    }

    public Image getPersonIconColoured() {
        return personIconColoured;
    }

    public Image getCreatedOnIconColoured() {
        return createdOnIconColoured;
    }

    public Image getPasswordIconColoured() {
        return passwordIconColoured;
    }

    public Image getEmailIconColoured() {
        return emailIconColoured;
    }

    public Image getServiceIconColoured() {
        return serviceIconColoured;
    }
}
